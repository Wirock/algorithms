package leetcode;

/**
 * 面试题 17.10. 主要元素
 * 数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[1,2,5,9,5,9,5,5,5]
 * 输出：5
 * 示例 2：
 *
 * 输入：[3,2]
 * 输出：-1
 * 示例 3：
 *
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * @author chenzw
 * @date 2021/7/9
 */
public class Solution17_10 {
    //Boyer-Moore 投票算法
    //遍历两遍
    //1.第一次遍历每次减去两个不同的数，看最后是否有剩下的数，如果有，这个数可能是主要元素。这个删除的操做用一个count来实现
    //对于初始元素count为1，遇到一个相同元素count加1，遇到一个不同的元素，则count减1。count为0,当前元素更新为下一个元素，count更新为1，
    //遍历完成后，最终的count便为剩余相同元素的数量，这个元素可能是主要元素
    //2.第二次遍历统计这个可能元素在数组中出现的次数，如果超过数组长度一半则是主要元素，否则没有主要元素
    public int majorityElement(int[] nums) {
        if(nums.length<1)return -1;
        int candidate = nums[0];
        int count = 1;
        for(int i=1;i<nums.length;i++){
            if(count==0) candidate = nums[i];
            if(candidate == nums[i]) count++;
            else count--;
        }
        if(count==0)return -1;
        count = 0;
        for(int i=0;i<nums.length;i++){
            if(candidate==nums[i]){
                count++;
            }
        }
        if(count>nums.length/2)return candidate;
        return -1;
    }
}
