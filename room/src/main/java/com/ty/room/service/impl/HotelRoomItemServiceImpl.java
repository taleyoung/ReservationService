package com.ty.room.service.impl;

import com.ty.room.dao.HotelRoomItemDao;
import com.ty.room.entity.HotelRoomItemEntity;
import com.ty.room.service.HotelRoomItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taleyoung
 * @since 2021-03-14
 */
@Service("HotelRoomItemService")
public class HotelRoomItemServiceImpl extends ServiceImpl<HotelRoomItemDao, HotelRoomItemEntity> implements HotelRoomItemService {

}
