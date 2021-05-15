package leetcode;

/**
 * 5212. 向下取整数对和
 * 给你一个整数数组 nums ，请你返回所有下标对 0 <= i, j < nums.length 的 floor(nums[i] / nums[j]) 结果之和。由于答案可能会很大，请你返回答案对109 + 7 取余 的结果。
 *
 * 函数 floor() 返回输入数字的整数部分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,5,9]
 * 输出：10
 * 解释：
 * floor(2 / 5) = floor(2 / 9) = floor(5 / 9) = 0
 * floor(2 / 2) = floor(5 / 5) = floor(9 / 9) = 1
 * floor(5 / 2) = 2
 * floor(9 / 2) = 4
 * floor(9 / 5) = 1
 * 我们计算每一个数对商向下取整的结果并求和得到 10 。
 * 示例 2：
 *
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：49
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * @author chenzw
 * @date 2021/5/16
 */
public class Solution5212 {
    //桶计数+前缀和
    public static int sumOfFlooredPairs(int[] nums) {
        int mod = (int)1e9+7;
        int [] sum = new int[(int)2e5+1];
        int max = 0;
        for(int i:nums){
            max = Math.max(max,i);
            sum[i]++;
        }
        for(int i=1;i<=2*max;i++){
            sum[i] += sum[i-1];
        }
        int ans = 0;
        for(int i:nums){
            for(int time=1;time<=max/i;time++){
                ans = (ans+(sum[i*time+i-1]-sum[i*time-1])*time)%mod;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
       System.out.println(sumOfFlooredPairs(new int[]{2,5,9}));
        System.out.println(sumOfFlooredPairs(new int[]{7,7,7,7,7,7,7}));
        System.out.println(sumOfFlooredPairs(new int[]{4,3,4,3,5}));
    }
}
