package com.me10zyl.springtest2.springboot.autoconfigure;

import com.me10zyl.springtest2.spring.factorybean.MyBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(MyBean3.class)
@ConditionalOnMissingBean(MyBean3.class)
public class MyAutoConfiguration {
    @Bean
    public MyBean3 mybean3(){
        return new MyBean3();
    }

}
