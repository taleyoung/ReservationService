package com.ty.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.sql.Time;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author taleyoung
 * @since 2021-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("hotel_check_in")
@ApiModel(value="HotelCheckInEntity对象", description="")
public class HotelCheckInEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String hotelRoomTypeName;

    private String hotelName;

    private Integer hotelRoomId;

    private String hotelRoomNum;

    private Integer hotelRoomTypeId;

    private Integer orderId;

    private Integer userId;

    private String userName;

    private String personName;

    private String personIdNumber;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date date;

    private Time expectedTime;

    @ApiModelProperty(value = "0已锁定未入住 1 已入住 2已退房")
    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
