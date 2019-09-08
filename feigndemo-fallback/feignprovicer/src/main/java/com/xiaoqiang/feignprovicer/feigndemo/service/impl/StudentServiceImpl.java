package com.xiaoqiang.feignprovicer.feigndemo.service.impl;

import com.xiaoqiang.common.entity.Student;
import com.xiaoqiang.feignprovicer.feigndemo.service.IStudentService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Override
    public List<Student> getStudentList(String name) {
        List<Student> list = new ArrayList<>();
        if(!StringUtils.isEmpty(name)){
            Student student = new Student();
            student.setName(name);
            student.setAge((int) (Math.random()*100));
            list.add(student);
        }else{
            for (int i=0; i<10; i++){
                Student student = new Student();
                student.setName("xiaoqiang"+i);
                student.setAge((int) (Math.random()*100));
                list.add(student);
            }
        }
        return list;
    }
}
