package com.ty.room.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ty.room.dao.RoomInfoDao;
import com.ty.room.entity.RoomInfoEntity;
import com.ty.room.service.RoomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("RoomInfoService")
public class RoomInfoServiceImpl implements RoomInfoService {

    @Autowired
    RoomInfoDao roomInfoDao;

    @Override
    public List<RoomInfoEntity> queryPage(Map<String, Object> params) {
        QueryWrapper<RoomInfoEntity> wrapper = new QueryWrapper<>();
        List<RoomInfoEntity> roomInfoEntities = roomInfoDao.selectList(null);
       return roomInfoEntities;
    }

    @Override
    public void addRoom(RoomInfoEntity roomInfo) {
        roomInfoDao.insert(roomInfo);
    }

    @Override
    public void updateRoom(Long roomId, RoomInfoEntity roomInfo) {
        roomInfoDao.updateById(roomInfo);
    }


}
