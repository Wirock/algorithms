package leetcode;

/**
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * 示例 2：
 *
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 * @author chenzw
 * @date 2021/5/28
 */
public class Solution64 {
    public static int minPathSum(int[][] grid) {
        int[] dp = new int[grid[0].length];
        dp[0] = grid[0][0];
        for(int i=1;i<dp.length;i++){
            dp[i] = dp[i-1]+grid[0][i];
        }
        for(int i=1;i<grid.length;i++){
            dp[0] = dp[0]+grid[i][0];
            for(int j=1;j<grid[0].length;j++){
                dp[j] = grid[i][j]+Math.min(dp[j-1],dp[j]);
            }
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        System.out.println(minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
        System.out.println(minPathSum(new int[][]{{1,2,3},{4,5,6}}));
    }
}
