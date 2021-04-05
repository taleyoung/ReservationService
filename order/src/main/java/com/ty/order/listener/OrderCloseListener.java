package com.ty.order.listener;

import com.rabbitmq.client.Channel;
import com.ty.order.entity.HotelOrderEntity;
import com.ty.order.service.HotelOrderService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OrderCloseListener {
    @Autowired
    HotelOrderService hotelOrderService;

    @RabbitListener(queues = "order.release.order.queue")
    public void listener(HotelOrderEntity hotelOrderEntity, Channel channel, Message message) throws IOException {
        try{
            System.out.println("收到过期的订单信息"+ hotelOrderEntity);
            Integer orderId = hotelOrderEntity.getId();
            hotelOrderService.handlePayResult(false, orderId);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception e){
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
