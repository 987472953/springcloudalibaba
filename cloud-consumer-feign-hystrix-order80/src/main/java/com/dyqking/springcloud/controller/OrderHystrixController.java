package com.dyqking.springcloud.controller;

import com.dyqking.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.awt.SunHints;

import javax.annotation.Resource;

@RestController
@RequestMapping("consumer")
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;


    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentOK(@PathVariable Integer id){
        return paymentHystrixService.paymentOK(id);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentErrorFallbackMethod",commandProperties = {
//        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")  //3秒钟以内就是正常的业务逻辑
//    })
    @HystrixCommand
    public String paymentError(@PathVariable Integer id){
        return paymentHystrixService.paymentError(id);
    }

    public String paymentErrorFallbackMethod(@PathVariable Integer id){
        return "消费者80调用8001出错，对方错误或80端口问题";
    }

    //全局默认fallback方法
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试,(┬＿┬)";
    }

}
