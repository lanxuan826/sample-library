package com.xiaoqiang.cxf.entity;

import java.io.Serializable;

/**
 * Student <br>
 * 〈学生〉
 *
 * @author XiaoQiang
 * @create 2019-6-27
 * @since 1.0.0
 */
public class Student implements Serializable {


    private String name;

    private int age;

    private double score;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
