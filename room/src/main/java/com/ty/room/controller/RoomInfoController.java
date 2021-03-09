package com.ty.room.controller;

import com.ty.common.utils.ApiResp;
import com.ty.room.entity.RoomInfoEntity;
import com.ty.room.service.RoomInfoService;
import com.ty.room.service.RoomMeetingService;
import com.ty.room.vo.RoomMeetingVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "房间管理")
@RestController
@RequestMapping("room")
public class RoomInfoController {

    @Autowired
    RoomInfoService roomInfoService;

    @Autowired
    RoomMeetingService roomMeetingService;

    @GetMapping("/room")
    @ApiOperation(value = "获取房间列表")
    public ApiResp<List<RoomInfoEntity>> roomList(){
        Map map = new HashMap<>();
        map.put("xx","xx");
        List<RoomInfoEntity> list = roomInfoService.queryPage(map);
        return ApiResp.retOK(list);
    }

    @PostMapping("/room")
    @ApiOperation(value = "增加会议室")
    public ApiResp addRoom(@RequestBody RoomInfoEntity roomInfo){
        roomInfoService.addRoom(roomInfo);
        return ApiResp.retOK();
    }

    @PutMapping("/room/{id}")
    @ApiOperation(value = "更新会议室")
    public ApiResp updateRoom(@PathVariable("id") String roomId, @RequestBody RoomInfoEntity roomInfo){
        Long id = Long.valueOf(roomId);
        roomInfoService.updateRoom(id, roomInfo);
        return ApiResp.retOK();
    }


    @GetMapping("/meeting")
    @ApiOperation(value = "获取房间会议列表")
    public ApiResp<List<RoomMeetingVo>> roomMeetingList(){
        Map map = new HashMap<>();
        map.put("xx","xx");
        List<RoomMeetingVo> list = roomMeetingService.queryPage(map);
        return ApiResp.retOK(list);
    }
}
