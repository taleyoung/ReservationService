package com.ty.room.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.omg.CORBA.IDLType;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Data
@TableName("meeting")
public class MeetingEntity implements Serializable {

    @TableId(value = "id", type= IdType.AUTO)
    private Long id;
    private String name;
    private Long meetingRoomId;
    private String meetingRoomName;
    private Date date;
    private Time start;
    private Time end;
    private Integer creatorId;
    private String creatorName;
    private Integer memberCount;
    private Date createTime;
    private Date updateTime;

}