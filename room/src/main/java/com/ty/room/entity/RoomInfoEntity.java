package com.ty.room.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("room_info")
public class RoomInfoEntity implements Serializable {

    @TableId(value = "id", type= IdType.AUTO)
    private Long id;
    private String name;
    private String device;
    private Long capacity;
    private String location;
    private String description;
    private Long adminId;
    private Long status;
    private String areaName;
    private Long areaId;

//    @TableField(exist = false)
//    private List<Object> reservedTimeList;
}
