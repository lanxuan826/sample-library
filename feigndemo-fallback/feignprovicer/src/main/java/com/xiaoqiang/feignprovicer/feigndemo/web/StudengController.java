package com.xiaoqiang.feignprovicer.feigndemo.web;

import com.xiaoqiang.common.entity.Student;
import com.xiaoqiang.feignprovicer.feigndemo.service.IStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("stud")
public class StudengController {

    private Logger logger = LoggerFactory.getLogger(StudengController.class);


    @Autowired
    private IStudentService studentService;

    @GetMapping("/getStudentList")
    public List<Student> getStudentList(@RequestParam(required = false,name = "name") String name){
        logger.info("请求getStudentList");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            logger.error("线程中断异常");
        }
        return studentService.getStudentList(name);
    }


    @GetMapping("/getStudent")
    public List<Student> getStudent(@RequestParam(required = false,name = "name") String name){
        throw new RuntimeException("服务端测试异常！");
    }
}
