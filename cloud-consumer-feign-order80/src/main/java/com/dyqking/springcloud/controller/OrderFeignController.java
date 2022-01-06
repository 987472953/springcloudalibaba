package com.dyqking.springcloud.controller;

import com.dyqking.springcloud.entities.CommonResult;
import com.dyqking.springcloud.entities.Payment;
import com.dyqking.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("consumer")
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getFeignPayment(@PathVariable("id") Long id){
        return paymentFeignService.getPayment(id);
    }

    @GetMapping("payment/feign/timeout")
    public String getTimeout(){
        return paymentFeignService.getTimeOut();
    }

}
