package com.me10zyl.kafka2.bestpartice;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.Future;

public class KafkaProducerBestPracticeExample {
    public static void main(String[] args) {
        // 配置生产者
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.0.134:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("enable.idempotenc", "true");
        props.put("acks", "all");

        // 创建生产者
        try (Producer<String, String> producer = new KafkaProducer<>(props)) {
            // 发送消息
            for (int i = 0; i < 10; i++) {
                Message message1 = new Message();
                message1.setUuid(UUID.randomUUID().toString().replace("-", ""));
                message1.setContent("Message " + i);
                String message = JSONObject.toJSONString(message1);
                ProducerRecord<String, String> record = new ProducerRecord<>("multi-partition", "key-" + i, message);
                producer.send(record, (metadata, exception) -> {
                    if (exception == null) {
                        System.out.printf("Sent message: %s, Topic: %s, Partition: %d, Offset: %d%n",
                                message, metadata.topic(), metadata.partition(), metadata.offset());
                    } else {
                        exception.printStackTrace();
                    }
                });
            }
        }
    }
}