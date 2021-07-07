package leetcode;

/**
 * 930. 和相同的二元子数组
 * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
 *
 * 子数组 是数组的一段连续部分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,0,1,0,1], goal = 2
 * 输出：4
 * 解释：
 * 如下面黑体所示，有 4 个满足题目要求的子数组：
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * 示例 2：
 *
 * 输入：nums = [0,0,0,0,0], goal = 0
 * 输出：15
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * nums[i] 不是 0 就是 1
 * 0 <= goal <= nums.length
 * @author chenzw
 * @date 2021/7/8
 */
public class Solution930 {
    //hash+前缀和
    /*public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        int ans = 0;
        int sum = 0;
        map.put(0,1);
        for(int i=0;i<n;i++){
            sum+=nums[i];
            ans+=map.getOrDefault(sum-goal,0);
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return ans;
    }*/
    //滑动窗口,左端点区间
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int left1 = 0;
        int left2 = 0;
        int right = 0;
        int sum1 = 0;
        int sum2 = 0;
        int ans = 0;
        while(right<n){
            sum1 += nums[right];
            sum2 += nums[right];
            while(left1<=right&&sum1>goal){
                sum1-=nums[left1++];
            }
            while(left2<=right&&sum2>=goal){
                sum2-=nums[left2++];
            }
            ans += left2-left1;
            right++;
        }
        return ans;
    }
}
