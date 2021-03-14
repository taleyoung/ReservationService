package com.ty.room.entity;

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
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author taleyoung
 * @since 2021-03-14
 */
@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("hotel_room_item")
@ApiModel(value="HotelRoomItem对象", description="")
public class HotelRoomItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer hotelId;

    private Integer hotelRoomId;

    private LocalDate date;

    private Integer wareCount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
