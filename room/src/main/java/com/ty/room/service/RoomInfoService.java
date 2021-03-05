package com.ty.room.service;

import com.ty.common.utils.PageUtils;
import com.ty.room.entity.RoomInfoEntity;
import com.ty.room.vo.RoomInfoVo;

import java.util.List;
import java.util.Map;

public interface RoomInfoService {
    List<RoomInfoVo> queryPage(Map<String, Object> params);
}
