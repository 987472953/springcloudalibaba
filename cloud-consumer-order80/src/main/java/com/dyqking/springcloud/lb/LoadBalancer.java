package com.dyqking.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    //收集服务器总共有多少台能够提供服务的机器，并放到list里面，返回选择的服务器
    ServiceInstance instance (List<ServiceInstance> serviceInstances);
}
