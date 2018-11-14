package com.jfree.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.jfree.demo.repo")
public class DemoElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoElasticsearchApplication.class, args);
    }
}
