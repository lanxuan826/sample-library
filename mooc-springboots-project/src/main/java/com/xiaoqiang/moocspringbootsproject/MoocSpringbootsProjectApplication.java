package com.xiaoqiang.moocspringbootsproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.xiaoqiang.moocspringbootsproject.web.servlet")
public class MoocSpringbootsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoocSpringbootsProjectApplication.class, args);
	}
}
