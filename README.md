# rabbitmq-verify
验证rabbitmq的基本功能。

三种交换器
- direct-provider  ***发布订阅模式***
- direct-consumer 
- topic-provider   ***规则匹配模式***
- topic-consumer 
- fanout-provider  ***广播模式***
- fanout-consumer 
---
#### rabbitmq消息持久化处理
>@Queue，如果设置了autoDelete=true，即表示创建临时队列，当所有消费者断开连接时，此队列会被删除。这时生产者发送的消息无处存储，会发生消息丢失。

>@Exchange，如果设置了autoDelete=true，表示当所有绑定的队列都不工作时，此交换器自动删除。
把autoDelete设置成false，即可启动消息持久化。

#### rabbitmq的ack应答机制
##### 1.什么是消息确认ACK? 
如果在处理消息的过程中，消费者的服务器在处理消息时出现异常，那可能这条正在处理的消息就没有完成消息消费，数据就会丢失。为了确保数据不会丢失，RabbitMQ支持消息确认：ACK。
##### 2.ACK的消息确认机制
ACK机制是消费者从RabbitMQ收到消息并处理完成后，反馈给RabbitMQ，RabbitMQ收到反馈后才将此消息从队列中删除。
###### 2.1.如果一个消费者在处理消息时出现了网络不稳定、服务器异常等现象，那么就不会有ACK反馈，RabbitMQ会认为这个消息没有正常消费，会将消息重新放入队列中。
###### 2.2.如果在集群的情况下: RabbitMQ会立即将这个消息推送给在线的其他消费者。这种机制保证了在消费者服务端故障的时候，不丢失任何消息和任务。
###### 2.3.消息永远不会从RabbitMQ中删除: 只有当消费者正确发送ACK反馈, RabbitMQ确认收到后，消息才会从RabbitMQ服务器的数据中删除。
###### 2.4.消息的ACK确认机制默认是打开的。
##### 3.ACK机制的开发注意事项
如果忘记了ACK,那么后果很严重。当Consumer退出时，Message会一直重新分发，消费者又瘫痪。然后RabbitMQ会占用越来越多的内存，由于RabbitMQ会长时间运行，因此这个“内存泄漏”是致命的。
###### 解决方法：
###### 1.在消费者的逻辑中加入try/catch
###### 2.在配置文件中，限制统一消息的重试次数。当达到重试次数还不能消费的话，mq就不会再重复发送该消息了。修改consumer的配置文件，增加重试设置：
```
spring:
  application:
    name: verify-fanout-consumer
  rabbitmq:
    username: guest
    password: guest
    host: 192.168.3.200
    port: 5672
    listener:
      direct:
        retry:
          enabled: true #开启重试
          max-attempts: 3 #默认就是3次
```