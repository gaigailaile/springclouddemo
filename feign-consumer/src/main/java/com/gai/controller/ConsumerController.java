package com.gai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer")
    public String consumer(String name){
        return restTemplate.getForEntity("http://EUREKA-CLIENT/hello/sayHello",String.class,name).getBody();
    }
}
