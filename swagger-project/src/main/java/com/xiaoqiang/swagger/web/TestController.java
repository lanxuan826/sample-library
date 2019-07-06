package com.xiaoqiang.swagger.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController <br>
 * 〈测试控制器〉
 *
 * @author XiaoQiang
 * @create 2019-1-4
 * @since 1.0.0
 */
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("/rest")
    @ResponseBody
    @ApiOperation(value = "填写用户名称" , notes = "得到用户选修的课程")

    public Object testRestful(@RequestBody String name) {
        return name + "同学，您好！你选修的课程是语文！";
    }

}
