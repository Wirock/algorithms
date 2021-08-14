package leetcode;

/**
 * 233. 数字 1 的个数
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 13
 * 输出：6
 * 示例 2：
 *
 * 输入：n = 0
 * 输出：0
 *
 *
 * 提示：
 *
 * 0 <= n <= 2 * 109
 * @author chenzw
 * @date 2021/8/13
 */
public class Solution233 {
    public int countDigitOne(int n) {
        long k = 1;
        int ans = 0;
        //设i为n从右往左数位数，例如：i=1为个位，i=2为十位
        // k = 10^(i-1)
        //第i位左边的部分(不包括第i位)有n / (k * 10)种
        //第i位左边的部分(包括第i位) 当 第i位大于1时 有k种，
        //                           当第i位等于1时 有n % k + 1种
        //                           当第i位等于0时 有0种
        while(k<=n) {
            ans += (n / (k * 10)) * k + Math.min(Math.max(n % (k* 10) - k + 1, 0), k);
            k *= 10;
        }
        return ans;
    }
}
