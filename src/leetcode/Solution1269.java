package leetcode;

/**
 * 1269. 停在原地的方案数
 * 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
 *
 * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
 *
 * 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
 *
 * 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。
 *
 *
 *
 * 示例 1：
 *
 * 输入：steps = 3, arrLen = 2
 * 输出：4
 * 解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
 * 向右，向左，不动
 * 不动，向右，向左
 * 向右，不动，向左
 * 不动，不动，不动
 * 示例  2：
 *
 * 输入：steps = 2, arrLen = 4
 * 输出：2
 * 解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
 * 向右，向左
 * 不动，不动
 * 示例 3：
 *
 * 输入：steps = 4, arrLen = 2
 * 输出：8
 *
 *
 * 提示：
 *
 * 1 <= steps <= 500
 * 1 <= arrLen <= 10^6
 * @author chenzw
 * @date 2021/5/13
 */
public class Solution1269 {

    //标准动态规划实现，超出内存限制
    /*public static int numWays(int steps, int arrLen) {
        int[][] dp = new int[steps+1][arrLen];
        int mod = (int)1e9 + 7;
        int len = Math.min(steps+1,arrLen);
        for(int i=0;i<len;i++){
            dp[i][i] = 1;
        }
        for(int i=1;i<=steps;i++){
            for(int j=0;j<arrLen;j++){
                dp[i][j] = dp[i-1][j];
                if(j>0)dp[i][j]=(dp[i][j]+dp[i-1][j-1])%mod;
                if(j<arrLen-1)dp[i][j]=(dp[i][j]+dp[i-1][j+1])%mod;
            }
        }
        return dp[steps][0];
    }*/

    //进一步优化，每次的结果只与上一行的数据相关，故用两行数组足矣
    //数组列数最大取到满足step步刚好是到达数组右边界又折回0处的最大长度即可
    public static int numWays(int steps, int arrLen) {
        int len = Math.min(steps/2+1,arrLen);
        int[][] dp = new int[2][len];
        int mod = (int)1e9 + 7;
        dp[0][0] = 1;
        for(int i=1;i<=steps;i++){
            int curRow = i%2;
            int lastRow = (i-1)%2;
            for(int j=0;j<len;j++){
                dp[curRow][j] = dp[lastRow][j];
                if(j>0)dp[curRow][j]=(dp[curRow][j]+dp[lastRow][j-1])%mod;
                if(j<len-1)dp[curRow][j]=(dp[curRow][j]+dp[lastRow][j+1])%mod;
            }
        }
        return dp[steps%2][0];
    }

    public static void main(String[] args) {
        System.out.println(numWays(3,2));
        System.out.println(numWays(2,4));
        System.out.println(numWays(4,2));
        System.out.println(numWays(430,148488));
    }
}
