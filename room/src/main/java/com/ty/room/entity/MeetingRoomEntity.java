package com.ty.room.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@TableName("meeting_room")
public class MeetingRoomEntity implements Serializable {

    @TableId(value = "id", type= IdType.AUTO)
    private Long id;
    private String name;
    private String device;
    private Long capacity;
    private String location;
    private String description;
    private Long adminId;
    private String adminName;
    private Long status;
    private String areaName;
    private Long areaId;

    @TableField(exist = false)
    private List<RsvTime> rsvTimeList;

    @Data
    public static class RsvTime{
        Long meetingId;
        Date date;
        Time start;
        Time end;
    }
}
