package com.ty.user.vo;

import com.ty.user.entity.UserEntity;
import lombok.Data;

@Data
public class LoginRes {
    private String msg;
    private Boolean loginSuccess;
    private UserEntity userInfo;

    @Data
    public static class UserInfo{
        private String username;

        private String email;

        private String mobile;

        private Integer role;

        private Integer status;
    }

}
