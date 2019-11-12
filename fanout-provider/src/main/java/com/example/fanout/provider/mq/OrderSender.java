package com.example.fanout.provider.mq;

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
public class OrderSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${mq.config.exchange}")
    private String exchange;

    public void send(String msg){
        //参数：交换器名称，路由键，消息
        //广播模式不需要路由键，给空串即可
        amqpTemplate.convertAndSend(exchange, "", msg);
    }


}
