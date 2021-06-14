package leetcode;

/**
 * 1895. 最大的幻方
 * 一个 k x k 的 幻方 指的是一个 k x k 填满整数的方格阵，且每一行、每一列以及两条对角线的和 全部相等 。幻方中的整数 不需要互不相同 。显然，每个 1 x 1 的方格都是一个幻方。
 *
 * 给你一个 m x n 的整数矩阵 grid ，请你返回矩阵中 最大幻方 的 尺寸 （即边长 k）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
 * 输出：3
 * 解释：最大幻方尺寸为 3 。
 * 每一行，每一列以及两条对角线的和都等于 12 。
 * - 每一行的和：5+1+6 = 5+4+3 = 2+7+3 = 12
 * - 每一列的和：5+5+2 = 1+4+7 = 6+3+3 = 12
 * - 对角线的和：5+4+3 = 6+4+2 = 12
 * 示例 2：
 *
 *
 * 输入：grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
 * 输出：2
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j] <= 106
 * @author chenzw
 * @date 2021/6/14
 */
public class Solution1895 {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int len = Math.min(m,n);
        long[][] rowSum = new long[m][n+1];
        long[][] colSum = new long[n][m+1];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                rowSum[i][j+1]=rowSum[i][j]+grid[i][j];
                colSum[j][i+1]=colSum[j][i]+grid[i][j];
            }
        }
        while(len>0){
            for(int i=0;i<=m-len;i++){
                for(int j=0;j<=n-len;j++){
                    boolean flag = false;
                    long r = rowSum[i][j+len]-rowSum[i][j];
                    for(int k=i+1;k<i+len;k++){
                        if(r!=rowSum[k][j+len]-rowSum[k][j]) {
                            flag=true;
                            break;
                        }
                    }
                    if(flag)continue;
                    long c = colSum[j][i+len]-colSum[j][i];
                    if(r!=c)continue;
                    for(int k=j+1;k<j+len;k++){
                        if(c!=colSum[k][i+len]-colSum[k][i]){
                            flag=true;
                            break;
                        }
                    }
                    if(flag)continue;
                    if(r!=c)continue;
                    long slash=0;
                    long backSlash=0;
                    for(int l=0;l<len;l++){
                        slash+=grid[i+l][j+l];
                        backSlash+=grid[i+l][j+len-1-l];
                    }
                    if(slash==backSlash&&slash==r)return len;
                }
            }
            len--;
        }
        return 1;
    }
}
