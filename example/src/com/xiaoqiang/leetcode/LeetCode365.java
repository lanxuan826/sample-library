package com.xiaoqiang.leetcode;

/**
 * 365. 水壶问题
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 *
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 *
 * 你允许：
 *
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * 示例 1: (From the famous "Die Hard" example)
 *
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 * 示例 2:
 *
 * 输入: x = 2, y = 6, z = 5
 * 输出: False
 *
 *
 * 思路：
 *如果两个数互质，那么可以得到任意一个小于等于x+y的数
 * 如果不互质，那么只能得到他们最大公约数的倍数（小于等于x+y
 */
public class LeetCode365 {


    public static void main(String[] args) {
        int x=13,y=15,z=14;
        System.out.println(canMeasureWater(x,y,z));
    }
    public static boolean canMeasureWater(int x, int y, int z) {
            if(z > x+y){
                return false;
            }
            if(x == 0 && y==0) {
                return z==0;
            }
            return z%isGreatestCommonDivisorOfMultiple(x,y)==0;
    }
    //辗转相除法 获取最大公约数
    private  static int isGreatestCommonDivisorOfMultiple(int x, int y){
        System.out.println("x="+x+",y="+y);
        return y==0? x: isGreatestCommonDivisorOfMultiple(y, x%y);
    }
}
