package com.xiaoqiang.bloomfilter;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.xiaoqiang.bloomfilter.redis.BloomFilterHelper;
import com.xiaoqiang.bloomfilter.redis.RedisBloomFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BloomFilterApplication.class)
public class RedisBloomFilterTest {


    @Autowired
    private RedisTemplate redisTemplate;


    protected static  Logger log = LoggerFactory.getLogger(RedisBloomFilter.class);

    @Test
    public void test() {
        RedisBloomFilter redisBloomFilter = new RedisBloomFilter();
        int expectedInsertions = 1000;
        double fpp = 0.1;
        redisTemplate.delete("bloom");
        BloomFilterHelper<CharSequence> bloomFilterHelper =
                new BloomFilterHelper<CharSequence>(Funnels.stringFunnel(Charset.defaultCharset()),expectedInsertions,fpp);

        int j =0;
        long begin = System.currentTimeMillis();
        List<String> values = new ArrayList<String>();
        for(int i=0 ; i<100; i++){
            values.add(i+"");
            redisBloomFilter.add(bloomFilterHelper,"bloom",i+"",redisTemplate);
        }

        String value = "1o";
        //todo:批量添加存在问题，存入之后，取不到值
//        redisBloomFilter.addList(bloomFilterHelper,"bloom",values,redisTemplate);


        long cost = System.currentTimeMillis() - begin;
        log.info("布隆过滤器添加{}个值，耗时：{}ms", 100, cost);
        for (int i=0; i<1000;i++){
            boolean result = redisBloomFilter.contain(bloomFilterHelper,"bloom",i+"",redisTemplate);
            if(!result){
                j++;
            }
        }
        log.info("漏掉了{}个,验证结果耗时：{}ms", j, System.currentTimeMillis() - begin);
    }


}
