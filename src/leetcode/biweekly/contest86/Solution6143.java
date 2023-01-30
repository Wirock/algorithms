package leetcode.biweekly.contest86;

import java.util.TreeMap;

/**
 * 6143. 预算内的最多机器人数目
 * 你有 n 个机器人，给你两个下标从 0 开始的整数数组 chargeTimes 和 runningCosts ，两者长度都为 n 。第 i 个机器人充电时间为 chargeTimes[i] 单位时间，花费 runningCosts[i] 单位时间运行。再给你一个整数 budget 。
 *
 * 运行 k 个机器人 总开销 是 max(chargeTimes) + k * sum(runningCosts) ，其中 max(chargeTimes) 是这 k 个机器人中最大充电时间，sum(runningCosts) 是这 k 个机器人的运行时间之和。
 *
 * 请你返回在 不超过 budget 的前提下，你 最多 可以 连续 运行的机器人数目为多少。
 *
 *
 *
 * 示例 1：
 *
 * 输入：chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25
 * 输出：3
 * 解释：
 * 可以在 budget 以内运行所有单个机器人或者连续运行 2 个机器人。
 * 选择前 3 个机器人，可以得到答案最大值 3 。总开销是 max(3,6,1) + 3 * sum(2,1,3) = 6 + 3 * 6 = 24 ，小于 25 。
 * 可以看出无法在 budget 以内连续运行超过 3 个机器人，所以我们返回 3 。
 * 示例 2：
 *
 * 输入：chargeTimes = [11,12,19], runningCosts = [10,8,7], budget = 19
 * 输出：0
 * 解释：即使运行任何一个单个机器人，还是会超出 budget，所以我们返回 0 。
 *
 *
 * 提示：
 *
 * chargeTimes.length == runningCosts.length == n
 * 1 <= n <= 5 * 104
 * 1 <= chargeTimes[i], runningCosts[i] <= 105
 * 1 <= budget <= 1015
 * Created by Chenzw on 2022/9/4 0:28
 */
public class Solution6143 {
    //滑动窗口求最值
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int ans = 0;
        long sum = 0;
        TreeMap<Integer,Integer> win = new TreeMap<>((x, y)->x-y);
        int n = chargeTimes.length;
        for(int i=0;i<n;i++){
            sum += runningCosts[i];
            win.put(chargeTimes[i],win.getOrDefault(chargeTimes[i],0)+1);
            if(sum*(ans+1)+win.lastKey()<=budget){
                ans++;
            }else{
                sum-=runningCosts[i-ans];
                Integer count = win.get(chargeTimes[i - ans]);
                if(count==1){
                    win.remove(chargeTimes[i-ans]);
                }else{
                    win.put(chargeTimes[i-ans], count-1);
                }
            }
        }
        return ans;
    }
}
