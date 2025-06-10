package com.me10zyl.springtest2.spring.applicationlistener;

import com.me10zyl.springtest2.spring.factorybean.MyBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import static com.me10zyl.springtest2.util.LogUtil.log;

@Slf4j
@Component
public class ApplicationListenerContextTest implements ApplicationListener<ApplicationContextEvent> {
    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
        log("applicationListener#" + event.getClass().getSimpleName());
        if (event instanceof ContextRefreshedEvent) {
            MyBean bean = event.getApplicationContext().getBean(MyBean.class);
            log("bean#" + bean);
        }
    }
}
