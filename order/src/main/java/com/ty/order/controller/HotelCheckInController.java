package com.ty.order.controller;


import com.ty.common.utils.ApiResp;
import com.ty.common.utils.PageUtils;
import com.ty.order.service.HotelCheckInService;
import com.ty.order.vo.CheckInStatusVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author taleyoung
 * @since 2021-03-17
 */
@Api("酒店房间登记")
@RestController
@RequestMapping("/order/hotel-check-in")
public class HotelCheckInController {
    @Autowired
    HotelCheckInService hotelCheckInService;

    @GetMapping("")
    public ApiResp queryPage(@RequestParam Map<String, Object> params, @RequestParam(value = "userId", required = false) Integer userId){
        PageUtils list = null;
        if(userId == null){
             list = hotelCheckInService.queryPage(params);
        }else{
             list = hotelCheckInService.queryPageByUserId(params, userId);
        }

        return ApiResp.retOK(list);
    }

    @GetMapping("/wareWithDate")
    public  ApiResp getWareWithDate(@RequestParam("roomTypeId") Integer id, @RequestParam("date") String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        Integer wareWithDate = hotelCheckInService.getWareWithDate(id, sdf.parse(date));
        return ApiResp.retOK(wareWithDate);
    }

    @PutMapping("/status")
    public  ApiResp updateStatus(@RequestBody CheckInStatusVo vo){
        hotelCheckInService.updateStatus(vo.getOrderId(), vo.getStatus());
        return ApiResp.retOK();

    }

}
