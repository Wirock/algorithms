package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 130. 被围绕的区域
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 *
 * 示例 1：
 *
 *
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * 示例 2：
 *
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 *
 *
 * 提示：
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 * @author chenzw
 * @date 2021/6/30
 */
public class Solution130 {

    //把边界O的连通区域搜索标记出来，剩余的O就是要改变的，改变完后再把标记的O恢复
    /*public static void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for(int i=0;i<m;i++){
            if(board[i][0]=='O')dfs(board,i,0);
            if(board[i][n-1]=='O')dfs(board,i,n-1);
        }
        for(int i=0;i<n;i++){
            if(board[0][i]=='O')dfs(board,0,i);
            if(board[m-1][i]=='O')dfs(board,m-1,i);
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='O') {
                    board[i][j]='X';
                }else if(board[i][j]=='.'){
                    board[i][j]='O';
                }
            }
        }
    }
    private static void dfs(char[][] board,int r,int c){
        if(r<0||c<0||r>board.length-1||c>board[0].length-1)return;
        if(board[r][c]!='O')return;
        board[r][c]='.';
        dfs(board,r+1,c);
        dfs(board,r-1,c);
        dfs(board,r,c+1);
        dfs(board,r,c-1);
    }*/
    public static void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<m;i++){
            if(board[i][0]=='O')queue.add(new int[]{i,0});
            if(board[i][n-1]=='O')queue.add(new int[]{i,n-1});
        }
        for(int i=1;i<n-1;i++){
            if(board[0][i]=='O')queue.add(new int[]{0,i});
            if(board[m-1][i]=='O')queue.add(new int[]{m-1,i});
        }

        //bfs(queue,board);
        while(!queue.isEmpty()){
            int[] index = queue.poll();
            int r = index[0];
            int c = index[1];
            if(board[r][c]!='O')continue;
            board[r][c]='.';
            if(r>0)queue.add(new int[]{r-1,c});
            if(r<board.length-1)queue.add(new int[]{r+1,c});
            if(c>0)queue.add(new int[]{r,c-1});
            if(c<board[0].length-1)queue.add(new int[]{r,c+1});
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='O') {
                    board[i][j]='X';
                }else if(board[i][j]=='.'){
                    board[i][j]='O';
                }
            }
        }
    }
    /*private static void bfs(Queue<int[]> queue, char[][] board){
        if(queue.isEmpty())return;
        int[] index = queue.poll();
        int r = index[0];
        int c = index[1];
        if(board[r][c]!='O')return;
        board[r][c]='.';
        if(r<board.length-1)queue.add(new int[]{r+1,c});
        if(r>0)queue.add(new int[]{r-1,c});
        if(c<board[0].length)queue.add(new int[]{r,c+1});
        if(c>0)queue.add(new int[]{r,c-1});
        bfs(queue,board);
    }*/
    public static void main(String[] args) {
        char[][] board;
        board = new char[][]{{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        solve(board);
        for(char[] c:board){
            System.out.println(Arrays.toString(c));
        }
        board = new char[][]{{'O','O','O'},{'O','O','O'},{'O','O','O'}};
        solve(board);
        for(char[] c:board){
            System.out.println(Arrays.toString(c));
        }
    }
}
