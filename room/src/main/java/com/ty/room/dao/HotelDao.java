package com.ty.room.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ty.room.entity.HotelEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface HotelDao extends BaseMapper<HotelEntity> {
}
