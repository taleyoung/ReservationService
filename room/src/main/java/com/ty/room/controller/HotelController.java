package com.ty.room.controller;

import com.ty.common.utils.ApiResp;
import com.ty.common.utils.PageUtils;
import com.ty.room.entity.HotelEntity;
import com.ty.room.service.HotelRoomTypeService;
import com.ty.room.service.HotelService;
import com.ty.room.vo.HotelWithRoomTypeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Api(value = "酒店管理")
@RestController
@RequestMapping("room/hotel")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @Autowired
    HotelRoomTypeService hotelRoomTypeService;

    @GetMapping("")
    @ApiOperation("获取酒店列表")
    public ApiResp<PageUtils> queryPage(@RequestParam(required = false) Map<String, Object> params){
        PageUtils list = hotelService.queryPage(params);
        return ApiResp.retOK(list);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id获取带房型的酒店详情")
    public ApiResp getHotelAndRoomByIdAndDate(@PathVariable("id") String id, @RequestParam("date") String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        HotelWithRoomTypeVo res = hotelService.getHotelWithRoomById(Long.valueOf(id), sdf.parse(date));
        return ApiResp.retOK(res);
    }

    @GetMapping("/{id}/hotel-room-type")
    @ApiOperation("根据酒店id获得房间类型")
    public ApiResp getRoomTypeByHotelId(@RequestParam(required = false) Map<String, Object> params, @PathVariable("id") Integer hotelId) {
        PageUtils list = hotelRoomTypeService.queryPage(params, hotelId);
        return ApiResp.retOK(list);
    }

    @PostMapping("")
    @ApiOperation(value = "添加酒店")
    public ApiResp addHotel(@RequestBody HotelEntity hotelEntity){
        hotelService.addHotel(hotelEntity);
        return ApiResp.retOK();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改酒店信息")
    public ApiResp updateHotel(@PathVariable("id") String hotelId, @RequestBody HotelEntity hotelEntity){
        Long id = Long.valueOf(hotelId);
        hotelService.updateHotel(id, hotelEntity);
        return ApiResp.retOK();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除酒店")
    public ApiResp deleteRoom(@PathVariable("id") String hotelId){
        Long id = Long.valueOf(hotelId);
        hotelService.deleteHotel(id);
        return ApiResp.retOK();
    }
}
