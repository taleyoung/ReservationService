package com.ty.room.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ty.common.utils.PageUtils;
import com.ty.room.dao.RoomInfoDao;
import com.ty.room.entity.RoomInfoEntity;
import com.ty.room.service.RoomInfoService;
import com.ty.room.vo.RoomInfoVo;
import org.springframework.beans.BeanUtils;
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
    public List<RoomInfoVo> queryPage(Map<String, Object> params) {
        QueryWrapper<RoomInfoEntity> wrapper = new QueryWrapper<>();
        List<RoomInfoEntity> roomInfoEntities = roomInfoDao.selectList(null);
        List<RoomInfoVo> collect = roomInfoEntities.stream().map(entity -> {
            RoomInfoVo roomInfoVo = new RoomInfoVo();
            BeanUtils.copyProperties(entity, roomInfoVo);
            List<RoomInfoVo.RsvTime> reservedTime = new ArrayList<>();
            RoomInfoVo.RsvTime rsvTime = new RoomInfoVo.RsvTime();
            rsvTime.setDate("2021-03-02");
            rsvTime.setStart("10:00");
            rsvTime.setEnd("12：00");
            reservedTime.add(rsvTime);
            reservedTime.add(rsvTime);
            System.out.println("tt"+reservedTime);
            roomInfoVo.setReservedTimeList(reservedTime);
            return roomInfoVo;
        }).collect(Collectors.toList());
        System.out.println("collect"+collect);
        return collect;
    }
}
