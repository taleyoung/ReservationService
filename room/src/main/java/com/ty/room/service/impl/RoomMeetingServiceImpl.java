package com.ty.room.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ty.room.dao.RoomInfoDao;
import com.ty.room.entity.RoomInfoEntity;
import com.ty.room.service.RoomMeetingService;
import com.ty.room.vo.RoomMeetingVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("RoomMeetingService")
public class RoomMeetingServiceImpl implements RoomMeetingService {

    @Autowired
    RoomInfoDao roomInfoDao;

    @Override
    public List<RoomMeetingVo> queryPage(Map<String, Object> params) {
        QueryWrapper<RoomInfoEntity> wrapper = new QueryWrapper<>();
        List<RoomInfoEntity> roomInfoEntities = roomInfoDao.selectList(null);
        List<RoomMeetingVo> collect = roomInfoEntities.stream().map(entity -> {
            RoomMeetingVo roomInfoVo = new RoomMeetingVo();
            BeanUtils.copyProperties(entity, roomInfoVo);
            List<RoomMeetingVo.RsvTime> reservedTime = new ArrayList<>();
            RoomMeetingVo.RsvTime rsvTime = new RoomMeetingVo.RsvTime();
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
