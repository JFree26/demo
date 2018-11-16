package com.jfree.dmo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(value = "client-1")
public interface ClientServiceFeign {

    @GetMapping("/client1/mathInt")
    double math();
}
