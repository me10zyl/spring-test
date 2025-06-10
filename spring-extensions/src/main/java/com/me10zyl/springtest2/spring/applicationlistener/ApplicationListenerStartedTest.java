package com.me10zyl.springtest2.spring.applicationlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import static com.me10zyl.springtest2.util.LogUtil.log;

@Slf4j
@Component
public class ApplicationListenerStartedTest  implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log("applicationListener#started");
    }
}
