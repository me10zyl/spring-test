package com.me10zyl.springtest2.spring.lifecycle;

import org.springframework.context.Lifecycle;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import static com.me10zyl.springtest2.util.LogUtil.log;

@Component
public class SmartLifeCycleTest implements SmartLifecycle {
    @Override
    public void start() {
        log("SmartLifeCycleTest#start");
    }

    @Override
    public void stop() {
        log("SmartLifeCycleTest#stop");
    }

    @Override
    public boolean isRunning() {
        log("SmartLifeCycleTest#isRunning");
        return true;
    }
}
