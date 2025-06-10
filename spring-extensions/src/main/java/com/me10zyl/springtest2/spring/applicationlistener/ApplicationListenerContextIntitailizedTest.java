package com.me10zyl.springtest2.spring.applicationlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import static com.me10zyl.springtest2.util.LogUtil.log;

@Component
@Slf4j
public class ApplicationListenerContextIntitailizedTest implements ApplicationListener<ApplicationContextInitializedEvent> {
    @Override
    public void onApplicationEvent(ApplicationContextInitializedEvent event) {
        log("applicationListener#contextInitialized");
    }
}
