package com.louis.mango.consumer.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 熔断器回调类，熔断器需要一些时间进行自我诊断和修复完成
 * Created by liyue
 * Time 2019-09-25 14:50
 */
@Component
public class MangoProducerHystrix implements MangoProducerService {

    @Override
    @RequestMapping("/hello")
    public String hello() {

        return "sorry, hello service call failed —— 熔断器Hystrix";
    }
}
