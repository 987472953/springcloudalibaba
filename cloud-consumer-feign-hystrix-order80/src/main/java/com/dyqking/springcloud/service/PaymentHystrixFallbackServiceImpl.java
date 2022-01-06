package com.dyqking.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixFallbackServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentOK(Integer id) {
        return "---FallbackServiceImpl---方法名：paymentOK";
    }

    @Override
    public String paymentError(Integer id) {
        return "---FallbackServiceImpl---方法名：paymentError";
    }
}
