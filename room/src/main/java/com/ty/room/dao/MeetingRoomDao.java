package com.ty.room.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ty.room.entity.MeetingRoomEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MeetingRoomDao extends BaseMapper<MeetingRoomEntity> {
}