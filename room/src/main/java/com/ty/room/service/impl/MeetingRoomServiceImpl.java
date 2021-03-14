package com.ty.room.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ty.common.utils.PageUtils;
import com.ty.common.utils.Query;
import com.ty.room.dao.MeetingRoomDao;
import com.ty.room.entity.MeetingEntity;
import com.ty.room.entity.MeetingRoomEntity;
import com.ty.room.service.MeetingRoomService;
import com.ty.room.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("MeetingRoomService")
public class MeetingRoomServiceImpl extends ServiceImpl<MeetingRoomDao, MeetingRoomEntity> implements MeetingRoomService {

    @Autowired
    MeetingRoomDao meetingRoomDao;

    @Autowired
    MeetingService meetingService;

    @Override
    public PageUtils queryPage(Map<String, Object> params, Date date) {
        IPage<MeetingRoomEntity> page = this.page(new Query<MeetingRoomEntity>().getPage(params), new QueryWrapper<>());

        if(date == null){
            return new PageUtils(page);
        }
        List<Long> roomIdList = page.getRecords().stream().map(MeetingRoomEntity::getId).collect(Collectors.toList());
        List<MeetingEntity> meetingList = meetingService.getMeetingByRoomIdsAndDate(roomIdList,  date);
        List<MeetingRoomEntity> collect = page.getRecords().stream().map(record -> {
            List<MeetingRoomEntity.RsvTime> rsvTimeList = new ArrayList<>();
            meetingList.stream().forEach(item -> {
                if (item.getMeetingRoomId().equals(record.getId())) {
                    MeetingRoomEntity.RsvTime rsvTime = new MeetingRoomEntity.RsvTime();
                    rsvTime.setMeetingId(item.getId());
                    rsvTime.setDate(item.getDate());
                    rsvTime.setStart(item.getStart());
                    rsvTime.setEnd(item.getEnd());
                    rsvTimeList.add(rsvTime);
                }
            });
            record.setRsvTimeList(rsvTimeList);
            return record;
        }).collect(Collectors.toList());
        IPage<MeetingRoomEntity> meetingRoomEntityIPage = page.setRecords(collect);
        return new PageUtils(meetingRoomEntityIPage);

    }

    @Override
    public void addRoom(MeetingRoomEntity roomInfo) {
        meetingRoomDao.insert(roomInfo);
    }

    @Override
    public void updateRoom(Long roomId, MeetingRoomEntity roomInfo) {
        meetingRoomDao.updateById(roomInfo);
    }

    @Override
    public void deleteRoom(Long id) {
        meetingRoomDao.deleteById(id);
    }


}
