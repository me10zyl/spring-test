package com.me10zyl.springtest2.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

@Component
public class ManyAware implements ApplicationContextAware, BeanNameAware, BeanFactoryAware, EnvironmentAware,
        EmbeddedValueResolverAware, ResourceLoaderAware, ApplicationEventPublisherAware, MessageSourceAware,
        NotificationPublisherAware {
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setBeanName(String name) {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {

    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {

    }

    @Override
    public void setEnvironment(Environment environment) {

    }

    @Override
    public void setMessageSource(MessageSource messageSource) {

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {

    }

    @Override
    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {

    }
}
