package leetcode;

import java.util.*;

/**
 * 1027. 最长等差数列
 * 给你一个整数数组 nums，返回 nums 中最长等差子序列的长度。
 *
 * 回想一下，nums 的子序列是一个列表 nums[i1], nums[i2], ..., nums[ik] ，且 0 <= i1 < i2 < ... < ik <= nums.length - 1。并且如果 seq[i+1] - seq[i]( 0 <= i < seq.length - 1) 的值都相同，那么序列 seq 是等差的。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,6,9,12]
 * 输出：4
 * 解释：
 * 整个数组是公差为 3 的等差数列。
 * 示例 2：
 *
 * 输入：nums = [9,4,7,2,10]
 * 输出：3
 * 解释：
 * 最长的等差子序列是 [4,7,10]。
 * 示例 3：
 *
 * 输入：nums = [20,1,15,3,10,5,8]
 * 输出：4
 * 解释：
 * 最长的等差子序列是 [20,15,10,5]。
 *
 *
 * 提示：
 *
 * 2 <= nums.length <= 1000
 * 0 <= nums[i] <= 500
 * 通过次数22,823提交次数51,223
 * Created by Chenzw on 2023/4/22 2:25
 */
public class Solution1027 {
    //暴力
    /*public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        int ans = 1;
        for(int i=0;i<=500;i++){
            for(int j=0;j<n;j++){
                int len = 1;
                for(int k=j+1;k<n;k++){
                    if(nums[k] == nums[j]+len*i){
                        len ++;
                    }
                }
                ans = Math.max(ans, len);
            }
            for(int j=0;j<n;j++){
                int len = 1;
                for(int k=j+1;k<n;k++){
                    if(nums[k] == nums[j]-len*i){
                        len ++;
                    }
                }
                ans = Math.max(ans, len);

            }
        }
        return ans;
    }*/

    //dp
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][1001];
        int ans = 1;
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
               int d = nums[i]-nums[j]+500;
               dp[i][d] = Math.max(dp[i][d], dp[j][d] + 1);
               ans = Math.max(ans,dp[i][d]+1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        List<String> arr = new ArrayList<>();
        while (in.hasNext()) { // 注意 while 处理多个 case
            String s = in.next();
            arr.add(s);
        }
        System.out.println(Arrays.toString(arr.toArray()));

    }
}
