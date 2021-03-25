package com.ty.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ty.common.utils.PageUtils;
import com.ty.common.utils.Query;
import com.ty.user.entity.UserEntity;
import com.ty.user.dao.UserDao;
import com.ty.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ty.user.utils.JwtUtils;
import com.ty.user.vo.LoginRes;
import com.ty.user.vo.LoginVo;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taleyoung
 * @since 2021-03-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public void register(UserEntity userEntity) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodePassword);

        //检查手机号等是否唯一
        this.save(userEntity);
    }

    @Override
    public PageUtils getUserList(Map<String, Object> params) {

        IPage<UserEntity> page = this.page(new Query<UserEntity>().getPage(params), new QueryWrapper<>());
        return new PageUtils(page);
    }

    @Override
    public void updateUserInfo(UserEntity userEntity) {
        this.updateById(userEntity);
    }

    @Override
    public LoginRes login(LoginVo loginVo) {
        LoginRes loginRes = new LoginRes();
        String username = loginVo.getUsername();
        UserEntity entity = this.baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("username", username));
        if(entity == null){
            loginRes.setLoginSuccess(false);
            loginRes.setMsg("没有此用户");
            return loginRes;
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean matches = bCryptPasswordEncoder.matches(loginVo.getPassword(), entity.getPassword());
        if(!matches){
            loginRes.setLoginSuccess(false);
            loginRes.setMsg("密码错误或用户名不存在");
            return loginRes;
        }
        JwtUtils jwtUtils = new JwtUtils();
        String token = jwtUtils.getToken(entity);
//        Cookie cookie = new Cookie("jwt",token);
//        cookie.setPath("/");
//        cookie.setMaxAge(1222);
//        cookie.setDomain("localhost");
//        response.addCookie(cookie);
        entity.setPassword(null);
        loginRes.setLoginSuccess(true);
        loginRes.setUserInfo(entity);
        loginRes.setJwtToken(token);
        return loginRes;

    }
}
