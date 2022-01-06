package com.dyqking.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("guonei_route", r->r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        routes.route("guoji_route", r->r.path("/guoji").uri("http://news.baidu.com/guoji")).build();
        return routes.build();
    }
}
