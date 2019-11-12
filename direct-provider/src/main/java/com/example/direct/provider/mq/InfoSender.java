package com.example.direct.provider.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: lij
 * @create: 2019-11-12 18:19
 */
@Component
public class InfoSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${mq.config.exchange}")
    private String exchange;
    @Value("${mq.config.queue.info.routing-key}")
    private String routingKey;

    public void send(String msg){
        //参数：交换器名称，路由键，消息
        amqpTemplate.convertAndSend(exchange, routingKey, msg);
    }
}
