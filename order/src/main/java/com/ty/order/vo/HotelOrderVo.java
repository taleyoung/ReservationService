package com.ty.order.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class HotelOrderVo {

    private Integer hotelRoomTypeId;

    private Date startDate;

    private Date endDate;

    private Time expectedTime;

    private Integer userId;

    private String userName;

    private String personName;

    private String personIdNumber;

    private Integer totalPrice;

    private Integer payType;

}
