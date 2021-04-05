package com.ty.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ty.common.to.OptLogTo;
import com.ty.common.utils.PageUtils;
import com.ty.common.utils.Query;
import com.ty.user.entity.OptLogEntity;
import com.ty.user.dao.OptLogDao;
import com.ty.user.entity.UserEntity;
import com.ty.user.service.OptLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taleyoung
 * @since 2021-04-05
 */
@Service
public class OptLogServiceImpl extends ServiceImpl<OptLogDao, OptLogEntity> implements OptLogService {

    @Override
    public void reportLog(OptLogTo optLogTo) {
        OptLogEntity optLogEntity = new OptLogEntity();
        BeanUtils.copyProperties(optLogTo,optLogEntity);
        this.save(optLogEntity);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OptLogEntity> page = this.page(new Query<OptLogEntity>().getPage(params),
                new QueryWrapper<OptLogEntity>().orderByDesc("opt_time"));
        return new PageUtils(page);
    }
}
