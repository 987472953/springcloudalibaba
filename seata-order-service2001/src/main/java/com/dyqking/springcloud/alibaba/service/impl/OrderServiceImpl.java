package com.dyqking.springcloud.alibaba.service.impl;

import com.dyqking.springcloud.alibaba.dao.OrderDao;
import com.dyqking.springcloud.alibaba.domain.Order;
import com.dyqking.springcloud.alibaba.service.feign.AccountService;
import com.dyqking.springcloud.alibaba.service.OrderService;
import com.dyqking.springcloud.alibaba.service.feign.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "dyq_tx_group", rollbackFor = Exception.class)
    public void create(Order order) {

        log.info("--->开始新建订单");
        orderDao.create(order);

        log.info("--->调用库存服务，减库存");
        storageService.decrease(order.getProductId(), order.getCount());

        log.info("--->调用账户服务，减余额");
        accountService.decrease(order.getUserId(), order.getMoney());

        log.info("--->修改订单状态");
        orderDao.update(order.getUserId(), 0);
    }


}
