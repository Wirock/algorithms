package leetcode;

import java.util.Arrays;

/**
 * @author chenzw
 * @date 2021/7/20
 */
public class Solution1877 {
    //排序，双指针
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length-1;
        int ans = 0;
        while(left<right){
            ans = Math.max(ans,nums[left++]+nums[right--]);
        }
        return ans;
    }
}
