package com.ty.room.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("room_info")
public class RoomInfoEntity implements Serializable {

    @TableId
    private Long id;

    private String name;
    private Long status;
    private Long unitId;
    private String description;
    private Long userId;
}
