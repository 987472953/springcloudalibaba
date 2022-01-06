package com.dyqking.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池" + Thread.currentThread().getName() + "paymentInfo_OK, id = " + id + "\t" + "哈哈哈O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_error_handler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_error(Integer id){
        int timeNumber = 1000;
        //int age = 10/0;
        try {TimeUnit.MILLISECONDS.sleep(timeNumber);} catch (InterruptedException e) { e.printStackTrace();}
        return "线程池" + Thread.currentThread().getName() + "paymentInfo_OK, id = " + id + " \t" + "哈哈哈O(∩_∩)O哈哈~\n耗时" + timeNumber + "毫秒";
    }

    /**
     * 兜底方法（上面方法出错调用）
     * @param id
     * @return
     */
    public String paymentInfo_error_handler(Integer id){
        return "线程池" + Thread.currentThread().getName() + "   系统繁忙或系统报错，请稍后再试  \t " + "o(╥﹏╥)o";
    }

    /**
     * 服务熔断
     * @param id
     * @return
     */
    //HystrixCommandProperty
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //时间范围，10s钟
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " +id;
    }
}
