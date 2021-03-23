package com.ty.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

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
@TableName("hotel_order")
@ApiModel(value="HotelOrderEntity对象", description="")
public class HotelOrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer hotelRoomTypeId;

    private String hotelRoomTypeName;

    private String hotelName;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date endDate;

    private Integer userId;

    private String userName;

    private Integer totalPrice;

    @ApiModelProperty(value = "0表示支付宝 1微信")
    private Integer payType;

    @ApiModelProperty(value = "0锁定库存 1付款成功 2入住 3已退房")
    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
