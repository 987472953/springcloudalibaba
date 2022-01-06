package com.dyqking.springcloud.alibaba.controller;

import com.dyqking.springcloud.alibaba.domain.CommonResult;
import com.dyqking.springcloud.alibaba.domain.Order;
import com.dyqking.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("order/create")
    public CommonResult<Order> createOrder(Order order) {
        orderService.create(order);
        return new CommonResult<Order>(200, "创建订单成功 -- " + new Date(), order);
    }
}
