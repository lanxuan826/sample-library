package com.xiaoqiang.bloomfilter.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

public class RedisBloomFilter<T> {

//    @Autowired
//    private RedisTemplate redisTemplate;

    public static  RedisTemplate redisTemplate = new RedisTemplate();

    public void delete(String key){
        redisTemplate.delete(key);
    }

    /**
     * 根据给定的布隆过滤器添加值，
     * @param bloomFilterHelper
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void add(BloomFilterHelper<T> bloomFilterHelper, String key, T value,  RedisTemplate redisTemplate){
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for(int i: offset){
            redisTemplate.opsForValue().setBit(key,i,true);
        }
    }

    /**
     * 根据给定的布隆过滤器批量添加值，批量添加的性能好，使用pipeline方式(如果是集群下，请使用优化后RedisPipeline的操作
     *
     * @param bloomFilterHelper
     * @param key
     * @param values
     * @param <T>
     */
    public <T> void addList(final BloomFilterHelper<T> bloomFilterHelper, final String key, final List<T> values, RedisTemplate redisTemplate){

        redisTemplate.executePipelined(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.openPipeline();
                 for (T value: values){
                            int[] offsert = bloomFilterHelper.murmurHashOffset(value);
                            for (int i:offsert){
                                redisConnection.setBit("key".getBytes(),i,true);
                                System.out.println("value="+value+"===,i="+i);
                            }
                 }
                redisConnection.closePipeline();
             return null;
            }
        });
    }

    public <T> boolean contain(BloomFilterHelper<T> bloomFilterHelper, String key, T value,RedisTemplate redisTemplate){
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset){
            System.out.println("value="+value+"===,i="+i);
            if(!redisTemplate.opsForValue().getBit(key,i)){
                return false;
            }
        }
        return true;
    }
}
