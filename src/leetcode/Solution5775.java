package leetcode;

import common.CommonUtil;

/**
 * 5775. 准时抵达会议现场的最小跳过休息次数
 * 给你一个整数 hoursBefore ，表示你要前往会议所剩下的可用小时数。要想成功抵达会议现场，你必须途经 n 条道路。道路的长度用一个长度为 n 的整数数组 dist 表示，其中 dist[i] 表示第 i 条道路的长度（单位：千米）。另给你一个整数 speed ，表示你在道路上前进的速度（单位：千米每小时）。
 *
 * 当你通过第 i 条路之后，就必须休息并等待，直到 下一个整数小时 才能开始继续通过下一条道路。注意：你不需要在通过最后一条道路后休息，因为那时你已经抵达会议现场。
 *
 * 例如，如果你通过一条道路用去 1.4 小时，那你必须停下来等待，到 2 小时才可以继续通过下一条道路。如果通过一条道路恰好用去 2 小时，就无需等待，可以直接继续。
 * 然而，为了能准时到达，你可以选择 跳过 一些路的休息时间，这意味着你不必等待下一个整数小时。注意，这意味着与不跳过任何休息时间相比，你可能在不同时刻到达接下来的道路。
 *
 * 例如，假设通过第 1 条道路用去 1.4 小时，且通过第 2 条道路用去 0.6 小时。跳过第 1 条道路的休息时间意味着你将会在恰好 2 小时完成通过第 2 条道路，且你能够立即开始通过第 3 条道路。
 * 返回准时抵达会议现场所需要的 最小跳过次数 ，如果 无法准时参会 ，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：dist = [1,3,2], speed = 4, hoursBefore = 2
 * 输出：1
 * 解释：
 * 不跳过任何休息时间，你将用 (1/4 + 3/4) + (3/4 + 1/4) + (2/4) = 2.5 小时才能抵达会议现场。
 * 可以跳过第 1 次休息时间，共用 ((1/4 + 0) + (3/4 + 0)) + (2/4) = 1.5 小时抵达会议现场。
 * 注意，第 2 次休息时间缩短为 0 ，由于跳过第 1 次休息时间，你是在整数小时处完成通过第 2 条道路。
 * 示例 2：
 *
 * 输入：dist = [7,3,5,5], speed = 2, hoursBefore = 10
 * 输出：2
 * 解释：
 * 不跳过任何休息时间，你将用 (7/2 + 1/2) + (3/2 + 1/2) + (5/2 + 1/2) + (5/2) = 11.5 小时才能抵达会议现场。
 * 可以跳过第 1 次和第 3 次休息时间，共用 ((7/2 + 0) + (3/2 + 0)) + ((5/2 + 0) + (5/2)) = 10 小时抵达会议现场。
 * 示例 3：
 *
 * 输入：dist = [7,3,5,5], speed = 1, hoursBefore = 10
 * 输出：-1
 * 解释：即使跳过所有的休息时间，也无法准时参加会议。
 *
 *
 * 提示：
 *
 * n == dist.length
 * 1 <= n <= 1000
 * 1 <= dist[i] <= 105
 * 1 <= speed <= 106
 * 1 <= hoursBefore <= 107
 * @author chenzw
 * @date 2021/5/30
 */
public class Solution5775 {
    //直接转double计算会有精度问题
    /*public static int minSkips(int[] dist, int speed, int hoursBefore) {
        int n = dist.length;
        double[][] dp = new double[n][n];//dp[i][j]表示第i条道路跳过j次休息花费的最少时间
        for(int i=1;i<n;i++){
            dp[i][0] = Math.ceil(dp[i-1][0]+(double)dist[i-1]/(double)speed);//全休息
            dp[i][i] = dp[i-1][i-1]+(double)dist[i-1]/(double)speed;//全不休息
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<i;j++){
                dp[i][j]=(double)dist[i-1]/(double)speed+dp[i-1][j-1];//跳过第i次休息
                dp[i][j]=Math.min(dp[i][j],Math.ceil((double)dist[i-1]/(double)speed+dp[i-1][j]));//不跳过第i次休息
            }
        }
        double d = hoursBefore - (double)dist[n-1]/(double)speed;//前n-1条道路可用的总时间
        System.out.println(d);
        CommonUtil.printArray(dp);
        for(int i=0;i<n;i++){
            if(Double.compare(dp[n-1][i],d)<=0)return i;
        }
        return -1;
    }*/

    //由于分母固定为speed,计算可以只计算分子,把时间转换成这段时间能走的距离
    public static int minSkips(int[] dist, int speed, int hoursBefore) {
        int n = dist.length;
        int[][] dp = new int[n][n];//dp[i][j]表示第i条道路跳过j次休息花费的最少时间能走的距离
        for(int i=1;i<n;i++){
            dp[i][0] = ceil(dp[i-1][0]+dist[i-1],speed);//全休息
            dp[i][i] = dp[i-1][i-1]+dist[i-1];//全不休息
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<i;j++){
                dp[i][j]=dist[i-1]+dp[i-1][j-1];//跳过第i次休息
                dp[i][j]=Math.min(dp[i][j],ceil(dist[i-1]+dp[i-1][j],speed));//不跳过第i次休息
            }
        }
        double d = hoursBefore*speed - dist[n-1];//前n-1条道路可用的总时间 能走的距离
        for(int i=0;i<n;i++){
            if(Double.compare(dp[n-1][i],d)<=0)return i;
        }
        return -1;
    }
    //自定义ceil
    private static int ceil(int m,int n){
        int k = m%n;
        return k>0?m-k+n:m;
    }

    public static void main(String[] args) {
        System.out.println(minSkips(new int[]{1,3,2},4,2));
        System.out.println(minSkips(new int[]{7,3,5,5},2,10));
        System.out.println(minSkips(new int[]{35,57,85,55,63,78,57,54,35,28,97,66,15,45,56,15,37,87,87,76,63,68,86,40,6,29,51,77,8,1,27,39,28,99,18,98,33,38,42,16,1,64,96,56,23,17,49,69,91,30,65,72,86,46,10,51,95,6,56,3,59,10,41,74,55,74,52,91,82,54,38,15,52,3,42,22,80,59,89,47,12,56,14,32,56,76,52,68,11,51,40,96,44,29,43,100,22,10,66,82,15,68,66,25,100,45,45,94,83,19,31,14,19,33,26,23,78,20,98,98,84,10,23,99,81,64,60,97,73,98,75,58,88,73,83,82,80,42,81,41,20},59,128));

    }
}
