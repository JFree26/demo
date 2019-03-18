package com.jfree.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class DemoCloudZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCloudZuulApplication.class, args);
	}
}
