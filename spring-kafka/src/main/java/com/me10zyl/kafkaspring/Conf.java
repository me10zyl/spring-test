package com.me10zyl.kafkaspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;

@Configuration
public class Conf {
    @Bean
    public KafkaListenerErrorHandler customErrorHandler() {
        return (message, exception) -> {
            System.err.println("Error processing message: " + message.getPayload());
            return null; // 跳过错误消息
        };
    }
}
