package com.dyqking.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class ConsulController {

    public static final String PAYMENT_RUL = "http://consul-provider-payment";
    @Resource
    RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String payment(){
        String result = restTemplate.getForObject(PAYMENT_RUL + "/payment/consul", String.class);
        return result;
    }
}
