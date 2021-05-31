package leetcode;

/**
 * 63. 不同路径 II
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 *
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * 示例 2：
 *
 *
 * 输入：obstacleGrid = [[0,1],[0,0]]
 * 输出：1
 *
 *
 * 提示：
 *
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] 为 0 或 1
 * @author chenzw
 * @date 2021/5/28
 */
public class Solution63 {
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[] dp = new int[obstacleGrid[0].length];
        dp[0] = 1-obstacleGrid[0][0];
        for(int j=1;j<obstacleGrid[0].length;j++){
            if(obstacleGrid[0][j]==0){
                dp[j]=dp[j-1];
            }
        }
        for(int i=1;i<obstacleGrid.length;i++){
            if(obstacleGrid[i][0]==1){
                dp[0]=0;
            }else{
                dp[0]=dp[0];
            }
            for(int j=1;j<obstacleGrid[i].length;j++){
                if(obstacleGrid[i][j]==1){
                    dp[j]=0;
                }else{
                    dp[j]=dp[j-1]+dp[j];
                }
            }
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePathsWithObstacles(new int[][]{{0,0,0},{0,1,0},{0,0,0}}));
        System.out.println(uniquePathsWithObstacles(new int[][]{{1,0}}));
    }
}
