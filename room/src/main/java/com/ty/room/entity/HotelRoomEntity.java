package com.ty.room.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("hotel_room")
public class HotelRoomEntity implements Serializable {
    @TableId
    private Long id;
    private String name;
    private Long hotelId;
    private String hotelName;
    private Integer areaCount;
    private Integer bedCount;
    private Integer originalPrice;
}
