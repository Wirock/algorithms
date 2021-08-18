package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 * @author chenzw
 * @date 2021/3/14
 */
public class Solution15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if(n==0)return result;
        Arrays.sort(nums);
        for(int i=0;i<n-2;i++){
            if(i>0&&nums[i]==nums[i-1])continue;
            int left = i+1;
            int right = n-1;
            while(left<right){
                int sum = nums[i]+nums[left]+nums[right];
                if(sum<0){
                    left++;
                    continue;
                }
                if(sum>0){
                    right--;
                    continue;
                }
                List<Integer> list = new ArrayList<>();
                list.add(nums[i]);
                list.add(nums[left]);
                list.add(nums[right]);
                result.add(list);
                left++;
                right--;
                while(left<right&&nums[left]==nums[left-1])left++;
                while(left<right&&nums[right]==nums[right+1])right--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(threeSum(new int[]{-1, 0, 1, 2, -1, -4}).toArray()));
    }
}
