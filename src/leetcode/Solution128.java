package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 *
 *
 * 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 *
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * @author chenzw
 * @date 2021/6/30
 */
public class Solution128 {
    //hash+枚举，枚举最大最小值的区间
    /*public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int n : nums) {
            set.add(n);
            max = Math.max(max, n);
            min = Math.min(min, n);
        }
        int maxCount = 0;
        int count = 0;
        for (int i = min; i <= max; i++) {
            if (set.contains(i)) {
                count++;
            } else {
                maxCount = Math.max(maxCount,count);
                count = 0;
            }
        }
        maxCount = Math.max(maxCount,count);
        return maxCount;
    }*/

    //hash+枚举，枚举数组元素
    public int longestConsecutive(int[] nums) {
        if(nums.length==0)return 0;
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }
        int maxCount = 1;
        int count = 1;
        for (int n:nums) {
            if (!set.contains(n-1)) {
                int cur = n;
                while(set.contains(++cur))count++;
                maxCount = Math.max(maxCount,count);
                count = 1;
            }
        }
        return maxCount;
    }
}
