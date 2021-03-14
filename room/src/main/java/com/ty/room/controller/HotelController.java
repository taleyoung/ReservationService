package com.ty.room.controller;

import com.ty.common.utils.ApiResp;
import com.ty.common.utils.PageUtils;
import com.ty.room.entity.HotelEntity;
import com.ty.room.service.HotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "酒店管理")
@RestController
@RequestMapping("room")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @GetMapping("/hotel")
    @ApiOperation("获取酒店列表")
    public ApiResp<PageUtils> queryPage(@RequestParam(required = false) Map<String, Object> params){
        PageUtils list = hotelService.queryPage(params);
        return ApiResp.retOK(list);
    }
    @PostMapping("/hotel")
    @ApiOperation(value = "添加酒店")
    public ApiResp addHotel(@RequestBody HotelEntity hotelEntity){
        hotelService.addHotel(hotelEntity);
        return ApiResp.retOK();
    }

    @PutMapping("/hotel/{id}")
    @ApiOperation(value = "修改酒店信息")
    public ApiResp updateHotel(@PathVariable("id") String hotelId, @RequestBody HotelEntity hotelEntity){
        Long id = Long.valueOf(hotelId);
        hotelService.updateHotel(id, hotelEntity);
        return ApiResp.retOK();
    }

    @DeleteMapping("/hotel/{id}")
    @ApiOperation(value = "删除酒店")
    public ApiResp deleteRoom(@PathVariable("id") String hotelId){
        Long id = Long.valueOf(hotelId);
        hotelService.deleteHotel(id);
        return ApiResp.retOK();
    }
}
