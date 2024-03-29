package leetcode;

/**
 * 375. 猜数字大小 II
 * 我们正在玩一个猜数游戏，游戏规则如下：
 *
 * 我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。
 *
 * 每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。
 *
 * 然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。直到你猜到我选的数字，你才算赢得了这个游戏。
 *
 * 示例:
 *
 * n = 10, 我选择了8.
 *
 * 第一轮: 你猜我选择的数字是5，我会告诉你，我的数字更大一些，然后你需要支付5块。
 * 第二轮: 你猜是7，我告诉你，我的数字更大一些，你支付7块。
 * 第三轮: 你猜是9，我告诉你，我的数字更小一些，你支付9块。
 *
 * 游戏结束。8 就是我选的数字。
 *
 * 你最终要支付 5 + 7 + 9 = 21 块钱。
 * 给定 n ≥ 1，计算你至少需要拥有多少现金才能确保你能赢得这个游戏。
 * @author chenzw
 * @date 2021/11/12
 */
public class Solution375 {
    //dp[i][j]表示猜[i,j]之间的数保证能赢需要的最少现金
    //dp[i][j] = min(k+max(dp[i][k],dp[k][j])) , i<=k<=j
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n+1][n+1];
        for(int len=1;len<n;len++){
            for(int i=1;i<=n-len;i++){
                int k=i+len;
                dp[i][k] = Integer.MAX_VALUE;
                for(int j=i;j<=k;j++){
                    dp[i][k] = Math.min(dp[i][k],j+Math.max(j>i?dp[i][j-1]:0,j<k?dp[j+1][k]:0));
                }
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution375().getMoneyAmount(10));
    }
}
