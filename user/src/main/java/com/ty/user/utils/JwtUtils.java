package com.ty.user.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ty.user.entity.UserEntity;

public class JwtUtils {
    public String getToken(UserEntity user) {
        String token="";
        token= JWT.create()
                .withClaim("userId",user.getId())
                .withClaim("userName",user.getUsername())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
