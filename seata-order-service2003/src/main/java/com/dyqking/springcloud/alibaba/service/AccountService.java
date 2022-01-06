package com.dyqking.springcloud.alibaba.service;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface AccountService {

    public void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
