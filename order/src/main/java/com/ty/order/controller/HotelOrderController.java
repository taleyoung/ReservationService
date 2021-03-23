package com.ty.order.controller;


import com.ty.common.utils.ApiResp;
import com.ty.common.utils.PageUtils;
import com.ty.order.entity.HotelOrderEntity;
import com.ty.order.service.HotelOrderService;
import com.ty.order.vo.HotelOrderVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author taleyoung
 * @since 2021-03-17
 */
@Api("酒店房间订单")
@RestController
@RequestMapping("/order/hotel-order")
public class HotelOrderController {
    @Autowired
    HotelOrderService hotelOrderService;

    @GetMapping("")
    public ApiResp queryPage(@RequestParam Map<String, Object> params){
        PageUtils list = hotelOrderService.queryPage(params);
        return ApiResp.retOK(list);
    }

    @PostMapping("")
    public ApiResp add(@RequestBody HotelOrderVo hotelOrderVo){
        hotelOrderService.add(hotelOrderVo);
        return ApiResp.retOK();
    }

    @PostMapping("success-payed")
    public ApiResp paySuccess(@RequestBody Integer orderId){
        hotelOrderService.successPayed(orderId);
        return ApiResp.retOK();
    }

    @PutMapping("/{id}")
    public  ApiResp update(@RequestParam("id") String id, @RequestBody HotelOrderEntity hotelOrderEntity){
        hotelOrderService.updateOrder(hotelOrderEntity);
        return ApiResp.retOK();
    }

}
