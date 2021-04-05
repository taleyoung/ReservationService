package com.ty.user.controller;


import com.ty.common.to.OptLogTo;
import com.ty.common.utils.ApiResp;
import com.ty.user.service.OptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
}
