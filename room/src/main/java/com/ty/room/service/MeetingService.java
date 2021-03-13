package com.ty.room.service;

import com.ty.common.utils.PageUtils;
import com.ty.room.entity.MeetingEntity;
import com.ty.room.entity.MeetingRoomEntity;
import com.ty.room.vo.RoomMeetingVo;

import java.util.List;
import java.util.Map;

public interface MeetingService {
    PageUtils queryPage(Map<String, Object> params);

    void addMeeting(MeetingEntity meetingEntity);

    void updateMeeting(Long id, MeetingEntity meetingEntity);

    void deleteMeeting(Long id);

    List<MeetingEntity> getMeetingByRoomIds(List<Long> roomIds);

    MeetingEntity getMeetingById(Long id);
}
