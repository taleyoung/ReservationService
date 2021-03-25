package com.ty.user.vo;

import com.ty.user.entity.UserEntity;
import lombok.Data;

@Data
public class LoginRes {
    private String msg;
    private Boolean loginSuccess;
    private String jwtToken;
    private UserEntity userInfo;
}
