package com.ty.room.service;

import com.ty.common.utils.PageUtils;
import com.ty.room.entity.HotelEntity;

import java.util.Map;

public interface HotelService {
    PageUtils queryPage(Map<String, Object> params);

    void addHotel(HotelEntity hotelEntity);

    void updateHotel(Long id, HotelEntity hotelEntity);

    void deleteHotel(Long id);
}
