package com.xiaoqiang.cxf.service.impl;

import com.xiaoqiang.cxf.entity.Student;
import com.xiaoqiang.cxf.service.IStudentService;
import org.springframework.stereotype.Component;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
/**
 * StudentServiceImpl <br>
 * 〈学生接口实现类〉
 *
 * @author XiaoQiang
 * @create 2019-6-27
 * @since 1.0.0
 */
@WebService(serviceName = "studentService"//服务名
        ,targetNamespace = "http://service.cxf.xiaoqiang.com/" //报名倒叙，并且和接口定义保持一致
        ,endpointInterface = "com.xiaoqiang.cxf.service.IStudentService")//包名
@Component
public class StudentServiceImpl implements IStudentService {

    @Override
    public List<Student> getStudentInfo() {
        List<Student> stuList = new ArrayList<>();
        Student student = new Student();
        student.setAge(18);
        student.setScore(700);
        student.setName("小强");
        stuList.add(student);
        return stuList;

    }
}
