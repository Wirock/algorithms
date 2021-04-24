package offer;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * 找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 *
 * 限制：
 *
 * 2 <= n <= 100000
 * @author chenzw
 * @date 2021/4/24
 */
public class Offer03 {
    //使用hashset的方式对于所有integer范围的数字都有小，没有利用到条件：所有数字都在 0～n-1
    /*public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(Integer num:nums){
            if(set.contains(num)){
                return num;
            }
            set.add(num);
        }
        return -1;
    }*/

    //利用到条件：所有数字都在 0～n-1，则只要对数组从头开始排序，当数值与索引对不上时就可以判断这个数是重复的
    public int findRepeatNumber(int[] nums) {
        for(int i=0;i<nums.length;i++){
            while(nums[i]!=i){
                int temp = nums[i];
                if(temp==nums[temp])return temp;
                nums[i]=nums[temp];
                nums[temp]=temp;
            }
        }
        return -1;
    }
}
