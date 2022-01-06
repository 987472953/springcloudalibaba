package com.dyqking.springcloud.service.impl;

import com.dyqking.springcloud.dao.PaymentDao;
import com.dyqking.springcloud.entities.Payment;
import com.dyqking.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public Payment selectById(Long id) {
        return paymentDao.selectById(id);
    }

    @Override
    public int insertPayment(Payment payment) {

        return paymentDao.insertPayment(payment);
    }
}
