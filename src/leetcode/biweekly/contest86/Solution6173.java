package leetcode.biweekly.contest86;

/**
 * 6173. 被列覆盖的最多行数
 * 给你一个下标从 0 开始的 m x n 二进制矩阵 mat 和一个整数 cols ，表示你需要选出的列数。
 *
 * 如果一行中，所有的 1 都被你选中的列所覆盖，那么我们称这一行 被覆盖 了。
 *
 * 请你返回在选择 cols 列的情况下，被覆盖 的行数 最大 为多少。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：mat = [[0,0,0],[1,0,1],[0,1,1],[0,0,1]], cols = 2
 * 输出：3
 * 解释：
 * 如上图所示，覆盖 3 行的一种可行办法是选择第 0 和第 2 列。
 * 可以看出，不存在大于 3 行被覆盖的方案，所以我们返回 3 。
 * 示例 2：
 *
 *
 *
 * 输入：mat = [[1],[0]], cols = 1
 * 输出：2
 * 解释：
 * 选择唯一的一列，两行都被覆盖了，原因是整个矩阵都被覆盖了。
 * 所以我们返回 2 。
 *
 *
 * 提示：
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 12
 * mat[i][j] 要么是 0 要么是 1 。
 * 1 <= cols <= n
 * Created by Chenzw on 2022/9/4 0:22
 */
public class Solution6173 {

    //二进制枚举
    /*public int maximumRows(int[][] mat, int cols) {
        int m = mat.length;
        int n = mat[0].length;
        int[] ma = new int[m];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                ma[i] = ma[i]|(mat[i][j]<<j);
            }
        }
        int ans = 0;
        for(int i=0;i<(1<<n);i++){
            if(Integer.bitCount(i)==cols){
                int count = 0;
                for(int j:ma){
                    if((j&i)==j){
                        count++;
                    }
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }*/

    //dfs枚举
    int m;
    int n;
    int[] ma;
    int cols;
    int ans = 0;
    public int maximumRows(int[][] mat, int cols) {
        this.m = mat.length;
        this.n = mat[0].length;
        this.cols = cols;
        ma = new int[m];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                ma[i] = ma[i]|(mat[i][j]<<j);
            }
        }
        int[] choose = new int[n];
        dfs(choose, 0, 0);
        return ans;
    }
    private void dfs(int[] choose, int index, int count){
        if(count==cols){
            int mask = 0;
            for(int i=0;i<n;i++){
                mask = mask|(choose[i]<<i);
            }
            int c = 0;
            for(int i=0;i<m;i++){
                if((ma[i]&mask)==ma[i]){
                    c++;
                }
            }
            ans = Math.max(ans,c);
            return;
        }
        if(index==n){
            return;
        }
        dfs(choose, index+1,count);
        choose[index] = 1;
        dfs(choose, index+1,count+1);
        choose[index] = 0;
    }

    public static void main(String[] args) {
        System.out.println(Integer.bitCount(3));
    }
}
