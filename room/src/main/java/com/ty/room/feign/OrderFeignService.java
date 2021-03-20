package com.ty.room.feign;

import com.ty.common.utils.ApiResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("order")
public interface OrderFeignService {
    @GetMapping("/order/hotel-check-in/wareWithDate")
    ApiResp<Integer> getWareWithDate(@RequestParam("roomTypeId") Integer id, @RequestParam("date") String date);
}
