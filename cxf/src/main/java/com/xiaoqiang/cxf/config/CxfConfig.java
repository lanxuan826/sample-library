package com.xiaoqiang.cxf.config;

import com.xiaoqiang.cxf.service.IStudentService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.xml.ws.Endpoint;
/**
 * CxfConfig <br>
 * 〈cxf配置类〉
 * @desription cxf发布webservice配置
 * @author XiaoQiang
 * @create 2019-6-27
 * @since 1.0.0
 */
@Configuration
public class CxfConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private IStudentService studentService;


    /**
     * 站点服务
     * @return
     */
    @Bean
    public Endpoint studentServiceEndpoint(){
        EndpointImpl endpoint = new EndpointImpl(bus,studentService);
        endpoint.publish("/studentService");
        return endpoint;
    }


}
