package com.jfree.dmo.controller;

import com.jfree.dmo.feign.ClientServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client2")
public class Client2Controller {

    @Autowired
    private ClientServiceFeign clientServiceFeign;

    @GetMapping("/c2")
    public double math() {
        Double d=clientServiceFeign.math();
        return d;
    }
}
