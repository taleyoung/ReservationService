package com.ty.user.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class MyMqConfig {

    @Bean
    public Queue userLogQueue(){
        return new Queue("user.log.queue", true, false, false);
    }

    @Bean
    public Exchange userEventExchange(){
        return new TopicExchange("user-event-exchange", true, false);
    }

    @Bean
    public Binding userLogBinding(){
        return new Binding("user.log.queue",
                Binding.DestinationType.QUEUE,
                "user-event-exchange",
                "user.create.log",
                null);
    }
}
