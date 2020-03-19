package com.xiaoqiang.test;

import java.util.Calendar;

/**
 * 小马哥 面试题
 * 本题主要考 static声明的顺序问题
 * 答案：size=-1940
 */
public class Test1 {
    public static final  Test1 Instance = new Test1();
    private  final  int size;
    private  final  static  int year = Calendar.getInstance().get(Calendar.YEAR);


    private  Test1(){
        size = year - 1940;
    }

    public int getSize(){
        return size;
    }
    public static void main(String[] args) {
        System.out.println("size:" + Instance.getSize());
    }
}
