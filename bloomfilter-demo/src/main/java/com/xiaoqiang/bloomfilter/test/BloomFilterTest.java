package com.xiaoqiang.bloomfilter.test;


import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class BloomFilterTest {

    private static final  int insertions = 1000000;

    private static double fpp = 0.01;

    public static void main(String[] args) {

        //初始化一个存储string数据的布隆过滤器,默认误判率是0.03
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8),insertions,fpp);

        //用于存放所有实际存在的key，用于是否存在
        Set<String> sets = new HashSet<String>(insertions);

        //用于存放所有实际存在的key，用于取出
        List<String> lists = new ArrayList<String>(insertions);


        for (int i=0; i<insertions; i++){
            String uuid = UUID.randomUUID().toString();
            bf.put(uuid);
            sets.add(uuid);
            lists.add(uuid);
        }

        int right = 0;
        int wrong = 0;

        for (int i=0; i<10000; i++){
            String data = i % 100 ==0? lists.get(i/100):UUID.randomUUID().toString();
            if(bf.mightContain(data)){
                if(sets.contains(data)){
                    right++;
                    continue;
                }
                wrong ++;
            }
        }
        BigDecimal percent = new BigDecimal(wrong).divide(new BigDecimal(9900), 2, RoundingMode.HALF_UP);
        BigDecimal bingo = new BigDecimal(9900-wrong).divide(new BigDecimal(9900),2,RoundingMode.HALF_UP);
        System.out.println("在100W个元素中，判断100个实际存在的元素，布隆过滤器认为存在的："+right);
        System.out.println("在100W个元素中，判断9900个实际不存在的元素，误认为存在的："+wrong+"，命中率："+bingo+"，误判率："+percent);

    }
}
