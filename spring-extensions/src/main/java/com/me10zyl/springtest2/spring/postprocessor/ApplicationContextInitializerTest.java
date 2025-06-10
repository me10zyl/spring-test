package com.me10zyl.springtest2.spring.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;

import static com.me10zyl.springtest2.util.LogUtil.log;

@Slf4j
public class ApplicationContextInitializerTest implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext context) {
        ConfigurableEnvironment env = context.getEnvironment();
        Properties props = new Properties();
        // 假设从外部文件加载
        props.put("app.name", "MyApp");
        env.getPropertySources().addFirst(new PropertiesPropertySource("dynamic", props));
        log("ApplicationContextInitializerTest#initialize");
    }
}
