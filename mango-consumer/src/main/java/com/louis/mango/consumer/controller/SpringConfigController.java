//package com.louis.mango.consumer.controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Created by liyue
// * Time 2019-09-26 14:49
// */
//@RefreshScope
//@RestController
//public class SpringConfigController {
//
//    @Value("${hello}")
//    private String hello;
//
//    @RequestMapping("/hello")
//    public String from(){
//        return this.hello;
//    }
//}
