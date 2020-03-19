package com.xiaoqiang.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * 读取文件内容
 */
public class FileRead {

    public static void main(String[] args) {
        fileRead();
    }

    public static void fileRead() {
        try{
            File file = new File("D:\\data\\11111.log");
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String str = "";
            while ((str = bufferedReader.readLine()) != null){
                sb.append(str).append("\n");
                System.out.println(str);
            }
            bufferedReader.close();
            System.out.println("sb===>"+sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
