package com.me10zyl.kafkaspring;

import com.alibaba.fastjson.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    @KafkaListener(topics = "test", groupId = "my-group", errorHandler = "customErrorHandler")
    public void consume(String message) {
        System.out.println("Received message: " + message);
        if (JSONObject.parseObject(message, MyMessage.class).getContent().equals("message5")) {
            throw new RuntimeException("Simulated error");
        }
    }
}
