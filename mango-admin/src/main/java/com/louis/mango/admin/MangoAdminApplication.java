package com.louis.mango.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @SpringBootApplication默认扫描启动类包及其子包
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.louis.mango.admin"})
public class MangoAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangoAdminApplication.class, args);
	}

}
