package com.jfree.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client1")
public class Client1Controller {

    @GetMapping("/mathInt")
    public double math() {
        System.out.println("----------------------double=" + Math.random());
        return Math.random();
    }
}
