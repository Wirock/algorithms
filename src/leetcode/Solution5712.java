package leetcode;

import java.util.Arrays;

/**
 * 5712. 你能构造出连续值的最大数目
 * 给你一个长度为 n 的整数数组 coins ，它代表你拥有的 n 个硬币。第 i 个硬币的值为 coins[i] 。如果你从这些硬币中选出一部分硬币，它们的和为 x ，那么称，你可以 构造 出 x 。
 *
 * 请返回从 0 开始（包括 0 ），你最多能 构造 出多少个连续整数。
 *
 * 你可能有多个相同值的硬币。
 *
 *示例 1：
 *
 * 输入：coins = [1,3]
 * 输出：2
 * 解释：你可以得到以下这些值：
 * - 0：什么都不取 []
 * - 1：取 [1]
 * 从 0 开始，你可以构造出 2 个连续整数。
 * 示例 2：
 *
 * 输入：coins = [1,1,1,4]
 * 输出：8
 * 解释：你可以得到以下这些值：
 * - 0：什么都不取 []
 * - 1：取 [1]
 * - 2：取 [1,1]
 * - 3：取 [1,1,1]
 * - 4：取 [4]
 * - 5：取 [4,1]
 * - 6：取 [4,1,1]
 * - 7：取 [4,1,1,1]
 * 从 0 开始，你可以构造出 8 个连续整数。
 * 示例 3：
 *
 * 输入：nums = [1,4,10,3,1]
 * 输出：20
 *
 *
 *
 * 解法：排序后按从小到大。设coins的前i个数能组成的连续集合是X(i)=[0,1,2...k],coins的第i+1个数是y,
 * 则coins的前i+1个数能组成的集合为X(i+1)=[0,1,2...k]U[y,y+1,y+2...y+k]
 * 为保证X(i+1)连续，则有y<=k+1&&y+k>=k+1，即y取值为1到k+1之间
 * @author chenzw
 * @date 2021/3/21
 */
public class Solution5712 {
    public static int getMaximumConsecutive(int[] coins) {
        int num = 1;
        Arrays.sort(coins);
        for(int i=0;i<coins.length;i++){
            if(num<coins[i])
                break;
            num+=coins[i];
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(getMaximumConsecutive(new int[]{1,4,10,3,1}));
    }
}
