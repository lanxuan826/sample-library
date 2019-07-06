package com.xiaoqiang.cxf.service;

import com.xiaoqiang.cxf.entity.Student;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * IStudentService <br>
 * 〈〉
 *
 * @author XiaoQiang
 * @create 2019-6-27
 * @since 1.0.0
 */

@WebService(targetNamespace = "http://service.cxf.xiaoqiang.com/") //命名一般是接口类的包名倒序
public interface IStudentService {

    @WebMethod
    List<Student> getStudentInfo();
}
