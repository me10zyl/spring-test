package com.me10zyl.mybatisMulti.conf.aop;

import ch.qos.logback.core.util.StringUtil;
import com.me10zyl.mybatisMulti.conf.DS;
import com.me10zyl.mybatisMulti.conf.DataSourceContextHolder;
import com.me10zyl.mybatisMulti.conf.DynamicDataSource;
import com.me10zyl.mybatisMulti.conf.anno.DSTranscational;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DSAop {

    @Autowired
    private DynamicDataSource dynamicDataSource;

    @Around("@annotation(com.me10zyl.mybatisMulti.conf.anno.DSTranscational)")
    public Object around2(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        DSTranscational anno = methodSignature.getMethod().getAnnotation(DSTranscational.class);
        if (anno != null) {
            Map<Object, DataSource> resolvedDataSources =
                    dynamicDataSource.getResolvedDataSources();
            Collection<DataSource> values = resolvedDataSources.values();
            Map<DataSourceTransactionManager, TransactionStatus> maps = new HashMap<>();
            values.forEach(dataSource -> {
                DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                TransactionStatus status = transactionManager.getTransaction(def);
                maps.put(transactionManager, status);
            });
            try {
                Object proceed = joinPoint.proceed();
                maps.forEach(AbstractPlatformTransactionManager::commit);
                return proceed;
            } catch (Throwable e) {
                maps.forEach(AbstractPlatformTransactionManager::rollback);
                throw e;
            }
        } else {
            return joinPoint.proceed();
        }
    }

    @Around("@annotation(com.me10zyl.mybatisMulti.conf.DS)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        DS ds = methodSignature.getMethod().getAnnotation(DS.class);
        String dataSourceType = ds.value();
        try {
            if (!StringUtil.isNullOrEmpty(dataSourceType)) {
                DataSourceContextHolder.setDataSourceType(dataSourceType);
            }
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw e;
        } finally {
            DataSourceContextHolder.clearDataSourceType();
        }
    }
}
