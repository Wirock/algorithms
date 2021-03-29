package leetcode;

/**
 * 33. 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的索引，否则返回 -1 。
 *
 *
 * @author chenzw
 * @date 2021/3/29
 */
public class Solution33 {
    public static int search(int[] nums, int target) {
        if(nums==null||nums.length==0)return 0;
        return findIndex(nums,target,0,nums.length-1);
    }

    private static int findIndex(int[] nums,int target,int left,int right){
        if(left>right) return -1;
        if(nums[left]==target)return left;
        if(nums[right]==target)return right;
        if(left==right) return -1;
        int mid = (left+right)/2;
        if(nums[mid]==target){
            return mid;
        }
        if(target<nums[mid]){
            if(nums[left]<target||nums[right]>nums[mid])return findIndex(nums,target,left,mid-1);
            return findIndex(nums,target,mid+1,right);
        }else {
            if(nums[right]>target||nums[left]<nums[mid])return findIndex(nums,target,mid+1,right);
            return findIndex(nums,target,left,mid-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{4,5,6,7,0,1,2},0));
        System.out.println(search(new int[]{4,5,6,7,0,1,2},3));
        System.out.println(search(new int[]{1},1));
        System.out.println(search(new int[]{4,5,6,7,8,1,2,3},8));
    }
}
