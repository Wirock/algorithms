package leetcode;

/**
 * 123. 买卖股票的最佳时机 III
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 *
 * 示例 1:
 *
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2：
 *
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3：
 *
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 * 示例 4：
 *
 * 输入：prices = [1]
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 105
 * @author chenzw
 * @date 2021/6/23
 */
public class Solution123 {
    //思路:dp算出子区间内最多购买一次能获取的最大利润，任意两个不重叠子区间的最大利润和即为所求
    /*public int maxProfit(int[] prices) {
        int[] dp1 = new int[prices.length];
        int[] dp2 = new int[prices.length];
        int min = prices[0];
        for(int i=1;i<prices.length;i++){
            dp1[i] = Math.max(dp1[i-1],prices[i]-min);
            min = Math.min(min,prices[i]);
        }
        int max = prices[prices.length-1];
        for(int i=prices.length-2;i>0;i--){
            dp2[i] = Math.max(dp2[i+1],max-prices[i]);
            max = Math.max(max,prices[i]);
        }
        int ans = Math.max(dp1[prices.length-1],dp2[0]);
        for(int i=1;i<prices.length-2;i++){
            ans = Math.max(ans,dp1[i]+dp2[i+1]);
        }
        return ans;
    }*/

    //优化1,合并到一个循环
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        int min = prices[0];
        int max = prices[n-1];
        for(int i=1;i<n;i++){
            dp1[i] = Math.max(dp1[i-1],prices[i]-min);
            min = Math.min(min,prices[i]);
            dp2[n-1-i] = Math.max(dp2[i+1],max-prices[i]);
            max = Math.max(max,prices[i]);
        }
        int ans = Math.max(dp1[prices.length-1],dp2[0]);
        for(int i=1;i<prices.length-2;i++){
            ans = Math.max(ans,dp1[i]+dp2[i+1]);
        }
        return ans;
    }
}
