package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 220. 存在重复元素 III
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在两个下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 *
 * 如果存在则返回 true，不存在返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [1,0,1,1], k = 1, t = 2
 * 输出：true
 * 示例 3：
 *
 * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出：false
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 104
 * 0 <= t <= 231 - 1
 * @author chenzw
 * @date 2021/4/17
 */
public class Solution220 {
    /*public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for(int i=0;i<nums.length-k;i++){
            for(int j=i+1;j<=i+k;j++){
                if(Math.abs((long)nums[i]-(long)nums[j])<=t){
                    return true;
                }
            }
        }
        return false;
    }*/

    //滑动窗口+二分查找 o(nlog(min(n,k)))
    /*public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for(int i=0;i<nums.length;i++){
            if(i>k){
                set.remove((long)nums[i-k-1]);
            }
            if(set.contains((long)nums[i])){
                return true;
            }
            Long ceiling = set.ceiling((long) nums[i]-(long)t);
            if(ceiling!=null&&ceiling<=(long)nums[i]+(long)t){
                return true;
            }
            set.add((long) nums[i]);
        }
        return false;
    }*/

    //桶
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        Map<Integer,Integer>  bucket = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            Integer key = getKey(nums[i],t);
            if(i>k){
                bucket.remove(getKey(nums[i-k-1],t));
            }
            if(bucket.containsKey(key))return true;
            Integer left = bucket.get(key-1);
            if(left!=null&&(long)nums[i]-(long)left<=t)return true;
            Integer right = bucket.get(key+1);
            if(right!=null&&(long)right-(long)nums[i]<=t)return true;
            bucket.put(key,nums[i]);
        }
        return false;
    }

    private static int getKey(int num,int t){
        if(num<0)return (num+1)/(t+1)-1;
        return num/(t+1);
    }

    public static void main(String[] args) {
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1,2,3,1},3,0));
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1,0,1,1},1,2));
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9},2,3));
        System.out.println(containsNearbyAlmostDuplicate(new int[]{-2147483648,2147483647},1,1));
        System.out.println(containsNearbyAlmostDuplicate(new int[]{8,7,15,1,6,1,9,15},1,3));
        System.out.println(containsNearbyAlmostDuplicate(new int[]{2147483640,2147483641},1,100));
    }
}
