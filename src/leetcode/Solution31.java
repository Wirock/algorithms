package leetcode;

import java.util.Arrays;

/**
 * 31. 下一个排列
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 *
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 示例 4：
 *
 * 输入：nums = [1]
 * 输出：[
 * @author chenzw
 * @date 2021/3/27
 */
public class Solution31 {
    public static void nextPermutation(int[] nums) {
        if(nums.length<2){
            return;
        }
        for(int i=nums.length-2;i>=0;i--){
            for(int j=nums.length-1;j>i;j--){
                if(nums[i]<nums[j]){
                    int temp=nums[i];
                    nums[i]=nums[j];
                    nums[j]=temp;
                    Arrays.sort(nums,i+1,nums.length);
                    return;
                }
            }
        }
        int i=0;
        int j=nums.length-1;
        while(i<j){
            int temp=nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{4,2,0,2,3,2,0};
        int[] nums = new int[]{2,3,1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
