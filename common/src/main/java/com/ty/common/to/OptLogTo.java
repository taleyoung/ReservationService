package com.ty.common.to;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class OptLogTo implements Serializable {
    private Integer userId;

    private String userName;

    private String ip;

    private String type;

    private String model;

    private String description;

    private String result;

    private LocalDateTime optTime;

}
