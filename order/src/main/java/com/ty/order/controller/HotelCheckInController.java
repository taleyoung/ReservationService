package com.ty.order.controller;


import com.ty.common.utils.ApiResp;
import com.ty.common.utils.PageUtils;
import com.ty.order.aop.OperationLogAnnotation;
import com.ty.order.service.HotelCheckInService;
import com.ty.order.vo.CheckInStatusVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @ApiOperation("获取客房情况表")
    @OperationLogAnnotation(optModule = "订单服务",optType = "查询", optDesc = "房间登记情况")
    public ApiResp queryPage(@RequestParam Map<String, Object> params){
        PageUtils list =  hotelCheckInService.queryPage(params);
        return ApiResp.retOK(list);
    }

    @GetMapping("/user")
    @ApiOperation("获取用户预订的酒店")
    public ApiResp queryPageUser(@RequestParam Map<String, Object> params, HttpServletRequest request){
        PageUtils list = hotelCheckInService.queryPageByUserId(params, request);

        return ApiResp.retOK(list);
    }

    @GetMapping("/wareWithDate")
    public  ApiResp getWareWithDate(@RequestParam("roomTypeId") Integer id, @RequestParam("date") String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        Integer wareWithDate = hotelCheckInService.getWareWithDate(id, sdf.parse(date));
        return ApiResp.retOK(wareWithDate);
    }

    @PutMapping("/status")
    @OperationLogAnnotation(optModule = "订单服务",optType = "修改", optDesc = "房间登记状态")
    public  ApiResp updateStatus(@RequestBody CheckInStatusVo vo){
        hotelCheckInService.updateStatus(vo.getOrderId(), vo.getStatus());
        return ApiResp.retOK();

    }

}
