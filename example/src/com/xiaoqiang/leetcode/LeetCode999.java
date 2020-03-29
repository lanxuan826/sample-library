package com.xiaoqiang.leetcode;

import jdk.nashorn.internal.objects.annotations.Where;

/**
 * 999. 车的可用捕获量
 * 在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。
 *
 * 车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。
 *
 * 返回车能够在一次移动中捕获到的卒的数量。
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","R",".",".",".","p"],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
 * 输出：3
 * 解释：
 * 在本例中，车能够捕获所有的卒。
 * 示例 2：
 *
 *
 *
 * 输入：[[".",".",".",".",".",".",".","."],[".","p","p","p","p","p",".","."],[".","p","p","B","p","p",".","."],[".","p","B","R","B","p",".","."],[".","p","p","B","p","p",".","."],[".","p","p","p","p","p",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
 * 输出：0
 * 解释：
 * 象阻止了车捕获任何卒。
 * 示例 3：
 *
 *
 *
 * 输入：[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","p",".",".",".","."],["p","p",".","R",".","p","B","."],[".",".",".",".",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."]]
 * 输出：3
 * 解释：
 * 车可以捕获位置 b5，d6 和 f5 的卒。
 *
 *
 * 提示：
 *
 * board.length == board[i].length == 8
 * board[i][j] 可以是 'R'，'.'，'B' 或 'p'
 * 只有一个格子上存在 board[i][j] == 'R'
 *
 *
 * 解题思路；
 * 1.先找到白车位置
 * 如果遇到白色的象（B，友方），这个时候要停止，因为不能同时在同一个方格中；
 * 如果遇到黑色的卒（p，敌方），说明找到捕获对象，返回值 + 1；
 * 如果遇到的 .（在这里表示空的棋格），表示可以继续移动；
 */
public class LeetCode999 {

    public static void main(String[] args) {
            char[][] abs =   {{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','R','.','.','.','p'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'}};
            System.out.println(numRookCaptures(abs));
    }
    public static int numRookCaptures(char[][] board) {

        int result = 0;
        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                if(board[i][j] == 'R'){
                    //向四个方向移动
                    for (int x=0;x<directions.length;x++){
                            int nx = directions[x][0] +i;
                            int ny = directions[x][1] +j;
                            while (nx >= 0 && nx <8 && ny>=0 && ny < 8 ){
                                    if(board[nx][ny] == 'p'){
                                        result++;
                                        break;
                                    }
                                    if(board[nx][ny] == 'B'){
                                        break;
                                    }
                                    if(directions[x][0] == 0 && directions[x][1] == 1){
                                        ny++;
                                    } else if(directions[x][0] == 0 && directions[x][1] == -1){
                                        ny--;
                                    }else if(directions[x][0] == -1 && directions[x][1] == 0){
                                        nx--;
                                    } else {
                                        nx++;
                                    }
                            }
                    }
                }
            }
        }

    return  result;
    }
}