package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 *
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 示例 2：
 *
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *
 * 提示：
 *
 * 1 <= n <= 104
 * @author chenzw
 * @date 2021/6/11
 */
public class Solution279 {
    //转化为多重背包问题，动态规划处理
    public static int numSquares(int n) {
        List<Integer> candidates = new ArrayList<>();
        int num=1;
        while(num*num<=n){
            candidates.add(num*num);
            num++;
        }
        //f(i,j)表示前i项凑出j的最少选择数量f(i,j) = min(f(i-1,j),f(i,j-candidates[i])+1)
        int[] dp = new int[n+1];
        for(int i=1;i<dp.length;i++){
            dp[i] = Integer.MAX_VALUE;
        }
        for(int i=0;i<candidates.size();i++){
            for(int j=candidates.get(i);j<=n;j++){
                //dp[j] = Math.min(dp[j],(dp[j-candidates.get(i)]==Integer.MAX_VALUE?0:dp[j-candidates.get(i)])+1);
                dp[j] = Math.min(dp[j],dp[j-candidates.get(i)]+1);//因为可选项有1，所以已经遍历过的dp[j]一定小于Integer.MAX_VALUE
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numSquares(12));
        System.out.println(numSquares(13));
    }
}
