package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 5765. 跳跃游戏 VII
 * 给你一个下标从 0 开始的二进制字符串 s 和两个整数 minJump 和 maxJump 。一开始，你在下标 0 处，且该位置的值一定为 '0' 。当同时满足如下条件时，你可以从下标 i 移动到下标 j 处：
 *
 * i + minJump <= j <= min(i + maxJump, s.length - 1) 且
 * s[j] == '0'.
 * 如果你可以到达 s 的下标 s.length - 1 处，请你返回 true ，否则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "011010", minJump = 2, maxJump = 3
 * 输出：true
 * 解释：
 * 第一步，从下标 0 移动到下标 3 。
 * 第二步，从下标 3 移动到下标 5 。
 * 示例 2：
 *
 * 输入：s = "01101110", minJump = 2, maxJump = 3
 * 输出：false
 *
 *
 * 提示：
 *
 * 2 <= s.length <= 105
 * s[i] 要么是 '0' ，要么是 '1'
 * s[0] == '0'
 * 1 <= minJump <= maxJump < s.length
 * @author chenzw
 * @date 2021/5/23
 */
public class Solution5765 {
    public boolean canReach(String s, int minJump, int maxJump) {
        boolean[] dp = new boolean[s.length()];
        Queue<Integer> queue = new LinkedList<>();
        dp[0]=true;
        queue.add(0);
        for(int i=1;i<dp.length;i++){
            if(s.charAt(i)=='0'){
                int start = Math.max(i-maxJump,0);
                int end = i-minJump;
                while(!queue.isEmpty()&&queue.peek()<start){
                    queue.poll();
                }
                if(!queue.isEmpty()&&queue.peek()<=end){
                    dp[i]=true;
                    queue.add(i);
                }
            }
        }
        return dp[s.length()-1];
    }
}
