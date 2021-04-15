package com.mido.simplerestservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SimpleRestServiceApplication {

    @Value("${service.instance.name}")
    private String instance;
    @Value("${some.env}")
    private String env;

    public static void main(String[] args) {
        SpringApplication.run(SimpleRestServiceApplication.class, args);
    }

    @RequestMapping("/")
    public String message() {
        return "Hello from " + instance + ", env is " + env;
    }
}
