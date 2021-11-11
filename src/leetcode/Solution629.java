package leetcode;

import java.util.Arrays;

/**
 * 629. K个逆序对数组
 * 给出两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个逆序对的不同的数组的个数。
 *
 * 逆序对的定义如下：对于数组的第i个和第 j个元素，如果满i < j且 a[i] > a[j]，则其为一个逆序对；否则不是。
 *
 * 由于答案可能很大，只需要返回 答案 mod 109 + 7 的值。
 *
 * 示例 1:
 *
 * 输入: n = 3, k = 0
 * 输出: 1
 * 解释:
 * 只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。
 * 示例 2:
 *
 * 输入: n = 3, k = 1
 * 输出: 2
 * 解释:
 * 数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。
 * 说明:
 *
 *  n 的范围是 [1, 1000] 并且 k 的范围是 [0, 1000]
 * @author chenzw
 * @date 2021/11/11
 */
public class Solution629 {
    //f[i][j]表示使用[1,i]数组，逆序数恰好为j的数组个数
   //考虑当i在第i位上，i后没有数，有j逆序对的数组数是f(i-1,j);
    //当i在第i-1位上，i后又1个比它小的数，有j逆序对的数组数是f(i-1,j-1);
    //...
    //当i在第1位上，i后又i-1个比它小的数，有j逆序对的数组数是f(i-1,j-(i-1));
    //f(i,j)为上述i中情况的和

    //令dp[i][j]=f(i,j),
    //sum[i][j] = sum(f(i,j)),
    //有dp[i][j]=sum[i-1][j]-sum[i-1][j-(i-1)-1]
    int mod = (int)1e9+7;
    public int kInversePairs(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        int[][] sum = new int[n + 1][k + 1];
        dp[1][0] = 1;
        Arrays.fill(sum[1], 1);
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = j < i ? sum[i - 1][j] : (sum[i - 1][j] - sum[i - 1][j - (i - 1) - 1] + mod) % mod;
                sum[i][j] = j == 0 ? dp[i][j] : (sum[i][j - 1] + dp[i][j]) % mod;
            }
        }
        return dp[n][k];
    }

}
