package com.xiaoqiang.leetcode;

import sun.security.util.Length;

/**
 * 面试题 17.16. 按摩师
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 *
 * 注意：本题相对原题稍作改动
 *
 *
 *
 * 示例 1：
 *
 * 输入： [1,2,3,1]
 * 输出： 4
 * 解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
 * 示例 2：
 *
 * 输入： [2,7,9,3,1]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
 * 示例 3：
 *
 * 输入： [2,1,4,5,3,1,1,3]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
 *
 *
 * 解题思路：
 * 动态规划法  自底而上
 * 动态规划的步骤：设计状态——确定状态转移方程——确定初始值（边界值）——确定输出——（状态是否可压缩）
 *
 *
 */
public class LeetCodeIntefview1617 {
    public static void main(String[] args) {
        int[] a =  {2,1,4,5,3,1,1,3};
            System.out.println(massage(a));
    }
    private static int[] nums1;
    private static int[] storge;

    public static int massage(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length < 2){
            return nums[0];
        }
        if(nums.length < 3){
            return nums[0]>nums[1]?nums[0]:nums[1];
        }
        nums1 = nums;
        storge = new int[nums.length];
        for (int i=0; i< nums.length;i++){
            storge[i] = -1;
        }

        //动态规划
        return dynamicProgramming(nums.length - 1);
    }

    //记忆化之前的方案，与当前方案对比，谁长取谁
    private static  int dynamicProgramming(int index){
        storge[0] = nums1[0];
        storge[1] = nums1[0]>nums1[1]?nums1[0]:nums1[1];
        for(int i=2; i< nums1.length ; i++){
            storge[i] = Math.max(storge[i-1],nums1[i]+storge[i-2]);
        }
        return storge[index];
    }
}
