package com.xiaoqiang.cxf;

import com.xiaoqiang.cxf.entity.Student;
import com.xiaoqiang.cxf.service.IStudentService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CxfApplicationTests {


	private Logger logger = LoggerFactory.getLogger(CxfApplication.class);
	@Test
	public void contextLoads() {
	}

	/**
	 * 方法一:动态客户端调用
	 */
	@Test
	public void DynamicClient(){

		JaxWsDynamicClientFactory jwdcf = JaxWsDynamicClientFactory.newInstance();
		Client client = jwdcf.createClient("http://localhost:8080/services/studentService?wsdl");
		Object[] objects = new Object[0];

		try {
			objects = client.invoke("getStudentInfo");
			logger.info("获取学生信息==>{}",objects[0].toString());
			System.out.println("invoke实体："+((ArrayList) objects[0]).get(0).getClass().getPackage());
			for(int i=0 ; i< ((ArrayList)objects[0]).size() ; i++){
				Student student = new Student();
				BeanUtils.copyProperties(((ArrayList) objects[0]).get(0),student);
				logger.info("DynamicClient方式，获取学生{}信息==> 姓名:{}，年龄:{},分数:{}",i+1,
						student.getName(),student.getAge(),student.getScore());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}


	/**
	 * 代理类工厂
	 */
	@Test
	public void ProxyFactory(){

		String address = "http://localhost:8080/services/studentService?wsdl";
		//代理工厂
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		//设置代理地址
		factoryBean.setAddress(address);
		//设置接口类型
		factoryBean.setServiceClass(IStudentService.class);
		//创建一个代理接口实现
		IStudentService studentService = (IStudentService) factoryBean.create();
		List<Student> studentList = studentService.getStudentInfo();
		for(int i=0 ; i< studentList.size() ; i++){
			Student student = studentList.get(i);
			logger.info("ProxyFactory方式，获取学生{}信息==> 姓名:{}，年龄:{},分数:{}",i+1,
					student.getName(),student.getAge(),student.getScore());
		}
	}



}
