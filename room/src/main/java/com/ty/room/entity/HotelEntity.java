package com.ty.room.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
@TableName("hotel")
public class HotelEntity implements Serializable {
    @TableId
    private Long id;
    private String name;
    private String image;
    private Integer score;
    private String location;
    private String description;
    private Date createTime;
    private Date updateTime;
}
