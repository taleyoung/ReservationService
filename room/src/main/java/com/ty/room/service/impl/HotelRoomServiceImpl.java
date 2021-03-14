package com.ty.room.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ty.common.utils.PageUtils;
import com.ty.common.utils.Query;
import com.ty.room.dao.HotelRoomDao;
import com.ty.room.entity.HotelEntity;
import com.ty.room.entity.HotelRoomEntity;
import com.ty.room.service.HotelRoomService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("HotelRoomService")
public class HotelRoomServiceImpl extends ServiceImpl<HotelRoomDao, HotelRoomEntity> implements HotelRoomService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage page = this.page(new Query<HotelRoomEntity>().getPage(params), new QueryWrapper<>());
        return new PageUtils(page);
    }

    @Override
    public void add(HotelRoomEntity hotelRoomEntity) {
        this.save(hotelRoomEntity);
    }

    @Override
    public void update(Long id, HotelRoomEntity hotelRoomEntity) {
        this.updateById(hotelRoomEntity);
    }

    @Override
    public void delete(Long id) {
        this.baseMapper.deleteById(id);
    }
}
