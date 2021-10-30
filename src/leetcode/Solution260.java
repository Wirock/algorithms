package leetcode;

/**
 * 260. 只出现一次的数字 III
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
 *
 *
 *
 * 进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,1,3,2,5]
 * 输出：[3,5]
 * 解释：[5, 3] 也是有效的答案。
 * 示例 2：
 *
 * 输入：nums = [-1,0]
 * 输出：[-1,0]
 * 示例 3：
 *
 * 输入：nums = [0,1]
 * 输出：[1,0]
 * 提示：
 *
 * 2 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * 除两个只出现一次的整数外，nums 中的其他数字都出现两次
 * @author chenzw
 * @date 2021/10/30
 */
public class Solution260 {
    //hash
    /*public int[] singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n:nums){
            if(set.contains(n))set.remove(n);
            else set.add(n);
        }
        int[] ans = new int[2];
        int i=0;
        for(int s:set){
            ans[i++]=s;
        }
        return ans;
    }*/

    //异或
    //数组中所有数异或，最终结果的二进制表示中为1的位,在这两个不同的数在这个位不同
    //可以任选上述的一个位来做区分，把数组中这个位为0和1分成两组
    //由于其他数都是成对出现，所以这两组的异或和就是这两个不同的数
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for(int n:nums){
            xor^=n;
        }
        int[] ans = new int[2];
        //xor&(-xor)能保留xor二进制形式最右边的1，其他位置0
        //例如：xor=001100,则 -xor=110100,xor&(-xor)=000100
        int mask = xor&(-xor);
        for(int n:nums){
            if((mask&n)==0){
                ans[0] ^= n;
            }else{
                ans[1] ^= n;
            }
        }
        return ans;
    }
}
