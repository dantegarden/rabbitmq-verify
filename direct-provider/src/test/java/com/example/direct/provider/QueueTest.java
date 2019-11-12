package com.example.direct.provider;

import com.example.direct.provider.mq.InfoSender;
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
@SpringBootTest(classes = DirectProviderApplication.class)
public class QueueTest {
    @Autowired
    private InfoSender sender;

    @Test
    public void test() throws InterruptedException {
        Random r = new Random();
        while (true){
            Thread.sleep(1000);
            this.sender.send("hello rabbitmq " + r.nextInt(5000));
        }
    }
}
