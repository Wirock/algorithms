package leetcode;

/**
 * 45. 跳跃游戏 II
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 假设你总是可以到达数组的最后一个位置。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 *
 * 输入: [2,3,0,1,4]
 * 输出: 2
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 105
 * @author chenzw
 * @date 2021/5/23
 */
public class Solution45 {
    //dp
    /*public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            int right = Math.min(i+nums[i],nums.length-1);
            for(int j=i+1;j<=right;j++){
                dp[j]=dp[j]==0?dp[i]+1:Math.min(dp[j],dp[i]+1);
            }
        }
        return dp[nums.length-1];
    }*/
    //贪心，计算每一步可到达的最远范围，以上一步的所有可能目标点为起点，计算下一步的可到达的目标点范围，当范围覆盖数组终点时结束
    public int jump(int[] nums) {
        int count=0;
        int maxPosition=0;
        int right = 0;
        int cur = 0;
        while(right<nums.length-1){
            maxPosition = Math.max(maxPosition,cur+nums[cur]);
            if(cur==right){
                right = maxPosition;
                count++;
            }
            cur++;
        }
        return count;
    }
}
