package com.me10zyl.springtest2.spring.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

import static com.me10zyl.springtest2.util.LogUtil.log;

@Slf4j
@Component
public class SmartInitializingSingletonTest implements SmartInitializingSingleton {
    @Override
    public void afterSingletonsInstantiated() {
        log("SmartInitializingSingletonTest#afterSingletonsInstantiated");
    }
}
