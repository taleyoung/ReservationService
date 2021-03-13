package com.ty.room.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ty.room.entity.MeetingEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MeetingDao extends BaseMapper<MeetingEntity> {
}
