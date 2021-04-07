package com.ty.user.listeners;

import com.rabbitmq.client.Channel;
import com.ty.common.to.OptLogTo;
import com.ty.user.service.OptLogService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserLogListener {

    @Autowired
    OptLogService optLogService;

    @RabbitListener(queues = "user.log.queue")
    public void listener(OptLogTo optLogTo, Channel channel, Message message) throws IOException {
        try{
            System.out.println("监听到日志记录"+ optLogTo);
            optLogService.reportLog(optLogTo);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}
