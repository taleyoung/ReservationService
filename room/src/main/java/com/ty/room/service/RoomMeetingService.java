package com.ty.room.service;

import com.ty.room.entity.RoomInfoEntity;
import com.ty.room.vo.RoomMeetingVo;

import java.util.List;
import java.util.Map;

public interface RoomMeetingService {
    List<RoomMeetingVo> queryPage(Map<String, Object> params);
}
