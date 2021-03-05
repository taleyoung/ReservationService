package com.ty.room.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("room_info")
public class RoomInfoEntity implements Serializable {

    @TableId
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

    @TableField(exist = false)
    private List<Object> reservedTimeList;
}
