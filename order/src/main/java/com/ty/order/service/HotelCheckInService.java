package com.ty.order.service;

import com.ty.common.to.HotelRoomTo;
import com.ty.common.utils.PageUtils;
import com.ty.order.entity.HotelCheckInEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ty.order.vo.HotelOrderVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taleyoung
 * @since 2021-03-17
 */
public interface HotelCheckInService extends IService<HotelCheckInEntity> {

    List<HotelCheckInEntity> getRecordsByTypeAndDate(Integer hotelRoomTypeId, Date date);

    HotelRoomTo newFreeRoomByTypeAndDate(Integer hotelRoomTypeId, Date date);

    void add(Integer orderId, HotelOrderVo hotelOrderVo);

    PageUtils queryPage(Map<String, Object> params);

    Integer getWareWithDate(Integer hotelRoomTypeId, Date date);

    void updateStatus(Integer orderId, Integer code);
}
