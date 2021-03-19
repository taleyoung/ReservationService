package com.ty.room.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ty.room.entity.HotelRoomEntity;
import com.ty.room.dao.HotelRoomDao;
import com.ty.room.service.HotelRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taleyoung
 * @since 2021-03-19
 */
@Service
public class HotelRoomServiceImpl extends ServiceImpl<HotelRoomDao, HotelRoomEntity> implements HotelRoomService {

    @Override
    public List<HotelRoomEntity> getListByTypeId(Integer roomTypeId) {

        List<HotelRoomEntity> list = this.list(new QueryWrapper<HotelRoomEntity>().eq("hotel_room_type_id", roomTypeId));
        return list;
    }
}
