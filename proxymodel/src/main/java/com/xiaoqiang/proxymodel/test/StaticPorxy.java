package com.xiaoqiang.proxymodel.test;


import com.sun.org.apache.bcel.internal.classfile.Code;
import com.xiaoqiang.proxymodel.service.Coder;
import com.xiaoqiang.proxymodel.service.impl.BirdCoder;
import com.xiaoqiang.proxymodel.service.impl.MasterCoder;
import org.omg.CORBA.portable.InvokeHandler;
import sun.rmi.runtime.NewThreadAction;

import javax.management.DynamicMBean;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * StaticPorxy <br>
 * 〈静态代理〉
 *
 * @author XiaoQiang
 * @create 2018-12-6
 * @since 1.0.0
 */
public class StaticPorxy {

    public static  void  main(String[] agrs){

        //静态代理
        PorxyStatic();

        //普通代理,真实对象对外界来说是透明的
        PorxySimple();


        //动态代理
        ProxyDynamic();

//        静态代理需要自己写代理类-->代理类需要实现与目标对象相同的接口
//
//        而动态代理不需要自己编写代理类--->(是动态生成的)
//
//        使用静态代理时：如果目标对象的接口有很多方法的话，那我们还是得一一实现，这样就会比较麻烦
//
//        使用动态代理时：代理对象的生成，是利用JDKAPI，动态地在内存中构建代理对象(需要我们指定创建 代理对象/目标对象 实现的接口的类型)，并且会默认实现接口的全部方法。

    }




    public static  void PorxyStatic(){
        //大神coder 接受代理，给菜鸟coder 写的博客 加推荐
        BirdCoder birdCoder = new BirdCoder();
        MasterCoder masterCoder = new MasterCoder(birdCoder);
        masterCoder.coding();
    }



    private static void PorxySimple() {
        //大神coder 接受代理，只给菜鸟coder 写的博客 加推荐
        MasterCoder masterCoder = new MasterCoder();
        masterCoder.coding();
    }

    private static void ProxyDynamic() {

        BirdCoder birdCoder = new BirdCoder();

        Coder coders = (Coder) Proxy.newProxyInstance(birdCoder.getClass().getClassLoader(), birdCoder.getClass().getInterfaces(),
                new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getName().equals("coding")){
                    method.invoke(birdCoder,args);
                    System.out.println("水军来推荐");
                }else{
                    return method.invoke(birdCoder,args);
                }

                return null;
            }
        });

        coders.coding();
    }


}
