package com.xiaoqiang.feigncomsumer.feigndemo.web;


import com.xiaoqiang.common.entity.Student;
import com.xiaoqiang.feigncomsumer.feigndemo.client.StudentClient;
import com.xiaoqiang.feigncomsumer.feigndemo.client.StudentOtherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class StudentController {


    @Autowired
    private StudentClient studentClient;

    @Autowired
    private StudentOtherClient studentOtherClient;

    @GetMapping("/getStudentList")
    public List<Student> getStudentList(@RequestParam(required = false,name = "name") String name){
        return studentClient.getStudentList(name);
    }


    @GetMapping("/getStudent")
    public Student getStudent(@RequestParam(required = false,name = "name") String name){
        return studentOtherClient.getStudent(name);
    }
}
