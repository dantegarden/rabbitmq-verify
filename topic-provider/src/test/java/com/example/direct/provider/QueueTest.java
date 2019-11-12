package com.example.direct.provider;

import com.example.topic.provider.TopicProviderApplication;
import com.example.topic.provider.mq.OrderSender;
import com.example.topic.provider.mq.ProductSender;
import com.example.topic.provider.mq.UserSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * @description:
 * @author: lij
 * @create: 2019-11-12 18:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TopicProviderApplication.class)
public class QueueTest {
    @Autowired
    private UserSender userSender;
    @Autowired
    private ProductSender productSender;
    @Autowired
    private OrderSender orderSender;

    @Test
    public void test() throws InterruptedException {
        Random r = new Random();
        while (true){
            Thread.sleep(1000);
            this.userSender.send("hello rabbitmq " + r.nextInt(5000));
            this.productSender.send("hello rabbitmq " + r.nextInt(5000));
            this.orderSender.send("hello rabbitmq " + r.nextInt(5000));
        }
    }
}
