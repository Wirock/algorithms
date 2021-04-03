package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 5708. 统计一个数组中好对子的数目
 * 给你一个数组 nums ，数组中只包含非负整数。定义 rev(x) 的值为将整数 x 各个数字位反转得到的结果。比方说 rev(123) = 321 ， rev(120) = 21 。我们称满足下面条件的下标对 (i, j) 是 好的 ：
 *
 * 0 <= i < j < nums.length
 * nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
 * 请你返回好下标对的数目。由于结果可能会很大，请将结果对 109 + 7 取余 后返回。
 * @author chenzw
 * @date 2021/4/4
 */
public class Solution5708 {
    //nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
    //转化为单坐标相关nums[i] - rev(nums[i]) == nums[j] - rev(nums[j])
    public static int countNicePairs(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int count=0;
        int mod=(int)1e9+7;
        for(int i=0;i<nums.length;i++){
            Integer diff = nums[i]-rev(nums[i]);
            int cur = map.getOrDefault(diff,0);
            count=(count+cur)%mod;
            map.put(diff,cur+1);
        }
        return count;
    }

    private static int rev(int num){
        int result=0;
        while(num>0){
            result=result*10+num%10;
            num/=10;
        }
        return result;
    }
}
