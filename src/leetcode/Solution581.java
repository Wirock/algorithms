package leetcode;

/**
 * 581. 最短无序连续子数组
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 *
 * @author chenzw
 * @date 2021/8/3
 */
public class Solution581 {

    //1.单调栈两次遍历
    /*public int findUnsortedSubarray(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        //找到左边界，最左边的比它右侧其中一个数大的元素的下标
        for(int i=0;i<nums.length;i++){
            while(!stack.isEmpty()&&nums[stack.peek()]>nums[i]){
                left = Math.min(left,stack.pop());
            }
            stack.push(i);
        }
        if(left==Integer.MAX_VALUE) return 0;
        //找右边界，最右边的比它左侧其中一个数小的元素的下标
        for(int i=nums.length-1;i>=0;i--){
            while(!stack.isEmpty()&&nums[stack.peek()]<nums[i]){
                right = Math.max(right,stack.pop());
            }
            stack.push(i);
        }
        return right-left+1;
    }*/
    //2.优化，不需要单调栈，只要从左往右遍历找右边界，从右往左遍历找左边界即可。
    // 找右边界需要记录已扫元素的最大值，只要当前元素比这个值小则更新右边界；
    // 找左边界需要记录已扫元素的最小值，只要当前元素比这个值大则更新左边界
    public int findUnsortedSubarray(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int left = -1;
        for(int i=nums.length-1;i>=0;i--){
            if(nums[i]>min){
                left = i;
            }else{
                min = nums[i];
            }
        }
        if(left == -1)return 0;
        int right = -1;
        for(int i=0;i<nums.length;i++){
           if(nums[i]<max){
               right = i;
           }else{
               max = nums[i];
           }
        }
        return right-left+1;
    }
}
