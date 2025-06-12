package com.me10zyl.mybatisMulti.service;

import com.me10zyl.mybatisMulti.dao.account.AccountMapper;
import com.me10zyl.mybatisMulti.dao.order.OrderMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MyService {
    private final AccountMapper accountMapper;
    private final OrderMapper orderMapper;
    private final ApplicationContext applicationContext;
    private final PlatformTransactionManager transactionManager;


    public void submitOrder(Integer uid, BigDecimal money){
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionAttribute());
        int i = accountMapper.dedectBalance(uid, money.intValue());
        if (i == 0) {
            throw new RuntimeException("余额不足");
        }
        try {
            applicationContext.getBean(this.getClass()).insertOrder(uid);
            transactionManager.commit(transaction);
        }catch (Exception e){
            transactionManager.rollback(transaction);
            throw e;
        }
    }


    public void insertOrder(Integer uid) {
        //始终回滚不了
        orderMapper.insert(uid);
        throw new RuntimeException("模拟异常");
    }

}
