package com.ty.user.controller;


import com.ty.common.to.OptLogTo;
import com.ty.common.utils.ApiResp;
import com.ty.common.utils.PageUtils;
import com.ty.user.service.OptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author taleyoung
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/user/opt-log")
public class OptLogController {
    @Autowired
    OptLogService optLogService;

    @PostMapping("/report")
    public ApiResp reportLog(@RequestBody OptLogTo optLogTo){
        optLogService.reportLog(optLogTo);
        return ApiResp.retOK();
    }

    @GetMapping("")
    public ApiResp getList(@RequestParam Map<String, Object> params){
        PageUtils list = optLogService.queryPage(params);
        return ApiResp.retOK(list);
    }
}
