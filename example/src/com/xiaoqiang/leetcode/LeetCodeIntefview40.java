package com.xiaoqiang.leetcode;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * 面试题40. 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *
 *
 * 限制：
 *
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 *
 *
 * 解题思路:
 * 先用桶排序，再取k个数
 * */
public class LeetCodeIntefview40 {


    public static void main(String[] args) {
        int[] arr =  {3,2,1};
        int k = 2;

        int[] s = getLeastNumbers(arr,k);
        for (int i: s){
            System.out.println(i);
        }

    }
    public static int[] getLeastNumbers(int[] arr, int k) {
        return bucketSort(arr,k);
    }

    //桶排序，取前k个数
    public static int[] bucketSort(int[] arr,int k){
        int[] result = new int[k];
        ArrayList<LinkedList<Integer>> buckets = new ArrayList<>();
        for(int i=0; i<arr.length; i++){
            buckets.add(new LinkedList<Integer>());
        }
        for(int data : arr){
            int index = getBucketIndex(data);
            insertSort(buckets.get(index), data);
        }

        int index = 0;
        for (LinkedList<Integer> bucket :buckets){
            for (Integer data : bucket){
                if(index < k ){
                    result[index++] = data;
                }else{
                    break;
                }

            }
        }
        return result;
    }

    private static int getBucketIndex(int data) {
        int quotient = data/1000;
        return quotient;
    }

    private static void insertSort(List<Integer> bucket, int data){
        ListIterator<Integer> it = bucket.listIterator();
        boolean insertFlag = true;
        while (it.hasNext()){
            if(data <= it.next()){
                it.previous();
                it.add(data);
                insertFlag = false;
                break;
            }
        }
        if(insertFlag){
            bucket.add(data);
        }


    }




    int[] bucketSort(int[] nums){
        int[] bk = new int[50000 * 2 + 1];
        for(int i = 0; i < nums.length; i++){
            bk[nums[i] + 50000] += 1;
        }
        int ar = 0;
        for(int i = 0; i < bk.length; i++) {
            for (int j = bk[i]; j > 0; j--) {
                nums[ar++] = i - 50000;
            }
        }
        return nums;
    }
}
