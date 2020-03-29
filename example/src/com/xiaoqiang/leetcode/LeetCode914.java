package com.xiaoqiang.leetcode;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 914. 卡牌分组
 * 给定一副牌，每张牌上都写着一个整数。
 *
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 *
 * 每组都有 X 张牌。
 * 组内所有的牌上都写着相同的整数。
 * 仅当你可选的 X >= 2 时返回 true。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[1,2,3,4,4,3,2,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
 * 示例 2：
 *
 * 输入：[1,1,1,2,2,2,3,3]
 * 输出：false
 * 解释：没有满足要求的分组。
 * 示例 3：
 *
 * 输入：[1]
 * 输出：false
 * 解释：没有满足要求的分组。
 * 示例 4：
 *
 * 输入：[1,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]
 * 示例 5：
 *
 * 输入：[1,1,2,2,2,2]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[2,2]
 *
 * 提示：
 *
 * 1 <= deck.length <= 10000
 * 0 <= deck[i] < 10000
 *
 *
 * 思路：
 * 先统计次数个数， 取出个数最少的x，个数%x==0
 */
public class LeetCode914 {

    public static void main(String[] args) {
        int[] a = {1,2,3,4,4,3,2,1};
        System.out.println(hasGroupsSizeX(a));
    }

    public static boolean hasGroupsSizeX(int[] deck) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i: deck){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        int minSize = Integer.MAX_VALUE;
        for (int i: map.values()){
            minSize = Math.min(minSize,i);
        }
        int i=2;
        boolean flag = false;
        while ( i <= minSize && !flag){
            boolean sign = true;
            for (int x: map.values()){
                if(x % i != 0 ){
                    sign = false;
                    break;
                }
            }
            if(sign) return true;
            i++;
        }
        return false;


    }


}
