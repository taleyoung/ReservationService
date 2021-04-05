package com.ty.user.service;

import com.ty.common.to.OptLogTo;
import com.ty.common.utils.PageUtils;
import com.ty.user.entity.OptLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taleyoung
 * @since 2021-04-05
 */
public interface OptLogService extends IService<OptLogEntity> {

    void reportLog(OptLogTo optLogTo);

    PageUtils queryPage(Map<String, Object> params);
}
