package com.ty.room.controller;

import com.ty.common.utils.ApiResp;
import com.ty.common.utils.PageUtils;
import com.ty.room.aop.OperationLogAnnotation;
import com.ty.room.entity.MeetingEntity;
import com.ty.room.service.MeetingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "会议管理")
@RestController
@RequestMapping(value = "room")
public class MeetingController {

    @Autowired
    MeetingService meetingService;

    @GetMapping("/meeting")
    @ApiOperation(value = "获取会议列表")
    public ApiResp<PageUtils> roomList(@RequestParam Map<String, Object> params){
        PageUtils list = meetingService.queryPage(params);
        return ApiResp.retOK(list);
    }

    @GetMapping("/meeting/{id}")
    @ApiOperation(value = "通过id获取会议")
    public ApiResp<MeetingEntity> getMeetingById(@PathVariable("id") String id){
        MeetingEntity meetingEntity = meetingService.getMeetingById(Long.valueOf(id));
        return ApiResp.retOK(meetingEntity);
    }

    @PostMapping("/meeting")
    @ApiOperation(value = "添加会议")
    @OperationLogAnnotation(optModule = "会议服务",optType = "新增", optDesc = "会议")
    public ApiResp addRoom(@RequestBody MeetingEntity meetingEntity){
        meetingService.addMeeting(meetingEntity);
        return ApiResp.retOK();
    }

    @PutMapping("/meeting/{id}")
    @ApiOperation(value = "修改会议")
    @OperationLogAnnotation(optModule = "会议服务",optType = "修改", optDesc = "会议")
    public ApiResp updateRoom(@PathVariable("id") String roomId, @RequestBody MeetingEntity meetingEntity){
        Long id = Long.valueOf(roomId);
        meetingService.updateMeeting(id, meetingEntity);
        return ApiResp.retOK();
    }

    @DeleteMapping("/meeting/{id}")
    @ApiOperation(value = "删除会议")
    @OperationLogAnnotation(optModule = "会议服务",optType = "删除", optDesc = "会议")
    public ApiResp deleteRoom(@PathVariable("id") String roomId){
        Long id = Long.valueOf(roomId);
        meetingService.deleteMeeting(id);
        return ApiResp.retOK();
    }
}
