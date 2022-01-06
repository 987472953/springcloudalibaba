package com.dyqking.springcloud.controller;

import com.dyqking.springcloud.entities.CommonResult;
import com.dyqking.springcloud.entities.Payment;
import com.dyqking.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @GetMapping("get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        Payment payment = paymentService.selectById(id);
        if (payment != null) {
            log.info("获得成功：serverPort=" + serverPort);
            return new CommonResult(200, "查询成功：serverPort=" + serverPort, payment);
        } else {
            log.info("获得失败：serverPort=" + serverPort);
            return new CommonResult(400, "");
        }
    }

    @PostMapping("create")
    public CommonResult insertPayment(@RequestBody Payment payment) {
        int insert = paymentService.insertPayment(payment);
        if (insert > 0) {
            log.info("插入成功：serverPort=" + serverPort);
            return new CommonResult(200, "插入成功：serverPort=" + serverPort, insert);
        } else {
            log.info("插入失败：serverPort=" + serverPort);
            return new CommonResult(400, "");
        }
    }

    @GetMapping("/lb")
    public String paymentlb(){
        return serverPort;
    }
}
