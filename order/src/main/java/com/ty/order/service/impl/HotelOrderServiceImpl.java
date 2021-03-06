package com.ty.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ty.common.enume.CheckInEnum;
import com.ty.common.enume.OrderStatusEnum;
import com.ty.common.utils.ApiResp;
import com.ty.common.utils.PageUtils;
import com.ty.common.utils.Query;
import com.ty.order.entity.HotelCheckInEntity;
import com.ty.order.entity.HotelOrderEntity;
import com.ty.order.dao.HotelOrderDao;
import com.ty.order.service.HotelCheckInService;
import com.ty.order.service.HotelOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ty.order.vo.HotelOrderVo;
import com.ty.order.vo.PayVo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taleyoung
 * @since 2021-03-17
 */
@Service
public class HotelOrderServiceImpl extends ServiceImpl<HotelOrderDao, HotelOrderEntity> implements HotelOrderService {

    @Autowired
    HotelCheckInService hotelCheckInService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HotelOrderEntity> page = this.page(
                new Query<HotelOrderEntity>().getPage(params),
                new QueryWrapper<HotelOrderEntity>().orderByDesc("create_time"));
        return new PageUtils(page);
    }

    @Transactional
    @Override
    public HotelOrderEntity createOrder(HotelOrderVo hotelOrderVo) {
        HotelOrderEntity hotelOrderEntity = new HotelOrderEntity();
        //TODO 登记业务
        BeanUtils.copyProperties(hotelOrderVo, hotelOrderEntity);
        hotelOrderEntity.setStatus(OrderStatusEnum.CREATE_NEW.getCode());
        boolean save = this.save(hotelOrderEntity);

        // 添加登记表
        hotelCheckInService.add(hotelOrderEntity.getId(), hotelOrderVo);
        //TODO 去支付
        //消息队列发消息 创建订单
        rabbitTemplate.convertAndSend("order-event-exchange","order.create.order",hotelOrderEntity);
        return hotelOrderEntity;
    }

    @Override
    public void handlePayResult(boolean payResult, Integer hotelOrderId){
        HotelOrderEntity orderEntity = this.getById(hotelOrderId);
        if(payResult){
            //付款成功 更改状态
            orderEntity.setStatus(OrderStatusEnum.PAYED.getCode());
            this.updateById(orderEntity);
            hotelCheckInService.updateStatus(hotelOrderId, CheckInEnum.WAIT_CHECK_IN.getCode());
        }else{
            orderEntity.setStatus(OrderStatusEnum.ERROR.getCode());
            this.updateById(orderEntity);
            //待定
            hotelCheckInService.updateStatus(hotelOrderId, CheckInEnum.CANCEL.getCode());
        }

    }

    @Override
    public void updateOrder(HotelOrderEntity hotelOrderEntity) {
        this.update(new QueryWrapper<HotelOrderEntity>(hotelOrderEntity));
    }

    @Override
    public PayVo getOrderPay(String orderSn) {
        PayVo payVo = new PayVo();
        payVo.setBody("xxx");
        payVo.setOut_trade_no("12344");
        payVo.setSubject("xxxx");
        payVo.setTotal_amount("200");
        return payVo;
    }

    @Override
    public void testPayAndSuccess(HotelOrderVo hotelOrderVo) {
        HotelOrderEntity orderEntity = this.createOrder(hotelOrderVo);
        this.handlePayResult(true, orderEntity.getId());
    }

    @Override
    public void testPayAndCancel(HotelOrderVo hotelOrderVo) {
        HotelOrderEntity orderEntity = this.createOrder(hotelOrderVo);
        rabbitTemplate.convertAndSend("order-event-exchange","order.create.order",orderEntity);
    }
}
