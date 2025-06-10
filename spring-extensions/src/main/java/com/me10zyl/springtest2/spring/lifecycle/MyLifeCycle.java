package com.me10zyl.springtest2.spring.lifecycle;

import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;

import static com.me10zyl.springtest2.util.LogUtil.log;

@Component
public class MyLifeCycle implements Lifecycle {
    @Override
    public void start() {
        log("myLifecycle#start");
    }

    @Override
    public void stop() {
        log("myLifecycle#stop");
    }

    @Override
    public boolean isRunning() {
        log("myLifecycle#isRunning");
        return true;
    }
}
