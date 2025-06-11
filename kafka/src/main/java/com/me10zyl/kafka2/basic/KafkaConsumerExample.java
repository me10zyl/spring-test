package com.me10zyl.kafka2.basic;

import org.apache.kafka.clients.consumer.*;

import java.time.Duration;
import java.util.*;

public class KafkaConsumerExample {
    public static void main(String[] args) {
        // 配置消费者
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.0.134:9092");
        props.put("group.id", "test-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest");

        // 创建消费者
        try (Consumer<String, String> consumer = new KafkaConsumer<>(props)) {
            // 订阅主题
            consumer.subscribe(Collections.singletonList("test"));

            // 消费消息
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Received message: key=%s, value=%s, topic=%s, partition=%d, offset=%d%n",
                            record.key(), record.value(), record.topic(), record.partition(), record.offset());
                }
            }
        }
    }
}