package com.ty.room.controller;

import com.ty.common.utils.ApiResp;
import com.ty.common.utils.PageUtils;
import com.ty.room.entity.MeetingRoomEntity;
import com.ty.room.service.MeetingRoomService;
import com.ty.room.vo.RoomMeetingVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Api(tags = "房间管理")
@RestController
@RequestMapping("room")
public class MeetingRoomController {

    @Autowired
    MeetingRoomService meetingRoomService;


    @GetMapping("/meeting-room")
    @ApiOperation(value = "获取房间列表")
    public ApiResp<PageUtils> roomList(@RequestParam Map<String, Object> pageParams, @RequestParam(value = "date", required = false) String date ) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        PageUtils list = meetingRoomService.queryPage(pageParams, sdf.parse(date));
        return ApiResp.retOK(list);
    }

    @PostMapping("/meeting-room")
    @ApiOperation(value = "增加会议室")
    public ApiResp addRoom(@RequestBody MeetingRoomEntity roomInfo){
        meetingRoomService.addRoom(roomInfo);
        return ApiResp.retOK();
    }

    @PutMapping("/meeting-room/{id}")
    @ApiOperation(value = "更新会议室")
    public ApiResp updateRoom(@PathVariable("id") String roomId, @RequestBody MeetingRoomEntity roomInfo){
        Long id = Long.valueOf(roomId);
        meetingRoomService.updateRoom(id, roomInfo);
        return ApiResp.retOK();
    }

    @DeleteMapping("/meeting-room/{id}")
    @ApiOperation(value = "删除会议室")
    public ApiResp deleteRoom(@PathVariable("id") String roomId){
        Long id = Long.valueOf(roomId);
        meetingRoomService.deleteRoom(id);
        return ApiResp.retOK();
    }


//    @GetMapping("/meeting")
//    @ApiOperation(value = "获取房间会议列表")
//    public ApiResp<List<RoomMeetingVo>> roomMeetingList(){
//        Map map = new HashMap<>();
//        map.put("xx","xx");
//        List<RoomMeetingVo> list = roomMeetingService.queryPage(map);
//        return ApiResp.retOK(list);
//    }
}
