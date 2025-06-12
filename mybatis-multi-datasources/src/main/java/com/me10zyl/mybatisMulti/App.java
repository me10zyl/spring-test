package com.me10zyl.mybatisMulti;

import com.me10zyl.mybatisMulti.service.MyService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class App {
    @Autowired
    private MyService myService;
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostConstruct
    public void init(){
        myService.submitOrder(1, new BigDecimal(100));
    }
}
