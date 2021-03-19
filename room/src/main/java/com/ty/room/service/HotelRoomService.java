package com.ty.room.service;

import com.ty.room.entity.HotelRoomEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taleyoung
 * @since 2021-03-19
 */
public interface HotelRoomService extends IService<HotelRoomEntity> {

    List<HotelRoomEntity> getListByTypeId(Integer roomTypeId);
}
