package leetcode.weekly.contest307;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 6155. 找出数组的第 K 大和
 * 给你一个整数数组 nums 和一个 正 整数 k 。你可以选择数组的任一 子序列 并且对其全部元素求和。
 *
 * 数组的 第 k 大和 定义为：可以获得的第 k 个 最大 子序列和（子序列和允许出现重复）
 *
 * 返回数组的 第 k 大和 。
 *
 * 子序列是一个可以由其他数组删除某些或不删除元素排生而来的数组，且派生过程不改变剩余元素的顺序。
 *
 * 注意：空子序列的和视作 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,4,-2], k = 5
 * 输出：2
 * 解释：所有可能获得的子序列和列出如下，按递减顺序排列：
 * - 6、4、4、2、2、0、0、-2
 * 数组的第 5 大和是 2 。
 * 示例 2：
 *
 * 输入：nums = [1,-2,3,4,-10,12], k = 16
 * 输出：10
 * 解释：数组的第 16 大和是 10 。
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 105
 * -109 <= nums[i] <= 109
 * 1 <= k <= min(2000, 2n)
 * @author chenzw
 * @date 2022/8/21
 */
public class Solution6155 {
    //堆
    //记 nums 中所有非负数的和为sum。
    //任意一个子序列和，都等价于从sum中减去某些非负数/加上某些负数得到,相当于减去绝对值
    //nums数组取绝对值后按从小到大排序
    //设s(i)为以nums下标0到i的一个子序列和，则小于等于sum-s(i)的最大子序列和是sum-s(i+1)
    public long kSum(int[] nums, int k) {
        long sum = 0L;
        int n = nums.length;
        for(int i=0;i<n;i++){
            if(nums[i]>=0){
                sum += nums[i];
            }else{
                nums[i] = -nums[i];
            }
        }
        Arrays.sort(nums);
        PriorityQueue<Long[]> queue = new PriorityQueue<>((a,b)->Long.compare(b[0],a[0]));
        queue.offer(new Long[]{sum,0L});//当前最大值是sum,下一个最大值sum-s(0)
        while(--k>0){
            Long[] p = queue.poll();
            int next = p[1].intValue();
            if(next<n){
                queue.offer(new Long[]{p[0]-nums[next],next+1L});
                if(next>0){
                    queue.offer(new Long[]{p[0]+nums[next-1]-nums[next],next+1L});
                }
            }
        }
        return queue.peek()[0];
    }
}
