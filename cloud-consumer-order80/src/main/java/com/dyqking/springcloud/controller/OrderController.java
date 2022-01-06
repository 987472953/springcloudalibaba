package com.dyqking.springcloud.controller;

import com.dyqking.springcloud.entities.CommonResult;
import com.dyqking.springcloud.entities.Payment;
import com.dyqking.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("consumer")
public class OrderController {

    // public static final String PAYMENT_RUL = "http://localhost:8001";
    public static final String PAYMENT_RUL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        //return restTemplate.postForObject(PAYMENT_RUL + "/payment/create", payment, CommonResult.class);
        return restTemplate.postForEntity(PAYMENT_RUL + "/payment/create", payment, CommonResult.class).getBody();
    }

    @GetMapping("payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_RUL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("payment/getForEntity/{id}")
    public CommonResult<Payment> getPaymentEntity(@PathVariable Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_RUL + "/payment/get" + id, CommonResult.class);

        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败");
        }
    }

    @GetMapping("payment/lb")
    public String paymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance instance = loadBalancer.instance(instances);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }

    // ====================> zipkin+sleuth
    @GetMapping("/payment/zipkin")
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
        return result;
    }

}
