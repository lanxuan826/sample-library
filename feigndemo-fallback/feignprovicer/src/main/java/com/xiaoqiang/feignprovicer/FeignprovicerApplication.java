package com.xiaoqiang.feignprovicer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class FeignprovicerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignprovicerApplication.class, args);
    }

}
