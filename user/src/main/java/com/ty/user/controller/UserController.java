package com.ty.user.controller;


import com.ty.common.utils.ApiResp;
import com.ty.common.utils.PageUtils;
import com.ty.user.entity.UserEntity;
import com.ty.user.service.UserService;
import com.ty.user.vo.LoginRes;
import com.ty.user.vo.LoginVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author taleyoung
 * @since 2021-03-24
 */
@Api("用户管理")
@RestController
@RequestMapping("/user/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public ApiResp getUserList(@RequestParam Map<String, Object> params){
        PageUtils list= userService.getUserList(params);
        return ApiResp.retOK(list);
    }

    @PostMapping("/login")
    public ApiResp login(@RequestBody LoginVo loginVo){
        LoginRes res = userService.login(loginVo);
        return ApiResp.retOK(res);
    }

    @PostMapping("")
    public ApiResp addNewUser(@RequestBody UserEntity userEntity){
        userService.register(userEntity);
        return ApiResp.retOK();
    }

    @PutMapping("")
    public ApiResp updateUserInfo(@RequestBody UserEntity userEntity){
        userService.updateUserInfo(userEntity);
        return ApiResp.retOK();
    }


}
