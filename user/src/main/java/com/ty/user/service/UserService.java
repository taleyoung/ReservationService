package com.ty.user.service;

import com.ty.common.utils.PageUtils;
import com.ty.user.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ty.user.vo.LoginRes;
import com.ty.user.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taleyoung
 * @since 2021-03-24
 */
public interface UserService extends IService<UserEntity> {

    void register(UserEntity userEntity);

    PageUtils getUserList(Map<String, Object> params);

    void updateUserInfo(UserEntity userEntity);

    LoginRes login(LoginVo loginVo);
}
