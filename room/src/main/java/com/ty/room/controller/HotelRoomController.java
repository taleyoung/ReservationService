package com.ty.room.controller;


import com.ty.common.utils.ApiResp;
import com.ty.room.entity.HotelRoomEntity;
import com.ty.room.service.HotelRoomService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author taleyoung
 * @since 2021-03-19
 */
@Api("酒店房间管理")
@RestController
@RequestMapping("/room/hotel-room")
public class HotelRoomController {
    @Autowired
    HotelRoomService hotelRoomService;

    @GetMapping("")
    public ApiResp<List<HotelRoomEntity>> getListByTypeId(@RequestParam Integer roomTypeId){
        List<HotelRoomEntity> list = hotelRoomService.getListByTypeId(roomTypeId);
        return ApiResp.retOK(list);
    }

}
