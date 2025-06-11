package com.me10zyl.kafkaspring;

import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.MessageUtil;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class Producer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send("test", message);
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 10; i++) {
            MyMessage message = new MyMessage();
            message.setId(UUID.randomUUID().toString().replace("-", ""));
            message.setContent("message" + i);

            sendMessage("test", JSONObject.toJSONString(message));
        }
    }
}
