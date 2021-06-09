package leetcode;

/**
 * 518. 零钱兑换 II
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 *
 *
 *
 * 示例 1:
 *
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 示例 2:
 *
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 * 示例 3:
 *
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 *
 *
 * 注意:
 *
 * 你可以假设：
 *
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额) <= 5000
 * 硬币种类不超过 500 种
 * 结果符合 32 位符号整数
 * @author chenzw
 * @date 2021/6/10
 */
public class Solution518 {
    //dp，   f(i,j) = f(i-1,j)+f(i-1,j-coins[i])+...+f(i-1,j-k*coins[i]),f(i,j)表示前i种硬币凑总额j的选择数
    /*public static int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0]=1;
        for(int i=0;i<coins.length;i++){
            for(int j=amount;j>=1;j--){
                for(int k=1;k<=j/coins[i];k++){
                    dp[j]+=dp[j-k*coins[i]];
                }
            }
        }
        return dp[amount];
    }*/
    //优化 f(i,j) = f(i-1,j)+f(i,j-coins[i])  f(i,j)表示前i种硬币凑总额j的选择数
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0]=1;
        for(int i=0;i<coins.length;i++){
            for(int j=coins[i];j<=amount;j++){
                dp[j]+=dp[j-coins[i]];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(change(5,new int[]{1,2,5}));
    }
}
