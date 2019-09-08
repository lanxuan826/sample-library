package com.xiaoqiang.feigncomsumer.feigndemo.client;

import com.xiaoqiang.common.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${feign.provider}",path = "/feignprovider"
        /*,fallback = FallBackStudentClient.class*/)
@Component
public interface StudentClient {

     @GetMapping("/stud/getStudentList")
     List<Student> getStudentList(@RequestParam(required = false,name = "name") String name);
}
