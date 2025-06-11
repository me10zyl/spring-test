package com.me10zyl.kafka2.bestpartice;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class KafkaConsumerPraticeExample {


    private  static final Set<String> handledMessageIds = ConcurrentHashMap.newKeySet();

    public static void main(String[] args) {
        // 配置消费者
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.0.134:9092");
        props.put("group.id", "test-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest");
        props.put("enable.auto.commit", "false");

        // 创建消费者
        try (Consumer<String, String> consumer = new KafkaConsumer<>(props)) {
            // 订阅主题
            consumer.subscribe(Collections.singletonList("multi-partition"));

            // 消费消息
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                List<String> handledMessages = new ArrayList<>();
                for (ConsumerRecord<String, String> record : records) {
                    Message message;
                    try {
                         message = JSONObject.parseObject(record.value(), Message.class);
                        if(message.getContent() == null || message.getUuid() == null){
                            System.out.println("解析失败");
                            continue;
                        }
                        if(handledMessageIds.contains(message.getUuid())){
                            System.out.println("重复消息");
                            continue;
                        }

                    }catch (Exception e){
                        System.out.println("解析失败");
                        continue;
                    }
                    String content = message.getContent();
                    //do something
                    System.out.printf("Received message: key=%s, value=%s, topic=%s, partition=%d, offset=%d%n",
                            record.key(), content, record.topic(), record.partition(), record.offset());
                    handledMessages.add(message.getUuid());
                }
                if(records.count() > 0){
                    handledMessageIds.addAll(handledMessages);
                    consumer.commitSync();
                }
            }
        }
    }
}