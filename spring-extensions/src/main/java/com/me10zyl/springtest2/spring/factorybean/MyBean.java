package com.me10zyl.springtest2.spring.factorybean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import static com.me10zyl.springtest2.util.LogUtil.log;

@Slf4j
public class MyBean implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        log("myBean#destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log("myBean#afterPropertiesSet");
    }

    @PreDestroy
    public void destory2(){
        log("myBean#destory2");
    }

    @PostConstruct
    public void init(){
        log("myBean#init");
    }
}
