package com.xiaoqiang.feignprovicer.feigndemo.service;

import com.xiaoqiang.common.entity.Student;

import java.util.List;


public interface IStudentService {

     List<Student> getStudentList(String name);
}
