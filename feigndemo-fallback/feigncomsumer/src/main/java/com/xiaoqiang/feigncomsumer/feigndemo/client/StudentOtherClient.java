package com.xiaoqiang.feigncomsumer.feigndemo.client;

import com.xiaoqiang.common.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Component
@FeignClient(name = "${feign.provider}",path = "/feignprovider"
        ,fallback = FallBackStudentClient.class)
public interface StudentOtherClient {
    @GetMapping("/stud/getStudent")
    Student getStudent(@RequestParam(required = false, name = "name") String name);
}
