package com.me10zyl.springtest2.springboot.environment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import static com.me10zyl.springtest2.util.LogUtil.log;

@Slf4j
public class EnvironmentPostProcessorTest implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log("EnvironmentPostProcessorTest#postProcessEnvironment");
    }
}
