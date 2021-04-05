package com.ty.order.feign;

import com.ty.common.to.OptLogTo;
import com.ty.common.utils.ApiResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("user")
public interface UserFeignService {
    @PostMapping("/user/opt-log/report")
    ApiResp reportLog(@RequestBody OptLogTo optLogTo);
}
