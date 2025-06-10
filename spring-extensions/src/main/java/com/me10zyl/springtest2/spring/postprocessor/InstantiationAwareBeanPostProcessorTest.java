package com.me10zyl.springtest2.spring.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import static com.me10zyl.springtest2.util.LogUtil.log;

@Component
@Slf4j
public class InstantiationAwareBeanPostProcessorTest implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        log("InstantiationAwareBeanPostProcessorTest#postProcessBeforeInstantiation:" + beanName, "InstantiationAwareBeanPostProcessorTest#postProcessBeforeInstantiation");
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        log("InstantiationAwareBeanPostProcessorTest#postProcessAfterInstantiation:" + beanName, "InstantiationAwareBeanPostProcessorTest#postProcessAfterInstantiation");
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        log("InstantiationAwareBeanPostProcessorTest#postProcessProperties:" + beanName, "InstantiationAwareBeanPostProcessorTest#postProcessProperties");
        return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }
}
