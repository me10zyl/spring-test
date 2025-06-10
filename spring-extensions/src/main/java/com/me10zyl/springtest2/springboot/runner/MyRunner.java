package com.me10zyl.springtest2.springboot.runner;

import com.me10zyl.springtest2.exception.MyException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.me10zyl.springtest2.util.LogUtil.log;

@Component
public class MyRunner implements ApplicationRunner, CommandLineRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log("MyRunner#run");

//        throw new MyException("My1 custom error exception");
    }

    @Override
    public void run(String... args) throws Exception {
        log("MyRunner2#run");
    }
}
