package com.example.fanout.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FanoutConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FanoutConsumerApplication.class, args);
    }

}
