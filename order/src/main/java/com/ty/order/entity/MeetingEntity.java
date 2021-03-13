package com.ty.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("meeting")
public class MeetingEntity implements Serializable {

    @TableId(value = "id")
    private Long id;
    private String name;
    private Date date;
    private Date start;
    private Date end;
    private Integer creatorId;
    private Integer memberCount;
    private Date createTime;

}
