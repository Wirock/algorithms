package leetcode;

/**
 * 1473. 粉刷房子 III
 * 在一个小城市里，有 m 个房子排成一排，你需要给每个房子涂上 n 种颜色之一（颜色编号为 1 到 n ）。有的房子去年夏天已经涂过颜色了，所以这些房子不需要被重新涂色。
 *
 * 我们将连续相同颜色尽可能多的房子称为一个街区。（比方说 houses = [1,2,2,3,3,2,1,1] ，它包含 5 个街区  [{1}, {2,2}, {3,3}, {2}, {1,1}] 。）
 *
 * 给你一个数组 houses ，一个 m * n 的矩阵 cost 和一个整数 target ，其中：
 *
 * houses[i]：是第 i 个房子的颜色，0 表示这个房子还没有被涂色。
 * cost[i][j]：是将第 i 个房子涂成颜色 j+1 的花费。
 * 请你返回房子涂色方案的最小总花费，使得每个房子都被涂色后，恰好组成 target 个街区。如果没有可用的涂色方案，请返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
 * 输出：9
 * 解释：房子涂色方案为 [1,2,2,1,1]
 * 此方案包含 target = 3 个街区，分别是 [{1}, {2,2}, {1,1}]。
 * 涂色的总花费为 (1 + 1 + 1 + 1 + 5) = 9。
 * 示例 2：
 *
 * 输入：houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
 * 输出：11
 * 解释：有的房子已经被涂色了，在此基础上涂色方案为 [2,2,1,2,2]
 * 此方案包含 target = 3 个街区，分别是 [{2,2}, {1}, {2,2}]。
 * 给第一个和最后一个房子涂色的花费为 (10 + 1) = 11。
 * 示例 3：
 *
 * 输入：houses = [0,0,0,0,0], cost = [[1,10],[10,1],[1,10],[10,1],[1,10]], m = 5, n = 2, target = 5
 * 输出：5
 * 示例 4：
 *
 * 输入：houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3, target = 3
 * 输出：-1
 * 解释：房子已经被涂色并组成了 4 个街区，分别是 [{3},{1},{2},{3}] ，无法形成 target = 3 个街区。
 *
 *
 * 提示：
 *
 * m == houses.length == cost.length
 * n == cost[i].length
 * 1 <= m <= 100
 * 1 <= n <= 20
 * 1 <= target <= m
 * 0 <= houses[i] <= n
 * 1 <= cost[i][j] <= 10^4
 * @author chenzw
 * @date 2021/5/4
 */
public class Solution1473 {
    public static int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int[][][] dp = new int[m+1][n+1][target+1];//dp[i][j][k]前0~i-1的房子,第i-1个是j色,分成k个街区的最小花费
        for(int i=1;i<=n;i++){
            dp[1][i][1] = cost[0][i-1];
        }
        for(int i=2;i<=m;i++){
            for(int j=1;j<=n;j++){
                for(int k=1;k<=target;k++){
                    if(j==houses[i-1]){
                        if(j==houses[i-2]){
                            dp[i][j][k] = dp[i-1][j][k];
                        }else{
                            if(houses[i-2]>0){
                                dp[i][j][k] = dp[i-1][houses[i-2]][k-1];
                            }else{
                                for(int c=1;c<=n;c++){
                                    if(dp[i][j][k]>0){
                                        dp[i][j][k] = Math.min(dp[i][j][k],dp[i-1][c][k-1]);
                                    }else{
                                        dp[i][j][k] = dp[i-1][c][k-1];
                                    }
                                }
                            }
                        }
                    }else{
                        //与前一个相同颜色
                        if(dp[i-1][j][k]>0){
                            dp[i][j][k] = dp[i-1][j][k]+cost[i-1][j-1];
                        }
                        //与前一个不同颜色
                        for(int c=1;c<=n;c++){
                            if(c!=j&&c!=houses[i-1]&&dp[i-1][c][k-1]>0){
                                if(dp[i][j][k]>0){
                                    dp[i][j][k] = Math.min(dp[i][j][k],dp[i-1][c][k-1]+cost[i-1][j-1]);
                                }else{
                                    dp[i][j][k] = dp[i-1][c][k-1]+cost[i-1][j-1];
                                }
                            }
                        }
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i=1;i<=n;i++){
            min = Math.min(min,dp[m][i][target]);
        }
        if(min==0){
            int maxBlocks = 1;
            int minBlocks = 1;
            for(int i=2;i<=m;i++){
                if(houses[i-1]==0||houses[i-1]!=houses[i-2]){
                    maxBlocks++;
                }
                if(houses[i-1]>0&&houses[i-2]>0&&houses[i-1]!=houses[i-2]){
                    minBlocks++;
                }
            }
            if(minBlocks>target||maxBlocks<target){
                return -1;
            }
        }
        return min;
    }


    public static void main(String[] args) {
        System.out.println(minCost(new int[]{0,0,0,0,0},new int[][]{{1,10},{10,1},{10,1},{1,10},{5,1}},5,2,3));
        System.out.println(minCost(new int[]{3,1,2,3},new int[][]{{1,1,1},{1,1,1},{1,1,1},{1,1,1},{1,1,1}},4,3,3));
        //System.out.println(minCost(new int[]{0,0,0},new int[][]{{1,10},{10,1},{10,1},{1,10},{5,1}},3,2,3));
    }

}
