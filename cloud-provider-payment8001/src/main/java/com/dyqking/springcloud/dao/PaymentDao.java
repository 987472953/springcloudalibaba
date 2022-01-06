package com.dyqking.springcloud.dao;

import com.dyqking.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    Payment selectById(@Param("id") Long id);

    int insertPayment(Payment payment);
}
