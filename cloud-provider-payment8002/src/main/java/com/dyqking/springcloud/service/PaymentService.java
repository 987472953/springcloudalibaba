package com.dyqking.springcloud.service;

import com.dyqking.springcloud.entities.Payment;

public interface PaymentService {

    Payment selectById(Long id);

    int insertPayment(Payment payment);
}
