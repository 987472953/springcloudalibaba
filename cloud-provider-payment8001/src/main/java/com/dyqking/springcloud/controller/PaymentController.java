package com.dyqking.springcloud.controller;

import com.dyqking.springcloud.entities.CommonResult;
import com.dyqking.springcloud.entities.Payment;
import com.dyqking.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        Payment payment = paymentService.selectById(id);
        if (payment != null) {
            log.info("获得成功：serverPort=" + serverPort);
            return new CommonResult(200, "查询成功：serverPort="+serverPort, payment);
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
            return new CommonResult(200, "插入成功：serverPort="+serverPort, insert);
        } else {
            log.info("插入失败：serverPort=" + serverPort);
            return new CommonResult(400, "");
        }
    }

    @GetMapping("discovery") // 服务发现
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*****service(element):" + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("instance: " + instance.getServiceId()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("/lb")
    public String paymentlb(){
        return serverPort;
    }

    @GetMapping("/feign/timeout")
        public String getTimeOut(){
        //OpenFeign 一般默认1秒钟
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }
}
