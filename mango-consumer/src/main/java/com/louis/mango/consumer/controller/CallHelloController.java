package com.louis.mango.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 模拟使用负载均衡的形式去获取服务端提供的服务
 * Created by liyue
 * Time 2019-09-25 10:41
 */
@RestController
public class CallHelloController {

    @Autowired
    private LoadBalancerClient loadBalancer;

    @RequestMapping("/call")
    public String call(){
        ServiceInstance serviceInstance = loadBalancer.choose("mango-producer");
        System.out.println("服务地址： " + serviceInstance.getUri());
        System.out.println("服务名称： " + serviceInstance.getServiceId());

        String callServiceResult = new RestTemplate().getForObject(serviceInstance.getUri().toString() + "/hello", String.class);
        System.out.println(callServiceResult);

        return callServiceResult;

    }
}
