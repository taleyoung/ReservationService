package com.ty.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
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

    private Integer hotelRoomId;

    private Integer orderId;

    private String personName;

    private String personIdNumber;

    private LocalDate date;

    @ApiModelProperty(value = "0已锁定未入住 1 已入住 2已退房")
    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
