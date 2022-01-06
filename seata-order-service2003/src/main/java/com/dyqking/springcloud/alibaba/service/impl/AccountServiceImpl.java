package com.dyqking.springcloud.alibaba.service.impl;

import com.dyqking.springcloud.alibaba.dao.AccountDao;
import com.dyqking.springcloud.alibaba.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;


    @Override
    public void decrease(Long userId, BigDecimal money) {
        accountDao.decrease(userId, money);
    }
}
