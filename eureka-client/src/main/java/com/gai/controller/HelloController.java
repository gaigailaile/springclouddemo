package com.gai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private DiscoveryClient client;

    @Value("${spring.application.name}")
    private String serviceId;

    @GetMapping("/sayHello")
    public String sayHello(String name){
        String result = "sayHello to " + name;
        List<ServiceInstance> instanceList = client.getInstances(serviceId.toUpperCase());
        if(instanceList != null && instanceList.size() != 0){
            ServiceInstance instance = instanceList.get(0);
            String host = " host: " + instance.getHost();
            String uri = " uri: " + instance.getUri();
            String port = " port: " + instance.getPort();
            String serviceId = " serviceId: " + instance.getServiceId();
            result = result + host + uri + port + serviceId;
        }
        return result;
    }

    @GetMapping("/a")
    public String a(){
        return "b";
    }
}
