package leetcode.weekly.contest308;

import java.util.Arrays;

/**
 * 6160. 和有限的最长子序列
 * 给你一个长度为 n 的整数数组 nums ，和一个长度为 m 的整数数组 queries 。
 *
 * 返回一个长度为 m 的数组 answer ，其中 answer[i] 是 nums 中 元素之和小于等于 queries[i] 的 子序列 的 最大 长度  。
 *
 * 子序列 是由一个数组删除某些元素（也可以不删除）但不改变剩余元素顺序得到的一个数组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,5,2,1], queries = [3,10,21]
 * 输出：[2,3,4]
 * 解释：queries 对应的 answer 如下：
 * - 子序列 [2,1] 的和小于或等于 3 。可以证明满足题目要求的子序列的最大长度是 2 ，所以 answer[0] = 2 。
 * - 子序列 [4,5,1] 的和小于或等于 10 。可以证明满足题目要求的子序列的最大长度是 3 ，所以 answer[1] = 3 。
 * - 子序列 [4,5,2,1] 的和小于或等于 21 。可以证明满足题目要求的子序列的最大长度是 4 ，所以 answer[2] = 4 。
 * 示例 2：
 *
 * 输入：nums = [2,3,4,5], queries = [1]
 * 输出：[0]
 * 解释：空子序列是唯一一个满足元素和小于或等于 1 的子序列，所以 answer[0] = 0 。
 *
 *
 * 提示：
 *
 * n == nums.length
 * m == queries.length
 * 1 <= n, m <= 1000
 * 1 <= nums[i], queries[i] <= 106
 * @author chenzw
 * @date 2022/8/28
 */
public class Solution6160 {

    /*
    //排序+贪心
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int[][] q = new int[queries.length][2];
        for(int i=0;i<queries.length;i++){
            q[i][0] = queries[i];
            q[i][1] = i;
        }
        Arrays.sort(q,(x,y)->x[0]-y[0]);
        int[] ans = new int[queries.length];
        long sum = 0;
        int j = 0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];
            while(sum>q[j][0]){
                j++;
                if(j>=queries.length){
                    return ans;
                }
            }
            for(int k=j;k<queries.length;k++){
                ans[q[k][1]] ++;
            }

        }
        return ans;
    }*/

    //前缀和+二分查找
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int[] ans = new int[queries.length];
        int[] sum = new int[nums.length+1];
        for(int i=1;i<sum.length;i++){
            sum[i] = sum[i-1]+nums[i-1];
        }
        for(int i=0;i<ans.length;i++){
            int left = 0;
            int right = sum.length-1;
            while(left<right){
                int mid = (left+right+1)>>1;
                if(sum[mid]<=queries[i]){
                    left = mid;
                }else{
                    right = mid-1;
                }
            }
            ans[i] = left;
        }
        return ans;
    }
}
