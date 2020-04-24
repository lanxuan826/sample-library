package com.xiaoqiang.springbootadminclientdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootAdminClientDemoApplication {
	protected  static  Logger logger = LoggerFactory.getLogger(SpringBootApplication.class);

	public static void main(String[] args) {
		logger.info("client start success");
		SpringApplication.run(SpringbootAdminClientDemoApplication.class, args);
	}

}
