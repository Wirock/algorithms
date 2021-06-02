package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 525. 连续数组
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 * 示例 2:
 *
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1
 * @author chenzw
 * @date 2021/6/3
 */
public class solution525 {
    //前缀和+哈希，sum[j]-sum[i]=j-i转换为sum[j]-j=sum[i]-i
    public static int findMaxLength(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int ans = 0;
        int last = 0;
        map.put(0,0);
        for(int i=1;i<=nums.length;i++){
            int sum=last+nums[i-1];
            Integer key = 2*sum-i;
            if(map.containsKey(key)){
                ans = Math.max(ans,i-map.get(key));
            }else{
                map.put(key,i);
            }
            last = sum;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(findMaxLength(new int[]{1,0}));
    }
}
