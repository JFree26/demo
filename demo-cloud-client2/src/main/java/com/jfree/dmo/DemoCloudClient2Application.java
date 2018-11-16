package com.jfree.dmo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class DemoCloudClient2Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoCloudClient2Application.class, args);
	}
}
