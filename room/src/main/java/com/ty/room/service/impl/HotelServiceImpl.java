package com.ty.room.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ty.common.utils.PageUtils;
import com.ty.common.utils.Query;
import com.ty.room.dao.HotelDao;
import com.ty.room.entity.HotelEntity;
import com.ty.room.entity.HotelRoomEntity;
import com.ty.room.service.HotelRoomService;
import com.ty.room.service.HotelService;
import com.ty.room.vo.HotelWithRoomVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("HotelService")
public class HotelServiceImpl extends ServiceImpl<HotelDao, HotelEntity> implements HotelService {

    @Autowired
    HotelRoomService hotelRoomService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage page = this.page(new Query<HotelEntity>().getPage(params), new QueryWrapper<>());
        return new PageUtils(page);
    }

    @Override
    public void addHotel(HotelEntity hotelEntity) {
        this.save(hotelEntity);
    }

    @Override
    public void updateHotel(Long id, HotelEntity hotelEntity) {
        this.updateById(hotelEntity);

    }

    @Override
    public void deleteHotel(Long id) {
        this.deleteHotel(id);
    }

    @Override
    public HotelEntity getHotelById(Long id) {
        return this.getById(id);
    }

    @Override
    public HotelWithRoomVo getHotelWithRoomById(Long id) {
        HotelWithRoomVo hotelWithRoomVo = new HotelWithRoomVo();
        List<HotelRoomEntity> hotelRoomList = hotelRoomService.getHotelRoomByHotelId(id);
        HotelEntity hotelById = this.getHotelById(id);
        BeanUtils.copyProperties(hotelById, hotelWithRoomVo);
        hotelWithRoomVo.setRooms(hotelRoomList);
        return hotelWithRoomVo;
    }
}
