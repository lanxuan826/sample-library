package com.xiaoqiang.proxymodel.service.impl;

import com.xiaoqiang.proxymodel.service.Coder;

/**
 * BirdCoder <br>
 * 〈菜鸟coder〉
 *
 * @author XiaoQiang
 * @create 2018-12-6
 * @since 1.0.0
 */
public class BirdCoder implements Coder {

    @Override
    public void coding() {
        System.out.println("菜鸟写博客=======");
    }
}
