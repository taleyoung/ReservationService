package com.ty.room.vo;

import lombok.Data;

import java.util.List;

@Data
public class RoomInfoVo {

    private Long id;
    private String name;
    private String device;
    private Long capacity;
    private String location;
    private String description;
    private Long adminId;
    private Long status;
    private String areaName;
    private Long areaId;
    private List<RsvTime> reservedTimeList;

    @Data
    public static class RsvTime{
        String date;
        String start;
        String end;
    }

}
