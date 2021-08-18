package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：答案中不可以包含重复的四元组。
 *
 * @author chenzw
 * @date 2021/3/16
 */
public class Solution18 {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums==null||nums.length<4){
            return result;
        }
        Arrays.sort(nums);
        for(int i=0;i<nums.length-3;i++){
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            for(int j=i+1;j<nums.length-2;j++){
                if(j>i+1&&nums[j]==nums[j-1]){
                    continue;
                }
                if(nums[i]+nums[j]+nums[j+1]+nums[j+2]>target)break;//优化，未遍历的最小的4个数和超过target，后续的和都大于target，直接结束
                if(nums[i]+nums[j]+nums[nums.length-1]+nums[nums.length-2]<target)continue;//优化，当前i,j组合与最大的两个数和小于target,则直接进入下一个i,j组合
                int m=j+1;
                int n=nums.length-1;
                while(m<n){
                    int sum = nums[i]+nums[j]+nums[m]+nums[n];
                    if(sum==target){
                        result.add(Arrays.asList(nums[i],nums[j],nums[m],nums[n]));
                        m++;
                        n--;
                        while(m<n&&nums[m]==nums[m-1]) m++;
                        while(m<n&&nums[n]==nums[n+1])n--;
                    }else if(sum<target){
                        m++;
                    }else{
                        n--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        for(List<Integer> l:lists){
            System.out.println(Arrays.toString(l.toArray()));
        }
    }
}
