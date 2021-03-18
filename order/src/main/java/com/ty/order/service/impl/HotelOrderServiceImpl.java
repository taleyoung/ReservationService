package com.ty.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ty.common.enume.OrderStatusEnum;
import com.ty.common.utils.PageUtils;
import com.ty.common.utils.Query;
import com.ty.order.entity.HotelOrderEntity;
import com.ty.order.dao.HotelOrderDao;
import com.ty.order.service.HotelOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ty.order.vo.HotelOrderVo;
import com.ty.order.vo.PayVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HotelOrderEntity> page = this.page(new Query<HotelOrderEntity>().getPage(params), new QueryWrapper<>());
        return new PageUtils(page);
    }

    @Override
    public void add(HotelOrderVo hotelOrderVo) {
        HotelOrderEntity hotelOrderEntity = new HotelOrderEntity();
        //TODO 登记业务
        BeanUtils.copyProperties(hotelOrderVo, hotelOrderEntity);
        hotelOrderEntity.setStatus(OrderStatusEnum.CREATE_NEW.getCode());
        boolean save = this.save(hotelOrderEntity);
        //TODO 去支付
        Boolean isPayedSuccess = true;
        //若支付失败
        if(!isPayedSuccess){
            hotelOrderEntity.setStatus(OrderStatusEnum.ERROR.getCode());
            this.updateOrder(hotelOrderEntity);
        }
        //支付成功


    }

    @Override
    public void updateOrder(HotelOrderEntity hotelOrderEntity) {
        this.update(new QueryWrapper<HotelOrderEntity>(hotelOrderEntity));
    }

    @Override
    public PayVo getOrderPay(String orderSn) {
        return null;
    }
}
