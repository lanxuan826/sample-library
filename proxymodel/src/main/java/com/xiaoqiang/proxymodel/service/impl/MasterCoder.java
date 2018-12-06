package com.xiaoqiang.proxymodel.service.impl;


import com.xiaoqiang.proxymodel.service.Coder;

/**
 * MasterCoder <br>
 * 〈大神 coder〉
 *
 * @author XiaoQiang
 * @create 2018-12-6
 * @since 1.0.0
 */
public class MasterCoder implements Coder {

    private BirdCoder birdCoder;

    //普通代理
    public MasterCoder() {
        this.birdCoder = new BirdCoder();
    }

    //静态代理
    public MasterCoder(BirdCoder birdCoder) {
        this.birdCoder = birdCoder;
    }

    @Override
    public void coding() {

        //菜鸟coder写博客，大神coder推荐之
        birdCoder.coding();

        recommend();
    }


    public  void recommend(){
        System.out.println("推荐、点赞");
    }

}
