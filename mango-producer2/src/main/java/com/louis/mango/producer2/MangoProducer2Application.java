package com.louis.mango.producer2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MangoProducer2Application {

    public static void main(String[] args) {
        SpringApplication.run(MangoProducer2Application.class, args);
    }

}
