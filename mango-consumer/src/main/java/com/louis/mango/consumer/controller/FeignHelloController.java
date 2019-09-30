package com.louis.mango.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * feign（声明式服务调用）服务消费控制器
 * Created by liyue
 * Time 2019-09-25 11:48
 */
@RestController
public class FeignHelloController {

    @Autowired
    private MangoProducerService mangoProducerService;

    @RequestMapping("/feign/call")
    public String hello(){
        return mangoProducerService.hello();
    }
}
