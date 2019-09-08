package com.xiaoqiang.feigncomsumer.feigndemo.client;

import com.xiaoqiang.common.entity.Student;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


@Component
@RequestMapping("fallback/")
public class  FallBackStudentClient implements StudentOtherClient {

    @Override
    public Student getStudent(String name) {
        Student student = new Student();
        student.setAge(0);
        student.setName("fall back test");
        return student;
    }
}
