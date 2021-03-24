package leetcode;

import java.util.Stack;

/**
 * 456. 132模式
 * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
 *
 * 注意：n 的值小于15000。
 * @author chenzw
 * @date 2021/3/24
 */
public class Solution456 {
    public static boolean find132pattern(int[] nums){
        if(nums==null||nums.length<3) return false;
        Stack<Integer> stack = new Stack<>();
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            min[i] = Math.min(min[i-1],nums[i]);
        }
        for(int j=nums.length-1;j>=0;j--){
            if(nums[j]<=min[j]) continue;
            while(!stack.isEmpty()&&stack.peek()<=min[j]){
                stack.pop();
            }
            if(!stack.isEmpty()&&stack.peek()<nums[j]){
                return true;
            }
            stack.push(nums[j]);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(find132pattern(new int[]{1, 2, 3, 4}));
        System.out.println(find132pattern(new int[]{3, 1, 4, 2}));
        System.out.println(find132pattern(new int[]{-1, 3, 2, 0}));
    }
}
