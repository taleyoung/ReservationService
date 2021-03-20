package com.ty.room.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("hotel_room_type")
public class HotelRoomTypeEntity implements Serializable {
    @TableId
    private Integer id;
    private String name;
    private Long hotelId;
    private String hotelName;
    private Integer areaCount;
    private Integer bedCount;
    private Integer originalPrice;
    private Integer wareCount;

    @TableField(exist = false)
    private Integer wareWithDate;
}
