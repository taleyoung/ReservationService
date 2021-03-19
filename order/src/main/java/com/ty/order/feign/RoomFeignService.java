package com.ty.order.feign;

import com.ty.common.to.HotelRoomTo;
import com.ty.common.utils.ApiResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("room")
public interface RoomFeignService {

    @GetMapping("/room/hotel-room")
    public ApiResp<List<HotelRoomTo>> getListByTypeId(@RequestParam Integer roomTypeId);
}
