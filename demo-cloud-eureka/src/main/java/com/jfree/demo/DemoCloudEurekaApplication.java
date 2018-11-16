package com.jfree.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DemoCloudEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCloudEurekaApplication.class, args);
	}
}
