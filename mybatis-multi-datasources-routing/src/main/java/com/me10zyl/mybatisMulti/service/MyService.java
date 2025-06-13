package com.me10zyl.mybatisMulti.service;

import com.me10zyl.mybatisMulti.conf.DS;
import com.me10zyl.mybatisMulti.conf.anno.DSTranscational;
import com.me10zyl.mybatisMulti.dao.account.AccountMapper;
import com.me10zyl.mybatisMulti.dao.order.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MyService {
    private final AccountMapper accountMapper;
    private final OrderMapper orderMapper;
    private final ApplicationContext applicationContext;

    @DSTranscational
    public void submitOrder(Integer uid, BigDecimal money) {
        int i = accountMapper.dedectBalance(uid, money.intValue());
        if (i == 0) {
            throw new RuntimeException("余额不足");
        }
//        DataSourceContextHolder.setDataSourceType("order");
        applicationContext.getBean(this.getClass()).insertOrder(uid);
    }


    @DS("order")
    public void insertOrder(Integer uid) {
        //始终回滚不了
        orderMapper.insert(uid);
        throw new RuntimeException("模拟异常");
    }

}
