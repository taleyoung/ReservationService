package com.ty.common.to;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HotelRoomTo {
    private Integer id;

    private Integer hotelRoomTypeId;

    private String roomNum;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
