package leetcode;

/**
 * 526. 优美的排列
 * 假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：
 *
 * 第 i 位的数字能被 i 整除
 * i 能被第 i 位上的数字整除
 * 现在给定一个整数 N，请问可以构造多少个优美的排列？
 *
 * 示例1:
 *
 * 输入: 2
 * 输出: 2
 * 解释:
 *
 * 第 1 个优美的排列是 [1, 2]:
 *   第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
 *   第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
 *
 * 第 2 个优美的排列是 [2, 1]:
 *   第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
 *   第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
 * 说明:
 *
 * N 是一个正整数，并且不会超过15。
 * @author chenzw
 * @date 2021/8/16
 */
public class Solution526 {
    public int countArrangement(int n) {
        int mask = 1 << n;
        int[][] f = new int[n + 1][mask];
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            // 枚举所有的状态
            for (int state = 0; state < mask; state++) {
                // 枚举位置 i（最后一位）选的数值是 k
                for (int k = 1; k <= n; k++) {
                    // 首先 k 在 state 中必须是 1
                    if (((state >> (k - 1)) & 1) == 0) continue;
                    // 数值 k 和位置 i 之间满足任一整除关系
                    if (k % i != 0 && i % k != 0) continue;

                    // state & (~(1 << (k - 1))) 代表将 state 中数值 k 的位置置零
                    f[i][state] += f[i - 1][state & (~(1 << (k - 1)))];
                }
            }
        }
        return f[n][mask - 1];
    }
}
