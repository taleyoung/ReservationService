package com.ty.room.service;

import com.ty.common.utils.PageUtils;
import com.ty.room.entity.HotelEntity;
import com.ty.room.vo.HotelWithRoomVo;

import java.util.Map;

public interface HotelService {
    PageUtils queryPage(Map<String, Object> params);

    void addHotel(HotelEntity hotelEntity);

    void updateHotel(Long id, HotelEntity hotelEntity);

    void deleteHotel(Long id);

    HotelEntity getHotelById(Long id);

    HotelWithRoomVo getHotelWithRoomById(Long id);
}
