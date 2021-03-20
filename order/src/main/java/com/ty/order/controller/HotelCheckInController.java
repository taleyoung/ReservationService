package com.ty.order.controller;


import com.ty.common.utils.ApiResp;
import com.ty.common.utils.PageUtils;
import com.ty.order.service.HotelCheckInService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ApiResp queryPage(@RequestParam Map<String, Object> params){
        PageUtils list = hotelCheckInService.queryPage(params);
        return ApiResp.retOK(list);
    }

    @GetMapping("/wareWithDate")
    public  ApiResp getWareWithDate(@RequestParam("roomTypeId") Integer id, @RequestParam("date") String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        Integer wareWithDate = hotelCheckInService.getWareWithDate(id, sdf.parse(date));
        return ApiResp.retOK(wareWithDate);
    }

}
