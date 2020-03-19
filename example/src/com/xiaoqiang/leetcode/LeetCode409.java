package com.xiaoqiang.leetcode;

import java.util.HashMap;

/**
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 *
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 *
 * 注意:
 * 假设字符串的长度不会超过 1010。
 *
 * 示例 1:
 *
 * 输入:
 * "abccccdd"
 *
 * 输出:
 * 7
 *
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 *
 *
 * 解题思路
 * 偶数个字符放两边，奇数放中间。
 * 1.查找出字符串中是偶个数的字符， 如果有奇数，先减去-1，加上奇数个数， 在最后的结果在加上1就可以
 */
public class LeetCode409 {

    public static void main(String[] args) {
        String s = "abccccdd";
        System.out.println(longestPalindrome(s));
    }
    public static int longestPalindrome(String s) {
        int maxLength = 0;
        boolean flag = false;  //是否存在偶数
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            if(map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),map.get(s.charAt(i))+1);
            }else{
                map.put(s.charAt(i),1);
            }
        }

        for (Character c : map.keySet()){
            if(map.get(c) % 2 == 0){
                maxLength  = maxLength + map.get(c);
            }else{
                maxLength = maxLength + map.get(c) - 1;
                flag = true;
            }
        }
        if(flag){
            maxLength++;
        }
        return maxLength;
    }
}
