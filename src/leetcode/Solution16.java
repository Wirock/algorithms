package leetcode;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 * 示例：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *  
 *
 * 提示：
 *
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 * 通过次数193,923提交次数422,155
 *
 * @author chenzw
 * @date 2021/3/14
 */
public class Solution16 {
   /* public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0]+nums[1]+nums[2];
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                for(int k=j+1;k<nums.length;k++){
                    int sum = nums[i]+nums[j]+nums[k];
                    if(Math.abs(sum-target)<Math.abs(result-target)){
                        result=sum;
                    }
                }
            }
        }
        return result;
    }*/

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0]+nums[1]+nums[2];
        for(int i=0;i<nums.length;i++){
            int j=i+1;
            int k=nums.length-1;
            while(j<k){
                    int sum = nums[i]+nums[j]+nums[k];
                    if(sum==target){
                        return sum;
                    }
                    if(Math.abs(sum-target)<Math.abs(result-target)){
                        result=sum;
                    }
                    if(sum<target){
                        int temp = j+1;
                        while(temp<k&&nums[temp]==nums[j])temp++;
                        j=temp;
                    }else if(sum>target){
                        int temp = k-1;
                        while(temp>j&&nums[temp]==nums[k])temp--;
                        k=temp;
                    }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-1,2,1,-4},1));
        System.out.println(threeSumClosest(new int[]{0,0,0},1));
        System.out.println(threeSumClosest(new int[]{1,1,1,1},0));
    }
}
