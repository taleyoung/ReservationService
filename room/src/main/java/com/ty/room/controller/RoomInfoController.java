package com.ty.room.controller;

import com.ty.common.utils.ApiResp;
import com.ty.room.entity.RoomInfoEntity;
import com.ty.room.service.RoomInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "房间管理")
@RestController
@RequestMapping("room")
public class RoomInfoController {

    @Autowired
    RoomInfoService roomInfoService;

    @GetMapping("/info")
    @ApiOperation(value = "获取房间列表")
    public ApiResp list(){
        Map map = new HashMap<>();
        map.put("xx","xx");
        List<RoomInfoEntity> list = roomInfoService.queryPage(map);
        return ApiResp.retOK(list);
    }
}
