package com.xiaoqiang.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * 820. 单词的压缩编码
 * 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
 *
 * 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
 *
 * 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
 *
 * 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
 *
 *
 *
 * 示例：
 *
 * 输入: words = ["time", "me", "bell"]
 * 输出: 10
 * 说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
 *
 *
 * 提示：
 *
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * 每个单词都是小写字母 。
 *
 *解题思路
 * 题目意思：字符串中存在该单词子串，并以“#”结尾
 * 1.先把数组中的字符串按长度排序
 * 2，判断短的 是否是长的结尾串， 如果是，就省略掉，用“#”代替，
 * 如果不是就结尾带“#”写到结果中
 */
public class LeetCode820 {

    public static void main(String[] args) {
        String[] words = {"time", "me", "bell"};
        System.out.print(minimumLengthEncoding2(words));
    }
    public static  int minimumLengthEncoding(String[] words) {
        //数组中的字符串按长度排序
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.length() - s1.length();
            }
        });
        //        Arrays.sort(words,(s1, s2) -> s2.length() - s1.length());
        String[] temp = new String[words.length];
        for (int i=0; i<words.length;i++){
            String s = words[i] + "#";
            temp[i] = s;
        }
        String result = "";
        for (String word : temp){
            if(!result.contains(word)){
                result += word;
            }
        }
//        System.out.println(result);
        return result.length();
    }

    public static  int minimumLengthEncoding2(String[] words) {
        Set<String> wordSet = new HashSet(Arrays.asList(words));
        for (String word: words) {
            for (int k = 1; k < word.length(); ++k)
                wordSet.remove(word.substring(k));
        }

        int result = 0;
        for (String word: wordSet)
            result += word.length() + 1;
        return result;
    }




}
