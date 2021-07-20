package leetcode;

/**
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * @author chenzw
 * @date 2021/7/21
 */
public class Solution152 {
    //dp，dp[i]表示因子包含第i位的最大乘积
    //因为因子有正有负，故每一步需同时记录最大与最小值
    /*public int maxProduct(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int n = nums.length;
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        dp1[0] = nums[0];
        dp2[0] = nums[0];
        for(int i=1;i<n;i++){
            dp1[i] = Math.max(nums[i],Math.max(dp2[i-1]*nums[i],dp1[i-1]*nums[i]));
            dp2[i] = Math.min(nums[i],Math.min(dp2[i-1]*nums[i],dp1[i-1]*nums[i]));
        }
        for(int i:dp1){
            ans = Math.max(ans,i);
        }
        return ans;
    }*/
    //空间优化
    public int maxProduct(int[] nums) {
        int ans = nums[0];
        int n = nums.length;
        int dp1 = nums[0];
        int dp2 = nums[0];
        for(int i=1;i<n;i++){
            int temp = dp1;
            dp1 = Math.max(nums[i],Math.max(dp2*nums[i],dp1*nums[i]));
            dp2 = Math.min(nums[i],Math.min(dp2*nums[i],temp*nums[i]));
            ans = Math.max(ans,dp1);
        }
        return ans;
    }
}
