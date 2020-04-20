package com.xiaoqiang.bloomfilter.redis;

import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

/*
布隆过滤器核心类
 */
public class BloomFilterHelper<T> {

    private int numHashFunctions;
    private int bitSize;
    private Funnel<T> funnel;

    public BloomFilterHelper(int expectedInsertions){
        this.funnel = (Funnel<T>) Funnels.stringFunnel(Charset.defaultCharset());
        bitSize = optimalNumOfBits(expectedInsertions,0.03);
        numHashFunctions = optimalNumOfHashFunctions(expectedInsertions,bitSize);
    }

    public BloomFilterHelper (Funnel<T> funnel, int expectedInsertions, double fpp){
        this.funnel = funnel;
        bitSize = optimalNumOfBits(expectedInsertions,fpp);
        numHashFunctions = optimalNumOfHashFunctions(expectedInsertions,bitSize);
    }



    public int[] murmurHashOffset(T value){
        int[]  offset = new int[numHashFunctions];
        long base64 = Hashing.murmur3_128().hashObject(value,funnel).asLong();
        int hash1 = (int)base64;
        int hash2 = (int)(base64 >>> 32);
        for (int i=1; i<=numHashFunctions;i++){
            int nextHash = hash1 + i*hash2;
            if(nextHash < 0){
                nextHash = ~nextHash;
            }
            offset[i-1] = nextHash % bitSize;
        }
        return offset;

    }

    /**
     * 计算bit数组长度
     * @param n
     * @param p
     * @return
     */
    private  int optimalNumOfBits(long n, double p){
        if(p == 0){
            p = Double.MIN_VALUE;
        }
        return (int)(-n*Math.log(p)/(Math.log(2)*Math.log(2)));
    }

    /**     * 计算hash方法执行次数     */
    private int optimalNumOfHashFunctions(long n, long m) {
        return (int)Math.max(1,Math.round((double)m/n*Math.log(2)));
    }


    public static void main(String[] args) {
//        double s =  -1000*Math.log(0.1);
//        double f =   Math.log(2)*Math.log(2);
        int s = (int)(-1000*Math.log(0.1)/Math.log(2)*Math.log(2));
        int f =   (int)Math.max(1,Math.round((double)1000/s*Math.log(2)));
        System.out.println("s="+s);
        System.out.println("f="+f);
    }


}
