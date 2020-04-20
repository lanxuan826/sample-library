package com.xiaoqiang.test;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
    private static int corePoolSize = Runtime.getRuntime().availableProcessors();
    //创建线程池  调整队列数
    private static ThreadPoolExecutor executor  = new ThreadPoolExecutor(corePoolSize, corePoolSize+1, 10l, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000));

    public static void main(String[] args) {
//        System.out.println(corePoolSize);
//        for (int i=0; i<50;i++){
//            poolTest();
//        }
        test2();
    }

    public static void poolTest(){
        executor.execute(new Runnable() {
            @Override
            public void run() {

                System.out.println("thread:"+Thread.currentThread().getName()+",活跃线程数量："+executor.getActiveCount()+",线程数量："+executor.getTaskCount());
            }
        });
        if(executor.isShutdown()){
            System.out.println("线程关闭哦");
        }
    }


    public static void test2(){
        // 创建线程池
        ThreadPoolExecutor service = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),

                new ThreadPoolExecutor.AbortPolicy());
// 等待执行的runnable
        Runnable runnable = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

    // 启动的任务数量
        int counts = 1224;
        for (int i = 0; i < counts; i++) {
            service.execute(runnable);
        }

    // 监控线程池执行情况的代码
        ThreadPoolExecutor tpe = ((ThreadPoolExecutor) service);
        while (true) {
            System.out.println();

            int corePoolSize = tpe.getCorePoolSize();
            System.out.println("当前核心线程数：" + corePoolSize);

            int queueSize = tpe.getQueue().size();
            System.out.println("当前排队线程数：" + queueSize);

            int activeCount = tpe.getActiveCount();
            System.out.println("当前活动线程数：" + activeCount);

            long completedTaskCount = tpe.getCompletedTaskCount();
            System.out.println("执行完成线程数：" + completedTaskCount);

            long taskCount = tpe.getTaskCount();
            System.out.println("总线程数：" + taskCount);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
