package com.ty.room.service;

import com.ty.common.utils.PageUtils;
import com.ty.room.entity.HotelRoomEntity;

import java.util.Map;

public interface HotelRoomService {
    PageUtils queryPage(Map<String, Object> params);

    void add(HotelRoomEntity hotelRoomEntity);

    void update(Long id, HotelRoomEntity hotelRoomEntity);

    void delete(Long id);
}
