package com.ty.order.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class HotelOrderVo {

    private Integer hotelRoomTypeId;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer userId;

    private String userName;

    private String personName;

    private String personIdNumber;

    private Integer totalPrice;

    private Integer payType;

    private Integer status;


}
