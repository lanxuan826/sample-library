package com.xiaoqiang.feigncomsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.xiaoqiang.feigncomsumer"})
@EnableFeignClients(basePackages = {"com.xiaoqiang.feigncomsumer"})
@EnableEurekaClient
public class FeigncomsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeigncomsumerApplication.class, args);
    }

}
