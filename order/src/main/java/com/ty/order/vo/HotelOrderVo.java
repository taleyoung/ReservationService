package com.ty.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Time;
import java.util.Date;


@Data
public class HotelOrderVo {

    private Integer hotelRoomTypeId;

    private String hotelRoomTypeName;

    private String hotelName;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date endDate;

    private Time expectedTime;

    private Integer userId;

    private String userName;

    private String personName;

    private String personIdNumber;

    private Integer totalPrice;

    private Integer payType;

}
