package com.example.topic.provider.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: lij
 * @create: 2019-11-12 18:19
 */
@Component
public class UserSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${mq.config.exchange}")
    private String exchange;

    public void send(String msg){
        //参数：交换器名称，路由键，消息
        formatAndSend(msg);
    }

    private void formatAndSend(String msg){
        List<String> levels = Arrays.asList("info", "debug", "trace", "warn", "error");
        levels.forEach(lv ->{
            String routkingKey = "user.log." + lv ;
            amqpTemplate.convertAndSend(exchange, routkingKey, routkingKey+":"+ msg);
        });
    }
}
