package com.ty.room.controller;

import com.ty.common.utils.ApiResp;
import com.ty.common.utils.PageUtils;
import com.ty.room.entity.HotelRoomEntity;
import com.ty.room.service.HotelRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api("酒店房间")
@RestController
@RequestMapping("/room/hotel-room")
public class HotelRoomController {

    @Autowired
    HotelRoomService hotelRoomService;

    @GetMapping("/")
    @ApiOperation("获取酒店房间列表")
    public ApiResp<PageUtils> queryPage(@RequestParam(required = false) Map<String, Object> params){
        PageUtils list = hotelRoomService.queryPage(params);
        return ApiResp.retOK(list);
    }
    @PostMapping("/")
    @ApiOperation(value = "添加酒店房间")
    public ApiResp add(@RequestBody HotelRoomEntity hotelRoomEntity){
        hotelRoomService.add(hotelRoomEntity);
        return ApiResp.retOK();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改酒店房间信息")
    public ApiResp update(@PathVariable("id") String id, @RequestBody HotelRoomEntity hotelRoomEntity){
        hotelRoomService.update(Long.valueOf(id), hotelRoomEntity);
        return ApiResp.retOK();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除酒店")
    public ApiResp delete(@PathVariable("id") String id){
        hotelRoomService.delete(Long.valueOf(id));
        return ApiResp.retOK();
    }
}
