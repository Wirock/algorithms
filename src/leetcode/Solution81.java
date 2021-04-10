package leetcode;

/**
 * 81. 搜索旋转排序数组 II
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,5,6,0,0,1,2], target = 0
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [2,5,6,0,0,1,2], target = 3
 * 输出：false
 * @author chenzw
 * @date 2021/4/7
 */
public class Solution81 {
    public static boolean search(int[] nums, int target) {
        return search(nums,target,0,nums.length-1);
    }

    private static boolean search(int[] nums, int target,int left ,int right){
        if(left>right)return false;
        int mid = (left+right)/2;
        if(nums[mid]==target)return true;
        if(nums[left]>nums[mid]){//左大于中，右侧无拐点
            if(target>nums[mid]&&target<=nums[right]){//比中大的,比右小的在右侧找
                return search(nums,target,mid+1,right);
            }else{//其余的在左侧找
                return search(nums,target,left,mid-1);
            }
        }else if(nums[left]<nums[mid]){//左小于中，左侧无拐点
            if(target>=nums[left]&&target<nums[mid]){
                return search(nums,target,left,mid-1);
            }else{
                return search(nums,target,mid+1,right);
            }
        }else{//拐点不确定，两边同时考虑
            return search(nums,target,left,mid-1)||search(nums,target,mid+1,right);
        }
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{2,5,6,0,0,1,2},0));
        System.out.println(search(new int[]{2,5,6,0,0,1,2},3));
        System.out.println(search(new int[]{2,2},3));
        System.out.println(search(new int[]{1},3));
        System.out.println(search(new int[]{4,4,4,3,4,4,4,4,4,4,4},3));
        System.out.println(search(new int[]{5,1,3},3));
    }
}
