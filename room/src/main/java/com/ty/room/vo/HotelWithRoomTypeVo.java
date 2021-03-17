package com.ty.room.vo;

import com.ty.room.entity.HotelRoomTypeEntity;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class HotelWithRoomTypeVo {
    private Long id;
    private String name;
    private String image;
    private Double score;
    private String location;
    private String description;
    private String phoneNumber;
    private Date createTime;
    private Date updateTime;
    private List<HotelRoomTypeEntity> rooms;
}
