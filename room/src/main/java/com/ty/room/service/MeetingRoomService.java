package com.ty.room.service;

import com.ty.common.utils.PageUtils;
import com.ty.room.entity.MeetingRoomEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MeetingRoomService {
    PageUtils queryPage(Map<String, Object> params, Date date);

    void addRoom(MeetingRoomEntity roomInfo);

    void updateRoom(Long roomId, MeetingRoomEntity roomInfo);

    void deleteRoom(Long id);
}
