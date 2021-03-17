package com.ty.room.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ty.common.utils.PageUtils;
import com.ty.common.utils.Query;
import com.ty.room.dao.HotelRoomTypeDao;
import com.ty.room.entity.HotelEntity;
import com.ty.room.entity.HotelRoomTypeEntity;
import com.ty.room.service.HotelRoomTypeService;
import com.ty.room.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("HotelRoomService")
public class HotelRoomTypeServiceImpl extends ServiceImpl<HotelRoomTypeDao, HotelRoomTypeEntity> implements HotelRoomTypeService {
    @Autowired
    HotelService hotelService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage page = this.page(new Query<HotelRoomTypeEntity>().getPage(params), new QueryWrapper<>());
        return new PageUtils(page);
    }

    @Override
    public void add(HotelRoomTypeEntity hotelRoomTypeEntity) {
        if(hotelRoomTypeEntity.getHotelName() == null){
            HotelEntity hotelEntity = hotelService.getHotelById(hotelRoomTypeEntity.getHotelId());
            hotelRoomTypeEntity.setHotelName(hotelEntity.getName());
        }
        this.save(hotelRoomTypeEntity);
    }

    @Override
    public void update(Long id, HotelRoomTypeEntity hotelRoomTypeEntity) {
        this.updateById(hotelRoomTypeEntity);
    }

    @Override
    public void delete(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public List<HotelRoomTypeEntity> getHotelRoomByHotelId(Long id) {
        QueryWrapper<HotelRoomTypeEntity> wrapper = new QueryWrapper<HotelRoomTypeEntity>().eq("hotel_id", id);
        List<HotelRoomTypeEntity> hotelRoomEntities = this.baseMapper.selectList(wrapper);
        return hotelRoomEntities;
    }
}
