package com.xiaoqiang.cxf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class CxfApplication {
	protected final static Logger logger = LoggerFactory.getLogger(CxfApplication.class);
	public static void main(String[] args) {
		logger.info(" cxf start success! ");
		SpringApplication.run(CxfApplication.class, args);
	}

}
