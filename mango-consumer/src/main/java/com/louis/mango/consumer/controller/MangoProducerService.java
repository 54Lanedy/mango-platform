package com.louis.mango.consumer.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * feign声明接口
 * Created by liyue
 * Time 2019-09-25 11:45
 */
@FeignClient(name = "mango-producer",fallback = MangoProducerHystrix.class)
public interface MangoProducerService {

    /**
     * 声明跟调用目标方法一样的方法，不需要具体实现
     * @return
     */
    @RequestMapping("/hello")
    public String hello();
}
