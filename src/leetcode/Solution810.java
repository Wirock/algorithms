package leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * 810. 黑板异或游戏
 * 黑板上写着一个非负整数数组 nums[i] 。Alice 和 Bob 轮流从黑板上擦掉一个数字，Alice 先手。如果擦除一个数字后，剩余的所有数字按位异或运算得出的结果等于 0 的话，当前玩家游戏失败。 (另外，如果只剩一个数字，按位异或运算得到它本身；如果无数字剩余，按位异或运算结果为 0。）
 *
 * 并且，轮到某个玩家时，如果当前黑板上所有数字按位异或运算结果等于 0，这个玩家获胜。
 *
 * 假设两个玩家每步都使用最优解，当且仅当 Alice 获胜时返回 true。
 *
 *
 *
 * 示例：
 *
 * 输入: nums = [1, 1, 2]
 * 输出: false
 * 解释:
 * Alice 有两个选择: 擦掉数字 1 或 2。
 * 如果擦掉 1, 数组变成 [1, 2]。剩余数字按位异或得到 1 XOR 2 = 3。那么 Bob 可以擦掉任意数字，因为 Alice 会成为擦掉最后一个数字的人，她总是会输。
 * 如果 Alice 擦掉 2，那么数组变成[1, 1]。剩余数字按位异或得到 1 XOR 1 = 0。Alice 仍然会输掉游戏。
 *
 *
 * 提示：
 *
 * 1 <= N <= 1000
 * 0 <= nums[i] <= 2^16
 * @author chenzw
 * @date 2021/5/22
 */
public class Solution810 {

    /*
        博弈论
     1.全部元素异或和为0，则Alice先手直接获胜；
     2.假设当前剩余元素为k个,分别为n1,n2,,,nk，当前剩余元素的异或和为S，则当前擦除一个元素必败的条件是：
        (s^n1)==0 && (s^n2)==0...&& (s^nk)==0,即 k+1个s的异或和为0
        当 k+1为偶数时，k+1个s的异或和必为0
        当nums的元素个数是偶数时，能保证轮到bob出手时k是奇数，此时Alice必胜
     */
    public static boolean xorGame(int[] nums) {
        int sum = 0;
        for(int n:nums){
            sum^=n;
        }
       return sum==0||nums.length%2==0;
    }

    public static void main(String[] args) {
       /* int[] nums = new int[999];
        Random random = new Random();
        for(int i=0;i<nums.length;i++){
            nums[i] = random.nextInt(1<<16);
        }
        System.out.println(Arrays.toString(nums));
        System.out.println(xorGame(nums));*/
        System.out.println(xorGame(new int[]{1,1,2}));
    }
}
