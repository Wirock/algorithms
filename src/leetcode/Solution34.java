package leetcode;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 进阶：
 *
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 * @author chenzw
 * @date 2021/3/29
 */
public class Solution34 {
    public static int[] searchRange(int[] nums, int target) {
        if(nums==null||nums.length==0)return new int[]{-1,-1};
        return searchRange(nums,target,0,nums.length-1);
    }

    private static int[] searchRange(int[] nums, int target,int left,int right) {
        if(nums[left]==nums[right]){
            if(nums[left]==target)return new int[]{left,right};
            return new int[]{-1,-1};
        }
        if(left>right){
            return new int[]{-1,-1};
        }
        int mid = (left+right)/2;
        if(nums[mid]>target) {
            return searchRange(nums, target, left, mid - 1);
        }else if(nums[mid]<target){
            return searchRange(nums,target,mid+1,right);
        }else{
            int[] leftRange = searchRange(nums,target,left,mid);
            int[] rightRange = searchRange(nums,target,mid+1,right);
            return new int[]{leftRange[0],rightRange[0]>-1?rightRange[1]:leftRange[1]};
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{5,7,7,8,8,10},8)));
        System.out.println(Arrays.toString(searchRange(new int[]{5,7,7,8,8,10},6)));
        System.out.println(Arrays.toString(searchRange(new int[]{5,7,7,8,8,10},10)));
        System.out.println(Arrays.toString(searchRange(new int[]{5,7,7,8,8,10},5)));
        System.out.println(Arrays.toString(searchRange(new int[]{5,5,5,5,5,5},5)));
        System.out.println(Arrays.toString(searchRange(new int[]{5,5,5,5,5,5},1)));
        System.out.println(Arrays.toString(searchRange(new int[]{0,1,1,1,1,2,3,3,4,4,4,4,5,5,6,6,7,8,10,10,10},6)));
        System.out.println(Arrays.toString(searchRange(new int[]{},0)));
    }
}
