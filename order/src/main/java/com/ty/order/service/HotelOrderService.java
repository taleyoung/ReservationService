package com.ty.order.service;

import com.ty.common.utils.PageUtils;
import com.ty.order.entity.HotelOrderEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ty.order.vo.HotelOrderVo;
import com.ty.order.vo.PayVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taleyoung
 * @since 2021-03-17
 */
public interface HotelOrderService extends IService<HotelOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    HotelOrderEntity createOrder(HotelOrderVo hotelOrderVo);

    void updateOrder(HotelOrderEntity hotelOrderEntity);

    PayVo getOrderPay(String orderSn);

    void handlePayResult(boolean payResult, Integer hotelOrderId);

    void testPayAndSuccess(HotelOrderVo hotelOrderVo);

    void testPayAndCancel(HotelOrderVo hotelOrderVo);
}
