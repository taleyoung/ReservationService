package com.ty.room.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ty.common.utils.PageUtils;
import com.ty.common.utils.Query;
import com.ty.room.dao.MeetingDao;
import com.ty.room.dao.MeetingRoomDao;
import com.ty.room.entity.MeetingEntity;
import com.ty.room.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("MeetingService")
public class MeetingServiceImpl extends ServiceImpl<MeetingDao, MeetingEntity> implements MeetingService {

    @Autowired
    MeetingRoomDao meetingRoomDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MeetingEntity> page = this.page(new Query<MeetingEntity>().getPage(params), new QueryWrapper<>());

        return new PageUtils(page);
    }

    @Override
    public void addMeeting(MeetingEntity meetingEntity) {
        this.save(meetingEntity);
    }

    @Override
    public void updateMeeting(Long id, MeetingEntity meetingEntity) {
        this.updateById(meetingEntity);
    }

    @Override
    public void deleteMeeting(Long id) {
        this.deleteMeeting(id);
    }

    @Override
    public List<MeetingEntity> getMeetingByRoomIds(List<Long> roomIds) {
        QueryWrapper<MeetingEntity> wrapper = new QueryWrapper<MeetingEntity>().in("meeting_room_id", roomIds);
        List<MeetingEntity> meetingEntities = this.baseMapper.selectList(wrapper);

        return meetingEntities;
    }

    @Override
    public MeetingEntity getMeetingById(Long id) {
        MeetingEntity meetingEntity = this.getById(id);
        return meetingEntity;
    }
}
