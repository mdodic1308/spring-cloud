package com.mido.restclient;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class RestClientApplication {

    @Autowired
    EurekaClient client;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public static void main(String[] args) {
        SpringApplication.run(RestClientApplication.class, args);
    }

    @RequestMapping("/")
    public String callService(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        InstanceInfo application = client.getNextServerFromEureka("rest-service",false);
        String baseUrl  = application.getHomePageUrl();
        ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET,null, String.class);
        return responseEntity.getBody();
    }
}
