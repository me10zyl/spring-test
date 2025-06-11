package com.me10zyl.kafka2.basic;

import org.apache.kafka.clients.producer.*;
import java.util.Properties;
import java.util.concurrent.Future;

public class KafkaProducerExample {
    public static void main(String[] args) {
        // 配置生产者
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.0.134:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 创建生产者
        try (Producer<String, String> producer = new KafkaProducer<>(props)) {
            // 发送消息
            for (int i = 0; i < 10; i++) {
                String message = "Message " + i;
                ProducerRecord<String, String> record = new ProducerRecord<>("test", "key-" + i, message);
                Future<RecordMetadata> send = producer.send(record, (metadata, exception) -> {
                    if (exception == null) {
                        System.out.printf("Sent message: %s, Topic: %s, Partition: %d, Offset: %d%n",
                                message, metadata.topic(), metadata.partition(), metadata.offset());
                    } else {
                        exception.printStackTrace();
                    }
                });
                try {
                    RecordMetadata metadata = send.get();
                    System.out.printf("Sent to topic=%s, partition=%d, offset=%d%n", metadata.topic(), metadata.partition(), metadata.offset());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}