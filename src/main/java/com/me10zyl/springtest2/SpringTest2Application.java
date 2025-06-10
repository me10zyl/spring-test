package com.me10zyl.springtest2;

import com.me10zyl.springtest2.exception.MyException;
import com.me10zyl.springtest2.spring.postprocessor.ApplicationContextInitializerTest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SpringTest2Application {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringTest2Application.class);
        app.addInitializers(new ApplicationContextInitializerTest());
        app.run(args);

    }

    @PostConstruct
    public void init() {
        System.out.println("Environment: " + environment.getProperty("app.name"));
    }

}
