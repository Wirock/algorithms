package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 377. 组合总和 Ⅳ
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 *
 * 题目数据保证答案符合 32 位整数范围。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * 示例 2：
 *
 * 输入：nums = [9], target = 3
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * nums 中的所有元素 互不相同
 * 1 <= target <= 1000
 *
 * @author chenzw
 * @date 2021/4/24
 */
public class Solution377 {
    /*public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];//dp[i]表示和为i的组合数
        dp[0]=1;
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<nums.length;i++){
            set.add(nums[i]);
        }
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<i;j++){
                if(set.contains(i-j)){
                    dp[i]+=dp[j];
                }
            }
        }
        return dp[target];
    }*/

    //优化，不需要hashset
    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];//dp[i]表示和为i的组合数
        dp[0]=1;
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<nums.length;j++){
                if(nums[j]+i<=target){
                    dp[i+nums[j]]+=dp[i];//因为nums[i]>=1,所以外层第i次循环时，dp[i]已经得到最终值了
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        System.out.println(combinationSum4(new int[]{1,2,3},4));
    }
}
