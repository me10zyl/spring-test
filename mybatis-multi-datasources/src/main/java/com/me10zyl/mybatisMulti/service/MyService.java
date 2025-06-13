package com.me10zyl.mybatisMulti.service;

import com.me10zyl.mybatisMulti.dao.account.AccountMapper;
import com.me10zyl.mybatisMulti.dao.order.OrderMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Resource
    private PlatformTransactionManager orderTM;
    @Autowired
    private PlatformTransactionManager transactionManager;


    public void submitOrder(Integer uid, BigDecimal money) {
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionAttribute());
        TransactionStatus orderStatus = orderTM.getTransaction(new DefaultTransactionAttribute());
        try {
            int i = accountMapper.dedectBalance(uid, money.intValue());
            if (i == 0) {
                throw new RuntimeException("余额不足");
            }
            applicationContext.getBean(this.getClass()).insertOrder(uid);
            orderTM.commit(orderStatus);
            transactionManager.commit(transaction);
        } catch (Exception e) {
            orderTM.rollback(orderStatus);
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
