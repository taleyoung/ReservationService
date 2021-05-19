package com.ty.room.service;

import com.ty.common.utils.PageUtils;
import com.ty.room.entity.HotelEntity;
import com.ty.room.vo.HotelWithRoomTypeVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HotelService {
    PageUtils queryPage(Map<String, Object> params);

    PageUtils getList(Map<String, Object> params);

    void addHotel(HotelEntity hotelEntity);

    void updateHotel(Long id, HotelEntity hotelEntity);

    void deleteHotel(Long id);

    HotelEntity getHotelById(Long id);

    HotelWithRoomTypeVo getHotelWithRoomById(Long id, Date date);
}
