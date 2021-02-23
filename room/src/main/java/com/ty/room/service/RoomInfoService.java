package com.ty.room.service;

import com.ty.common.utils.PageUtils;
import com.ty.room.entity.RoomInfoEntity;

import java.util.List;
import java.util.Map;

public interface RoomInfoService {
    List<RoomInfoEntity> queryPage(Map<String, Object> params);
}
