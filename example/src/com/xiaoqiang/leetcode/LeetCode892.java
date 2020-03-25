package com.xiaoqiang.leetcode;

import java.util.ArrayDeque;

/**
 * 892. 三维形体的表面积
 * 在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
 *
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
 *
 * 请你返回最终形体的表面积。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[[2]]
 * 输出：10
 * 示例 2：
 *
 * 输入：[[1,2],[3,4]]
 * 输出：34
 * 示例 3：
 *
 * 输入：[[1,0],[0,2]]
 * 输出：16
 * 示例 4：
 *
 * 输入：[[1,1,1],[1,0,1],[1,1,1]]
 * 输出：32
 * 示例 5：
 *
 * 输入：[[2,2,2],[2,1,2],[2,2,2]]
 * 输出：46
 *
 *
 * 提示：
 *
 * 1 <= N <= 50
 * 0 <= grid[i][j] <= 50
 * 通过次数21,877提交次数34,
 *
 * 解题思路：
 * 单个柱体：上下底面+4侧面
 * 把柱体贴合在一起之后，我们需要把贴合的表面积给减掉，两个柱体贴合的表面积就是 两个柱体高的最小值*2。
 */
public class LeetCode892 {
    public static void main(String[] args) {
        int[][] a = {{1,0},{0,2}};
        System.out.print(surfaceArea(a));
    }
    public static  int surfaceArea(int[][] grid) {

        int area = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]!=0)
                    //假设每个v=grid[i][j]都是独立的。
                    area+=grid[i][j]*4+2;
                //减去面贴在一起的情况
                if (i>0)
                    area-=Math.min(grid[i-1][j], grid[i][j])*2;
                if (j>0)
                    area-=Math.min(grid[i][j-1], grid[i][j])*2;
            }
        }
        return area;

    }
}
