package com.xiaoqiang.leetcode;

/**
 * 151. 翻转字符串里的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 *
 *
 * 示例 1：
 *
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 *
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 *
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 *
 * 说明：
 *
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * 思路：
 * 先分解成数组，然后倒序输出
 */
public class LeetCode151 {

    public static void main(String[] args) {
        String s = "the sky is blue";
        String res = reverseWords(s);
        System.out.println(reverseWords(res));
    }
    public static  String reverseWords(String s) {
        String[] str = s.trim().split(" +");
        StringBuffer sb = new StringBuffer();
        for (int i=str.length-1; i>=0; i--){
            sb.append(str[i]+" ");
        }
        return sb.toString().trim();
    }
}
