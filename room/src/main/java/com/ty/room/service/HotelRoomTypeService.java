package com.ty.room.service;

import com.ty.common.utils.PageUtils;
import com.ty.room.entity.HotelRoomTypeEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HotelRoomTypeService {
    PageUtils queryPage(Map<String, Object> params, Integer hotelId);

    void add(HotelRoomTypeEntity hotelRoomTypeEntity);

    void update(Long id, HotelRoomTypeEntity hotelRoomTypeEntity);

    void delete(Long id);

    List<HotelRoomTypeEntity> getRoomByHotelIdAndDate(Long id, Date date);
}
