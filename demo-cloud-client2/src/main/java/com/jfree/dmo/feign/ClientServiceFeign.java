package com.jfree.dmo.feign;

import com.jfree.dmo.feign.his.His1;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "client-1",fallback = His1.class)
public interface ClientServiceFeign {

    @GetMapping("/client1/mathInt")
    double math();


}
