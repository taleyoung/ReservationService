package com.ty.room.controller;

import com.ty.common.utils.ApiResp;
import com.ty.common.utils.PageUtils;
import com.ty.room.entity.HotelRoomTypeEntity;
import com.ty.room.service.HotelRoomTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api("酒店房间类型")
@RestController
@RequestMapping("/room/hotel-room-type")
public class HotelRoomTypeController {

    @Autowired
    HotelRoomTypeService hotelRoomTypeService;

    @GetMapping("")
    @ApiOperation("获取某指定货酒店下的房间类型列表")
    public ApiResp<PageUtils> queryPage(@RequestParam(required = false) Map<String, Object> params, @RequestParam("hotelId") Integer hoteLId){
        PageUtils list = hotelRoomTypeService.queryPage(params, hoteLId);
        return ApiResp.retOK(list);
    }
    @PostMapping("")
    @ApiOperation(value = "添加酒店房间类型")
    public ApiResp add(@RequestBody HotelRoomTypeEntity hotelRoomTypeEntity){
        hotelRoomTypeService.add(hotelRoomTypeEntity);
        return ApiResp.retOK();
    }

    @PutMapping("{id}")
    @ApiOperation(value = "修改酒店房间类型信息")
    public ApiResp update(@PathVariable("id") String id, @RequestBody HotelRoomTypeEntity hotelRoomTypeEntity){
        hotelRoomTypeService.update(Long.valueOf(id), hotelRoomTypeEntity);
        return ApiResp.retOK();
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "删除酒店类型")
    public ApiResp delete(@PathVariable("id") String id){
        hotelRoomTypeService.delete(Long.valueOf(id));
        return ApiResp.retOK();
    }
}
