package leetcode;

import java.util.stream.LongStream;

/**
 * 552. 学生出勤记录 II
 * 可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
 * 'A'：Absent，缺勤
 * 'L'：Late，迟到
 * 'P'：Present，到场
 * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 *
 * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 * 学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
 * 给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 109 + 7 取余 的结果。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：8
 * 解释：
 * 有 8 种长度为 2 的记录将被视为可奖励：
 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * 只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：3
 * 示例 3：
 *
 * 输入：n = 10101
 * 输出：183236316
 *
 *
 * 提示：
 *
 * 1 <= n <= 105
 * @author chenzw
 * @date 2021/8/19
 */
public class Solution552 {
    int MOD = 1000000007;

    //1.dfs超时
    /*public int checkRecord(int n) {
        return dfs(0, n, 0, 0);
    }

    private int dfs(int day, int n, int absent, int late) {
        if (day >= n) {
            return 1;
        }

        // 回溯开始
        int ans = 0;
        // 1. Present随便放
        ans = (ans + dfs(day + 1, n, absent, 0)) % MOD;
        // 2. Absent最多只能放一个
        if (absent < 1) {
            ans = (ans + dfs(day + 1, n, 1, 0)) % MOD;
        }
        // 3. Late最多连续放2个
        if (late < 2) {
            ans = (ans + dfs(day + 1, n, absent, late + 1)) % MOD;
        }

        return ans;
    }*/

    //2.记忆化dfs
    /*public int checkRecord(int n) {
        int[][][] memo = new int[n][2][3];
        return dfs(0, n, 0, 0, memo);
    }

    private int dfs(int day, int n, int absent, int late, int[][][] memo) {
        if (day >= n) {
            return 1;
        }

        // 相同的状态计算过了直接返回
        if (memo[day][absent][late] != 0) {
            return memo[day][absent][late];
        }

        // 回溯开始
        int ans = 0;
        // 1. Present随便放
        ans = (ans + dfs(day + 1, n, absent, 0, memo)) % MOD;
        // 2. Absent最多只能放一个
        if (absent < 1) {
            ans = (ans + dfs(day + 1, n, 1, 0, memo)) % MOD;
        }
        // 3. Late最多连续放2个
        if (late < 2) {
            ans = (ans + dfs(day + 1, n, absent, late + 1, memo)) % MOD;
        }

        // 记录每一个状态的计算结果
        memo[day][absent][late] = ans;

        return ans;
    }*/


    //3.dp
    /*public int checkRecord(int n) {
        long[][][] dp = new long[n][2][3];
        // 初始值
        dp[0][0][0] = 1;
        dp[0][1][0] = 1;
        dp[0][0][1] = 1;

        for (int i = 1; i < n; i++) {
            // 本次填入P，分成前一天累计了0个A和1个A两种情况
            dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % MOD;
            dp[i][1][0] = (dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2]) % MOD;
            // 本次填入A，前一天没有累计A都能转移过来
            // 这行可以与上面一行合并计算，为了方便理解，我们分开，下面会合并
            dp[i][1][0] = (dp[i][1][0] + dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % MOD;
            // 本次填入L，前一天最多只有一个连续的L，分成四种情况
            dp[i][0][1] = dp[i - 1][0][0];
            dp[i][0][2] = dp[i - 1][0][1];
            dp[i][1][1] = dp[i - 1][1][0];
            dp[i][1][2] = dp[i - 1][1][1];
        }

        // 计算结果，即最后一天的所有状态相加
        long ans = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                ans = (ans + dp[n - 1][i][j]) % MOD;
            }
        }

        return (int) ans;
    }*/

    //4.dp滚动数组降维优化
    /*public int checkRecord(int n) {
        long[][] dp = new long[2][6];
        // 初始值
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[0][3] = 1;

        for (int i = 1; i < n; i++) {
            // 当前使用的下标
            int cur = i & 1;
            // 上一次使用的下标
            int last = (i - 1) & 1;
            dp[cur][0] = (dp[last][0] + dp[last][1] + dp[last][2]) % MOD;
            dp[cur][1] = dp[last][0];
            dp[cur][2] = dp[last][1];
            dp[cur][3] = (dp[last][3] + dp[last][4] + dp[last][5] + dp[last][0] + dp[last][1] + dp[last][2]) % MOD;
            dp[cur][4] = dp[last][3];
            dp[cur][5] = dp[last][4];
        }

        return (int) (LongStream.of(dp[(n - 1) & 1]).sum() % MOD);
    }*/

    //5. 4中的dp数组两个坐标维度状态有限，可以进行状态压缩，使用一个维度
    /*public int checkRecord(int n) {
        long[] dp = new long[6];
        // 初始值
        dp[0] = 1;
        dp[1] = 1;
        dp[3] = 1;

        for (int i = 1; i < n; i++) {
            long[] newDp = new long[6];
            newDp[0] = (dp[0] + dp[1] + dp[2]) % MOD;
            newDp[1] = dp[0];
            newDp[2] = dp[1];
            // 稍微调整了一下顺序
            newDp[3] = (dp[3] + dp[4] + dp[5] + dp[0] + dp[1] + dp[2]) % MOD;
            newDp[4] = dp[3];
            newDp[5] = dp[4];

            dp = newDp;
        }

        return (int) (LongStream.of(dp).sum() % MOD);
    }*/

    //6.在5的基础上使用滚动数组优化,避免每次重建数组
    public int checkRecord(int n) {
        long[][] dp = new long[2][6];
        // 初始值
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[0][3] = 1;

        for (int i = 1; i < n; i++) {
            // 当前使用的下标
            int cur = i & 1;
            // 上一次使用的下标
            int last = (i - 1) & 1;
            dp[cur][0] = (dp[last][0] + dp[last][1] + dp[last][2]) % MOD;
            dp[cur][1] = dp[last][0];
            dp[cur][2] = dp[last][1];
            dp[cur][3] = (dp[last][3] + dp[last][4] + dp[last][5] + dp[last][0] + dp[last][1] + dp[last][2]) % MOD;
            dp[cur][4] = dp[last][3];
            dp[cur][5] = dp[last][4];
        }

        return (int) (LongStream.of(dp[(n - 1) & 1]).sum() % MOD);
    }
}
