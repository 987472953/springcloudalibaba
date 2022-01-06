package com.dyqking.springcloud.alibaba.controller;

import com.dyqking.springcloud.alibaba.service.PaymentService;
import com.dyqking.springcloud.entities.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class FeignController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }
}
