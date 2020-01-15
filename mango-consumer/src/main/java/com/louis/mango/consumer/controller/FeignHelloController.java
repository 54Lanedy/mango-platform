package com.louis.mango.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * feign（声明式服务调用）服务消费控制器,与ribbon均属于负载均衡。
 *
 * feign采用接口的方式，将需要调用的其他服务的方法定义成抽象方法即可，不需要自己构建http请求。
 * 不过要注意的是抽象方法的注解、方法签名要和提供服务的方法完全一致。
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
