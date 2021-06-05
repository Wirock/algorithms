package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N 皇后
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[["Q"]]
 *
 *
 * 提示：
 *
 * 1 <= n <= 9
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 * @author chenzw
 * @date 2021/5/1
 */
public class Solution51 {
    private int row;
    private int column;
    private int slash;
    private int backSlash;
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        dfs(ans,new ArrayList<>(),0,n);
        return ans;
    }

    private void dfs(List<List<String>> ans,List<String> list,int index,int n){
        if(index==n){
            ans.add(new ArrayList<>(list));
            return;
        }
        for(int i=0;i<n;i++){
            if(valid(index,i,n)){
                flip(index,i,n);
                list.add(getRowString(i,n));
                dfs(ans,list,index+1,n);
                list.remove(list.size()-1);
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

    private String getRowString(int index,int len){
        char[] chars = new char[len];
        for(int i=0;i<chars.length;i++){
            chars[i] = '.';
        }
        chars[index]='Q';
        return new String(chars);
    }

    public static void main(String[] args) {
        Solution51 s = new Solution51();
        List<List<String>> lists = s.solveNQueens(4);
        for(List<String> l:lists){
            for (String str:l){
                System.out.println(str);
            }
            System.out.println();
        }
    }
}
