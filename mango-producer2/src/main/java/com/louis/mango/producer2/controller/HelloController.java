package com.louis.mango.producer2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liyue
 * Time 2019-09-24 17:32
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello Mango2 !";
    }
}
