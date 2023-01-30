package leetcode.weekly.contest309;

/**
 * 6168. 恰好移动 k 步到达某一位置的方法数目
 * 给你两个 正 整数 startPos 和 endPos 。最初，你站在 无限 数轴上位置 startPos 处。在一步移动中，你可以向左或者向右移动一个位置。
 *
 * 给你一个正整数 k ，返回从 startPos 出发、恰好 移动 k 步并到达 endPos 的 不同 方法数目。由于答案可能会很大，返回对 109 + 7 取余 的结果。
 *
 * 如果所执行移动的顺序不完全相同，则认为两种方法不同。
 *
 * 注意：数轴包含负整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：startPos = 1, endPos = 2, k = 3
 * 输出：3
 * 解释：存在 3 种从 1 到 2 且恰好移动 3 步的方法：
 * - 1 -> 2 -> 3 -> 2.
 * - 1 -> 2 -> 1 -> 2.
 * - 1 -> 0 -> 1 -> 2.
 * 可以证明不存在其他方法，所以返回 3 。
 * 示例 2：
 *
 * 输入：startPos = 2, endPos = 5, k = 10
 * 输出：0
 * 解释：不存在从 2 到 5 且恰好移动 10 步的方法。
 *
 *
 * 提示：
 *
 * 1 <= startPos, endPos, k <= 1000
 * Created by Chenzw on 2022/9/4 13:50
 */
public class Solution6168 {
    //dp
    public int numberOfWays(int startPos, int endPos, int k) {
        final int MOD = (int)1e9+7;
        int n = Math.max(startPos,endPos)+2*k+3;
        int[][] dp = new int[n][k+1];
        dp[k+startPos+1][0] = 1;
        for(int j=1;j<=k;j++){
            for(int i=1;i<n-1;i++){
                dp[i][j] = (dp[i-1][j-1]+dp[i+1][j-1])%MOD;
            }
        }
        return dp[endPos+k+1][k];
    }
}
