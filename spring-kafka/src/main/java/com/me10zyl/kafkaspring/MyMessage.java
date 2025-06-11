package com.me10zyl.kafkaspring;

import lombok.Data;

@Data
public class MyMessage {
    private String id;
    private String content;
}