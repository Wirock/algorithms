package leetcode;

/**
 * 52. N皇后 II
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 4
 * 输出：2
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= n <= 9
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 * @author chenzw
 * @date 2021/5/1
 */
public class Solution52 {
    private int row;
    private int column;
    private int slash;
    private int backSlash;
    private int count;
    public int totalNQueens(int n) {
        count = 0;
        dfs(0,n);
        return count;
    }

    private void dfs(int index,int n){
        if(index==n){
            count++;
            return;
        }
        for(int i=0;i<n;i++){
            if(valid(index,i,n)){
                flip(index,i,n);
                dfs(index+1,n);
                flip(index,i,n);
            }
        }
    }

    private boolean valid(int rowIndex,int columnIndex,int n){
        if((row|1<<rowIndex)==row)return false;
        if((column|1<<columnIndex)==column)return false;
        if((slash|1<<(rowIndex+columnIndex))==slash)return false;
        if((backSlash|1<<(rowIndex+n-1-columnIndex))==backSlash)return false;
        return true;
    }

    private void flip(int rowIndex,int columnIndex,int n){
        row ^= 1<<rowIndex;
        column ^= 1<<columnIndex;
        slash ^= 1<<(rowIndex+columnIndex);
        backSlash ^= 1<<(rowIndex+n-1-columnIndex);
    }

    public static void main(String[] args) {
        Solution52 s = new Solution52();
        System.out.println(s.totalNQueens(1));
        System.out.println(s.totalNQueens(2));
        System.out.println(s.totalNQueens(3));
        System.out.println(s.totalNQueens(4));
        System.out.println(s.totalNQueens(5));
        System.out.println(s.totalNQueens(6));
        System.out.println(s.totalNQueens(7));
        System.out.println(s.totalNQueens(8));
        System.out.println(s.totalNQueens(9));
        System.out.println(s.totalNQueens(10));
        System.out.println(s.totalNQueens(11));
    }
}
