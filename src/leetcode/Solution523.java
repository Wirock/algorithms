package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 523. 连续的子数组和
 * 给定一个包含 非负数 的数组和一个目标 整数 k ，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，且总和为 k 的倍数，即总和为 n * k ，其中 n 也是一个整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[23,2,4,6,7], k = 6
 * 输出：True
 * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6。
 * 示例 2：
 *
 * 输入：[23,2,6,4,7], k = 6
 * 输出：True
 * 解释：[23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
 *
 *
 * 说明：
 *
 * 数组的长度不会超过 10,000 。
 * 你可以认为所有数字总和在 32 位有符号整数范围内。
 * @author chenzw
 * @date 2021/6/2
 */
public class Solution523 {
    //哈希，前缀和，同余定理(a-b)%k==0等价于a%k==b%k
    public static boolean checkSubarraySum(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int last = nums[0];
        set.add(0);
        for(int i=1;i<nums.length;i++){
            int sum = last+nums[i];
            if(set.contains(sum%k)){
                return true;
            }
            set.add(last%k);
            last = sum;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkSubarraySum(new int[]{1,2,3,4,5},5));
        System.out.println(checkSubarraySum(new int[]{23,2,6,4,7},6));
    }
}
