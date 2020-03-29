package com.xiaoqiang.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1162. 地图分析
 * 你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。
 *
 * 我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。
 *
 * 如果我们的地图上只有陆地或者海洋，请返回 -1。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：[[1,0,1],[0,0,0],[1,0,1]]
 * 输出：2
 * 解释：
 * 海洋区域 (1, 1) 和所有陆地区域之间的距离都达到最大，最大距离为 2。
 * 示例 2：
 *
 *
 *
 * 输入：[[1,0,0],[0,0,0],[0,0,0]]
 * 输出：4
 * 解释：
 * 海洋区域 (2, 2) 和所有陆地区域之间的距离都达到最大，最大距离为 4。
 *
 *
 * 提示：
 *
 * 1 <= grid.length == grid[0].length <= 100
 * grid[i][j] 不是 0 就是 1
 *
 * 解题思路：
 * 先把所有陆地位置都找出，再遍历每个陆地到海洋的距离，得到最长距离
 * 多源最长距离
 */
public class LeetCode1162 {
    public static void main(String[] args) {
       // int[][] grid = {{1,0,0},{0,0,0},{0,0,0}};
        int[][] grid = {{1,0,1},{0,1,1},{1,1,1}};
        System.out.println(maxDistance(grid));
    }

    public static int maxDistance(int[][] grid) {
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        Queue<int[]> queue = new ArrayDeque<>();
        int x = grid.length, y = grid[0].length;
        for (int i=0; i<x; i++){  //找出所有陆地=1坐标
            for (int j=0; j<y; j++){
                if(grid[i][j] == 1){
                    queue.add(new int[]{i,j});
                }
            }
        }
        //从各个陆地开始，一圈一圈的遍历海洋，最后遍历到的海洋就是离陆地最远的海洋。
        boolean ocean = false;
        int[] point = null;
        while (!queue.isEmpty()){
            point = queue.poll();
            int m = point[0],n = point[1];
            for (int i=0; i<4; i++){
                int newX = m + dx[i];
                int newY = n + dy[i];
                if(newX < 0 || newX >= x || newY < 0 || newY >= y || grid[newX][newY] != 0){
                    continue;
                }
                grid[newX][newY] = grid[m][n] + 1;
                ocean = true;
                queue.offer(new int[]{newX,newY});
            }
        }

        if(point == null || !ocean ){
            return -1;
        }

        return grid[point[0]][point[1]] -1 ;
    }
}
