package com.xiaoqiang.moocspringbootsproject.web.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * MyServlet <br>
 * 〈My Servlet〉
 *
 * @author XiaoQiang
 * @create 2018-12-9
 * @since 1.0.0
 */
@WebServlet(urlPatterns = "/my/servlet",asyncSupported=true)
public class MyServlet  extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().print("Hello World");

        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(()->{
            try {
                resp.getWriter().print("Hello World");
                asyncContext.complete();
            }catch (IOException e){
                e.printStackTrace();
            }

        });
/*
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    resp.getWriter().print("Hello World");
                    asyncContext.complete();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });*/
    }
}
