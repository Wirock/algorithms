package leetcode.biweekly.contest55;

/**
 * 5782. 最大子序列交替和 显示英文描述
 * 通过的用户数0
 * 尝试过的用户数0
 * 用户总通过次数0
 * 用户总提交次数0
 * 题目难度Medium
 * 一个下标从 0 开始的数组的 交替和 定义为 偶数 下标处元素之 和 减去 奇数 下标处元素之 和 。
 *
 * 比方说，数组 [4,2,5,3] 的交替和为 (4 + 5) - (2 + 3) = 4 。
 * 给你一个数组 nums ，请你返回 nums 中任意子序列的 最大交替和 （子序列的下标 重新 从 0 开始编号）。
 *
 * 一个数组的 子序列 是从原数组中删除一些元素后（也可能一个也不删除）剩余元素不改变顺序组成的数组。比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的一个子序列（加粗元素），但是 [2,4,2] 不是。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,2,5,3]
 * 输出：7
 * 解释：最优子序列为 [4,2,5] ，交替和为 (4 + 5) - 2 = 7 。
 * 示例 2：
 *
 * 输入：nums = [5,6,7,8]
 * 输出：8
 * 解释：最优子序列为 [8] ，交替和为 8 。
 * 示例 3：
 *
 * 输入：nums = [6,2,1,2,4,5]
 * 输出：10
 * 解释：最优子序列为 [6,1,5] ，交替和为 (6 + 5) - 1 = 10 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * @author chenzw
 * @date 2021/6/27
 */
public class Solution5782 {

    //dp o(n^2)超时
    /*public long maxAlternatingSum(int[] nums) {
        int n=nums.length;
        long[] dp = new long[n+1];
        for(int i=1;i<=n;i++){
            for(int len=i;len>0;len--){
                dp[len]=Math.max(dp[len],dp[len-1]+(len%2==0?-nums[i-1]:nums[i-1]));
            }
        }
        long ans = 0;
        for(int len=1;len<=n;len++){
            ans = Math.max(ans,dp[len]);
        }
        return ans;
    }*/
    public long maxAlternatingSum(int[] nums) {
        int n=nums.length;
        long[] dp1 = new long[n+1];//dp1[i]表示从前i个数中选出奇数个元素能组成的最大值
        long[] dp2 = new long[n+1];//dp2[i]表示从前i个数中选出偶数个元素能组成的最大值
        for(int i=1;i<=n;i++){
            dp1[i]=Math.max(dp1[i-1],dp2[i-1]+nums[i-1]);
            dp2[i]=Math.max(dp2[i-1],dp1[i-1]-nums[i-1]);
        }
        return Math.max(dp1[n],dp2[n]);
    }
}
