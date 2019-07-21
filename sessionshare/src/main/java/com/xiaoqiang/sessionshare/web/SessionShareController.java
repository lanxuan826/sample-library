package com.xiaoqiang.sessionshare.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * SessionShareController <br>
 * 〈session共享控制器〉
 *
 * @author XiaoQiang
 * @create 2019-7-6
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/session")
public class SessionShareController {

    @Value("${server.port}")
    Integer port;


    @GetMapping(value = "/set")
    public String set(HttpSession session){
        session.setAttribute("user","wangwq8");
        return String.valueOf(port);
    }

    @GetMapping(value = "get")
    public String get(HttpSession session){
        return "用户:"+session.getAttribute("user")+",端口:"+port;
    }


}
