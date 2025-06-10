package com.me10zyl.springtest2.springboot.runlisteners;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.me10zyl.springtest2.util.LogUtil.log;


public class SpringApplicationRunListenerTest implements SpringApplicationRunListener {

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        log("runListener#starting");
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        log("runListener#environmentPrepared");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log("runListener#contextPrepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log("runListener#contextLoaded");
    }

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        log("runListener#started");
    }

    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        log("runListener#ready");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        log("runListener#failed");
    }
}
