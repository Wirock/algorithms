package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1224. 最大相等频率
 * 给你一个正整数数组 nums，请你帮忙从该数组中找出能满足下面要求的 最长 前缀，并返回该前缀的长度：
 *
 * 从前缀中 恰好删除一个 元素后，剩下每个数字的出现次数都相同。
 * 如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,2,1,1,5,3,3,5]
 * 输出：7
 * 解释：对于长度为 7 的子数组 [2,2,1,1,5,3,3]，如果我们从中删去 nums[4] = 5，就可以得到 [2,2,1,1,3,3]，里面每个数字都出现了两次。
 * 示例 2：
 *
 * 输入：nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
 * 输出：13
 *
 *
 * 提示：
 *
 * 2 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * @author chenzw
 * @date 2022/8/20
 */
public class Solution1224 {
    public int maxEqualFreq(int[] nums) {
        int ans = 1;
        int maxF = 0;
        Map<Integer,Integer> count = new HashMap<>();
        Map<Integer,Integer> freq = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int c = count.getOrDefault(nums[i],0);
            count.put(nums[i],c+1);
            maxF = Math.max(c+1,maxF);
            if(c>0){
                freq.put(c, freq.get(c)-1);
                if(freq.get(c)==0){
                    freq.remove(c);
                }
            }
            freq.put(c+1,freq.getOrDefault(c+1,0)+1);
            if(freq.size()==2){
                if(freq.get(maxF)==1&&freq.containsKey(maxF-1)){
                    ans = i+1;
                }else if(freq.getOrDefault(maxF-1,0)==1){
                    ans = i+1;
                }
            }
        }
        return ans;
    }
}
