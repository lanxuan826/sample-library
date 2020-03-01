package com.xiaoqiang.test;

import org.junit.Test;


public class MyTest {


    public static void main(String[] args) {
        String[] per = new String[]{"202001","202002"};
        for (int i = 0;i<10; i++){
            int a = (int)Math.floor(Math.random()*2);
            System.out.println(a);
            System.out.println(per[a]);
        }
    }
}
