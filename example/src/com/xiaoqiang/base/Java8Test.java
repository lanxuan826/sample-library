package com.xiaoqiang.base;

import java.util.*;

public class Java8Test {
    public static void main(String[] args) {
        lambda();  //lambd
        functional(); //方法引用
        stream();
        Optional();

    }


    public static  void lambda(){
        List<String> names1 = new ArrayList<String>();
        names1.add("Google ");
        names1.add("Runoob ");
        names1.add("Taobao ");
        names1.add("Baidu ");
        names1.add("Sina ");

        List<String> names2 = new ArrayList<String>();
        names2.add("Google ");
        names2.add("Runoob ");
        names2.add("Taobao ");
        names2.add("Baidu ");
        names2.add("Sina ");

        System.out.println("使用 Java 7 语法: ");
        sortUsingJava7(names1);
        System.out.println(names1);
        System.out.println("使用 Java 8 语法: ");

        sortUsingJava8(names2);
        System.out.println(names2);
    }
    //java7写法
    public  static void sortUsingJava7(List<String> names){
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }
    //java8写法
    public  static void sortUsingJava8(List<String> names){
        Collections.sort(names,(o1,o2)->o1.compareTo(o2));
    }

    //方法引用
    public static void functional(){
        List names = new ArrayList();
        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");
        System.out.println("=========方法引用==============");
        names.forEach(System.out::println);
    }

    public static void stream(){
        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 输出非空字符串
        System.out.println("=========stream==============");
        strings.stream().filter(string -> !string.isEmpty()).forEach(System.out::println);
    }

    public static void Optional(){
        Integer value1 = null;
        Integer value2 = new Integer(10);
        System.out.println("=========Optional==============");
        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);
        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> b = Optional.of(value2);
        // Optional.isPresent - 判断值是否存在

        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer result1 = a.orElse(new Integer(0));

        //Optional.get - 获取值，值需要存在
        Integer result2 = b.get();

        System.out.println("a+b=" +(result1+result2));

    }

}
