package com.ty.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ty.common.enume.CheckInEnum;
import com.ty.common.to.HotelRoomTo;
import com.ty.common.utils.ApiResp;
import com.ty.common.utils.PageUtils;
import com.ty.common.utils.Query;
import com.ty.order.entity.HotelCheckInEntity;
import com.ty.order.dao.HotelCheckInDao;
import com.ty.order.feign.RoomFeignService;
import com.ty.order.service.HotelCheckInService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ty.order.vo.HotelOrderVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taleyoung
 * @since 2021-03-17
 */
@Service
public class HotelCheckInServiceImpl extends ServiceImpl<HotelCheckInDao, HotelCheckInEntity> implements HotelCheckInService {

    @Autowired
    RoomFeignService roomFeignService;

    @Override
    public List<HotelCheckInEntity> getRecordsByTypeAndDate(Integer hotelRoomTypeId, Date date) {
//        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        QueryWrapper<HotelCheckInEntity> wrapper = new QueryWrapper<HotelCheckInEntity>()
                .eq("hotel_room_type_id", hotelRoomTypeId)
                .eq("date",date);
        List<HotelCheckInEntity> list = this.list(wrapper);
        return list;
    }

    public List<HotelRoomTo> getFreeHotelRooms(Integer hotelRoomTypeId, Date date){
        List<HotelCheckInEntity> recordsByTypeAndDate = this.getRecordsByTypeAndDate(hotelRoomTypeId, date);
        List<Integer> reservedRoomIds = recordsByTypeAndDate.stream().map(HotelCheckInEntity::getHotelRoomId).collect(Collectors.toList());

        HotelRoomTo hotelRoomTo = new HotelRoomTo();
        ApiResp<List<HotelRoomTo>> res = roomFeignService.getListByTypeId(hotelRoomTypeId);
        if(res.getCode() != 0){
            return null;
        }
        List<HotelRoomTo> allHotelRooms = res.getData();
        List<HotelRoomTo> freeHotelRooms = new ArrayList<>();
        allHotelRooms.stream().forEach(item->{
            if(!reservedRoomIds.contains(item.getId())){
                freeHotelRooms.add(item);
            }
        });
        return freeHotelRooms;
    }

    @Override
    public HotelRoomTo newFreeRoomByTypeAndDate(Integer hotelRoomTypeId, Date date) {
        List<HotelRoomTo> freeHotelRooms = this.getFreeHotelRooms(hotelRoomTypeId, date);
        if(freeHotelRooms.size() < 1){
            return null;
        }
        //分配空闲房间的策略
        return freeHotelRooms.get(0);
    }

    @Override
    public Integer getWareWithDate(Integer hotelRoomTypeId, Date date) {
        List<HotelRoomTo> freeHotelRooms = this.getFreeHotelRooms(hotelRoomTypeId, date);
        if(freeHotelRooms.size() < 1){
            return 0;
        }
        return freeHotelRooms.size();
    }

    @Override
    public void updateStatus(Integer orderId, Integer code) {
        QueryWrapper<HotelCheckInEntity> wrapper = new QueryWrapper<HotelCheckInEntity>().eq("order_id", orderId);
        List<HotelCheckInEntity> list = this.list(wrapper);
        List<HotelCheckInEntity> collect = list.stream().map(item -> {
            item.setStatus(code);
            return item;
        }).collect(Collectors.toList());
        this.updateBatchById(collect);
    }




    @Override
    public void add(Integer orderId, HotelOrderVo hotelOrderVo) {
        Integer hotelRoomTypeId = hotelOrderVo.getHotelRoomTypeId();
        HotelCheckInEntity hotelCheckInEntity = new HotelCheckInEntity();
        hotelCheckInEntity.setOrderId(orderId);
        hotelCheckInEntity.setHotelRoomTypeId(hotelRoomTypeId);
        hotelCheckInEntity.setHotelName(hotelOrderVo.getHotelName());
        hotelCheckInEntity.setHotelRoomTypeName(hotelOrderVo.getHotelRoomTypeName());
        hotelCheckInEntity.setUserId(hotelOrderVo.getUserId());
        hotelCheckInEntity.setUserName(hotelOrderVo.getUserName());
        hotelCheckInEntity.setPersonName(hotelOrderVo.getPersonName());
        hotelCheckInEntity.setPersonIdNumber(hotelOrderVo.getPersonIdNumber());
        hotelCheckInEntity.setExpectedTime(hotelOrderVo.getExpectedTime());
        hotelCheckInEntity.setStatus(CheckInEnum.WAIT_PAYED.getCode());
        //date  hotel_room_id / num
        List<Date> dateList = this.getBetweenDates(hotelOrderVo.getStartDate(), hotelOrderVo.getEndDate());
        dateList.stream().forEach(date->{
            HotelCheckInEntity temp = new HotelCheckInEntity();
            BeanUtils.copyProperties(hotelCheckInEntity, temp);
            temp.setDate(date);
            HotelRoomTo hotelRoomTo = this.newFreeRoomByTypeAndDate(hotelRoomTypeId, date);
            temp.setHotelRoomId(hotelRoomTo.getId());
            temp.setHotelRoomNum(hotelRoomTo.getRoomNum());
            this.save(temp);
        });
        return;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HotelCheckInEntity> page = this.page(
                new Query<HotelCheckInEntity>().getPage(params),
                new QueryWrapper<HotelCheckInEntity>().orderByDesc("date"));
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByUserId(Map<String, Object> params, Integer userId) {
        IPage<HotelCheckInEntity> page = this.page(
                new Query<HotelCheckInEntity>().getPage(params),
                new QueryWrapper<HotelCheckInEntity>().eq("user_id",userId).orderByDesc("date"));
        return new PageUtils(page);
    }



    private List<Date> getBetweenDates(Date begin, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(begin);
            /* Calendar tempEnd = Calendar.getInstance();
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            tempEnd.setTime(end);
            while (tempStart.before(tempEnd)) {
                result.add(tempStart.getTime());
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }*/

            //含头不含尾
        while(begin.getTime()<end.getTime()){
            result.add( tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            begin = tempStart.getTime();
        }
        return result;
    }
}
