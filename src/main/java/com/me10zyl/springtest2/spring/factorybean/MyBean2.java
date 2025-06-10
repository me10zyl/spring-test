package com.me10zyl.springtest2.spring.factorybean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import static com.me10zyl.springtest2.util.LogUtil.log;

@Component
public class MyBean2 implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        log("myBean2#destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log("myBean2#afterPropertiesSet");
    }

    @PreDestroy
    public void destory2(){
        log("myBean2#destory2");
    }

    @PostConstruct
    public void init(){
        log("myBean2#init");
    }
}
