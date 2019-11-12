package com.example.topic.consumer.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 消息接收者
 * @RabbitListener bindings :绑定队列
 * @QueueBinding value :绑定队列的名称
 *               exchange :配置交换器
 * @Queue value :配置队列名称
 *        autoDelete: 是否是一个可删除的临时队列
 * @Exchange value :为交换器起个名称
 *           type :指定具体的交换器类型
 */
@RabbitListener(
      bindings = @QueueBinding(
              value = @Queue(value = "${mq.config.queue.error.queue-name}", autoDelete = "true"),
              exchange = @Exchange(value = "${mq.config.exchange}", type = ExchangeTypes.TOPIC),
              key = "*.log.error"
      )
) //表示类中某个方法负责处理消息
@Component
@Slf4j
public class ErrorReceiver {

    @RabbitHandler
    public void process(String msg){
        log.info(msg);
    }
}
