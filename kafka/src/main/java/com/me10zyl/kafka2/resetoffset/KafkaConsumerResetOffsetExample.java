package com.me10zyl.kafka2.resetoffset;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;

@Slf4j
public class KafkaConsumerResetOffsetExample {
    private static final int MAX_ASSIGNMENT_RETRIES = 5;
    private static final long POLL_TIMEOUT_MS = 5000; // 增加超时时间
    private static final String TOPIC = "test";

    public static void main(String[] args) {
        // 配置消费者
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.0.134:9092");
        props.put("group.id", "test-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "100");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        // 创建消费者
        try (Consumer<String, String> consumer = new KafkaConsumer<>(props)) {
            // 订阅主题
            consumer.subscribe(Collections.singletonList(TOPIC));

            log.info("Subscribed to topic: {}", TOPIC);

            Set<TopicPartition> partitions = null;
            int retries = 0;
            while (retries < MAX_ASSIGNMENT_RETRIES) {
                consumer.poll(Duration.ofMillis(POLL_TIMEOUT_MS));
                partitions = consumer.assignment();
                if (!partitions.isEmpty()) {
                    log.info("Assigned partitions: {}", partitions);
                    break;
                }
                log.warn("No partitions assigned on attempt {}/{}", retries + 1, MAX_ASSIGNMENT_RETRIES);
                retries++;
                Thread.sleep(1000); // 等待 1 秒后重试
            }

            // 获取分配的分区

            if (partitions.isEmpty()) {
                log.warn("No partitions assigned for topic: {}", TOPIC);
                return;
            }
            log.info("Assigned partitions: {}", partitions);

            // 重置偏移量到最早
            consumer.seekToBeginning(partitions);
            log.info("Reset offsets to beginning for partitions: {}", partitions);

            // 验证偏移量位置
            for (TopicPartition partition : partitions) {
                long offset = consumer.position(partition);
                log.info("Current offset for {}: {}", partition, offset);
            }

            // 消费消息
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(POLL_TIMEOUT_MS));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Received message: key=%s, value=%s, topic=%s, partition=%d, offset=%d%n",
                            record.key(), record.value(), record.topic(), record.partition(), record.offset());
                }
                consumer.commitAsync();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}