package com.ty.room.controller;

import com.ty.room.entity.RoomInfoEntity;
import com.ty.room.service.RoomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("room")
public class RoomInfoController {

    @Autowired
    RoomInfoService roomInfoService;

    @GetMapping("/")
    public List<RoomInfoEntity> list(){
        Map map = new HashMap<>();
        map.put("xx","xx");
        return roomInfoService.queryPage(map);
    }
}
