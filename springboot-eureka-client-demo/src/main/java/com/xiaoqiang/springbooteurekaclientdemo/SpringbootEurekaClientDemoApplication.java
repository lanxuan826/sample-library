package com.xiaoqiang.springbooteurekaclientdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringbootEurekaClientDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootEurekaClientDemoApplication.class, args);
	}

}
