package com.ty.room.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ty.common.utils.Constant;
import com.ty.common.utils.PageUtils;
import com.ty.common.utils.Query;
import com.ty.room.dao.HotelDao;
import com.ty.room.entity.HotelEntity;
import com.ty.room.entity.HotelRoomTypeEntity;
import com.ty.room.service.HotelRoomTypeService;
import com.ty.room.service.HotelService;
import com.ty.room.vo.HotelWithRoomTypeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import springfox.documentation.spring.web.json.Json;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("HotelService")
public class HotelServiceImpl extends ServiceImpl<HotelDao, HotelEntity> implements HotelService {

    @Autowired
    HotelRoomTypeService hotelRoomTypeService;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage page = this.page(new Query<HotelEntity>().getPage(params), new QueryWrapper<>());
        return new PageUtils(page);
    }

    public PageUtils getList(Map<String, Object> params){
//        String hotelListRedis = stringRedisTemplate.opsForValue().get("hotel_list");
//        if(!StringUtils.isEmpty(hotelListRedis)){
//            List<HotelEntity> data = JSON.parseArray(hotelListRedis.toString(), HotelEntity.class);
//            return data;
//        }
//        stringRedisTemplate.opsForValue().set("hotel_list", JSON.toJSONString(list));
        int pageNum = 0;
        int limit = 0;
        if(params.get(Constant.PAGE) != null){
            pageNum = Integer.parseInt(params.get(Constant.PAGE).toString());
        }
        if(params.get(Constant.LIMIT) != null){
            limit = Integer.parseInt((String)params.get(Constant.LIMIT));
        }
        Boolean hasHotelList = redisTemplate.hasKey("hotel_list");
        if(hasHotelList){
            int left = (pageNum-1) * limit;
            int right = left + limit;
            List<HotelEntity> redisHotelList = redisTemplate.opsForList().range("hotel_list", left, right);
            Long redisHotelListTotal = redisTemplate.opsForList().size("hotel_list");
            return new PageUtils(redisHotelList, redisHotelListTotal.intValue(), limit, pageNum);
        }
        List<HotelEntity> list = this.list(new QueryWrapper<>());
        redisTemplate.opsForList().rightPushAll("hotel_list", list);
        IPage page = this.page(new Query<HotelEntity>().getPage(params), new QueryWrapper<>());
        return new PageUtils(page);
    }

    @Override
//    @CacheEvict(value = "hotel_list", allEntries = true)
    public void addHotel(HotelEntity hotelEntity) {
        this.save(hotelEntity);
        redisTemplate.delete("hotel_list");
    }

    @Override
//    @CacheEvict(value = "hotel_lisit", allEntries = true)
    public void updateHotel(Long id, HotelEntity hotelEntity) {
        this.updateById(hotelEntity);
        redisTemplate.delete("hotel_list");
    }

    @Override
    public void deleteHotel(Long id) {
        this.removeById(id);
        redisTemplate.delete("hotel_list");
    }

    @Override
    public HotelEntity getHotelById(Long id) {
        return this.getById(id);
    }

    @Override
    public HotelWithRoomTypeVo getHotelWithRoomById(Long id, Date date) {
        HotelWithRoomTypeVo hotelWithRoomTypeVo = new HotelWithRoomTypeVo();
        List<HotelRoomTypeEntity> hotelRoomList = hotelRoomTypeService.getRoomByHotelIdAndDate(id, date);
        HotelEntity hotelById = this.getHotelById(id);
        BeanUtils.copyProperties(hotelById, hotelWithRoomTypeVo);
        hotelWithRoomTypeVo.setRooms(hotelRoomList);
        return hotelWithRoomTypeVo;
    }
}
