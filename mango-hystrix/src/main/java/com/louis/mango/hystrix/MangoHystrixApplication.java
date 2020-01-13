package com.louis.mango.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * HystrixDashboard：针对Hystrix进行实时监控的工具，可以直观地看到各Hystrix Command的请求响应时间, 请求成功率等数据
 * Turbine：汇总系统内多个服务的数据并显示到Hystrix Dashboard上
 */
@EnableTurbine
@EnableHystrixDashboard
@EnableDiscoveryClient
@SpringBootApplication
public class MangoHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoHystrixApplication.class, args);
    }

}
