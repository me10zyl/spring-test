package com.me10zyl.mybatisMulti.service;

import com.me10zyl.mybatisMulti.dao.account.AccountMapper;
import com.me10zyl.mybatisMulti.dao.order.OrderMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MyService {
//    private final AccountMapper accountMapper;
//    private final OrderMapper orderMapper;
//    private final ApplicationContext context;
//
//    @Transactional
//    public void submitOrder(Integer uid, BigDecimal money){
//        int i = accountMapper.dedectBalance(uid, money.intValue());
//        if (i == 0) {
//            throw new RuntimeException("余额不足");
//        }
//        orderMapper.insert(uid);
//    }
//
//    @PostConstruct
//    public void init(){
//        context.getBean(MyService.class).submitOrder(1, new BigDecimal(100));
//    }
}
