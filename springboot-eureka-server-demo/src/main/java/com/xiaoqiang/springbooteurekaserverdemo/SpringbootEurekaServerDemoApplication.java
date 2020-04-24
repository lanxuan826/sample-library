package com.xiaoqiang.springbooteurekaserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringbootEurekaServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootEurekaServerDemoApplication.class, args);
	}

}
