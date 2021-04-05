package com.ty.order.controller;


import com.ty.common.utils.ApiResp;
import com.ty.common.utils.PageUtils;
import com.ty.order.entity.HotelOrderEntity;
import com.ty.order.service.HotelOrderService;
import com.ty.order.vo.HotelOrderVo;
import io.swagger.annotations.Api;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("")
    public ApiResp queryPage(@RequestParam Map<String, Object> params){
        PageUtils list = hotelOrderService.queryPage(params);
        return ApiResp.retOK(list);
    }

    @PostMapping("")
    public ApiResp createOrder(@RequestBody HotelOrderVo hotelOrderVo){
        hotelOrderService.createOrder(hotelOrderVo);
        return ApiResp.retOK();
    }

    @PostMapping("/testPayAndSuccess")
    public ApiResp testPayAndSuccess(@RequestBody HotelOrderVo hotelOrderVo){
        hotelOrderService.testPayAndSuccess(hotelOrderVo);
        return ApiResp.retOK();
    }

    @PostMapping("/testPayAndCancel")
    public ApiResp testOrderQueue(@RequestBody HotelOrderVo hotelOrderVo){
        hotelOrderService.testPayAndCancel(hotelOrderVo);
        return ApiResp.retOK();

    }

    @PostMapping("success-payed")
    public ApiResp paySuccess(@RequestBody Integer orderId){
        hotelOrderService.handlePayResult(true, orderId);
        return ApiResp.retOK();
    }

    @PutMapping("/{id}")
    public  ApiResp update(@RequestParam("id") String id, @RequestBody HotelOrderEntity hotelOrderEntity){
        hotelOrderService.updateOrder(hotelOrderEntity);
        return ApiResp.retOK();
    }

}
