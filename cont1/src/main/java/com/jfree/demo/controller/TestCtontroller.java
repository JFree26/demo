package com.jfree.demo.controller;

import com.jfree.demo.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestCtontroller {

    @Autowired
    private AppConfig appConfig;
}
