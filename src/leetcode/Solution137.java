package leetcode;

/**
 * 137. 只出现一次的数字 II
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * 示例 2：
 *
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 *
 *
 * 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * @author chenzw
 * @date 2021/4/30
 */
public class Solution137 {
    //所有数按位相加，每一位的和对3求余即可得到只出现一次的数。
   /* public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int total = 0;
            for (int num: nums) {
                total += ((num >> i) & 1);
            }
            if (total % 3 != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }*/

   /*进一步压缩空间，由于对3求余，故每一位的和增加时只需要按0，1，2这三个状态循环变换就可以
    最大是2，所以用两位二进制数00,01,10表示即可，对于int这个状态只需要2个int来保存即可。
    其中a的每一位表示高位,b的每一位表示低位。
    所有出现三次的数按位相加后，低位b全部为0，再按位加上出现一次的数后，这个数的每一位都和低位b相同,b即为答案
    用数字电路设计的方法，列出真值表，可得
            b = ~a & (b ^ num);
            a = ~b & (a ^ num);
    */
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            b = ~a & (b ^ num);
            a = ~b & (a ^ num);
        }
        return b;
    }
}
