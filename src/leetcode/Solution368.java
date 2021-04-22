package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 368. 最大整除子集
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[1,2]
 * 解释：[1,3] 也会被视为正确答案。
 * 示例 2：
 *
 * 输入：nums = [1,2,4,8]
 * 输出：[1,2,4,8]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 109
 * nums 中的所有整数 互不相同
 * @author chenzw
 * @date 2021/4/23
 */
public class Solution368 {
    //暴力dfs超时o(2^n)
    /*public static List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums,0,ans,new ArrayList<>());
        return ans;
    }

    private static void dfs(int[] nums,int index,List<Integer> ans,List<Integer> list){
        if(index==nums.length){
            if(list.size()>ans.size()){
                ans.clear();
                ans.addAll(list);
            }
            return;
        }
        dfs(nums,index+1,ans,list);
        if(list.size()>0){
            if(nums[index]%list.get(list.size()-1)!=0){
                return;
            }
        }
        list.add(nums[index]);
        dfs(nums,index+1,ans,list);
        list.remove(list.size()-1);
    }*/

    //动态规划
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(nums);
        int max = 1;
        int maxIndex = 0;
        int[] dp = new int[nums.length];
        dp[0]=1;
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<i;j++){
                if(nums[i]%nums[j]==0){
                    dp[i] = Math.max(dp[j]+1,dp[i]);
                }
            }
            if(dp[i]==0)dp[i]=1;
            if(dp[i]>max){
                max=dp[i];
                maxIndex=i;
            }
        }
        for(int i=maxIndex;i>=0;i--){
            if(dp[i]==max&&nums[maxIndex]%nums[i]==0){
                ans.add(nums[i]);
                maxIndex=i;
                max--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        /*int[] nums = new int[1000];
        for(int i=0;i<nums.length;i++){
            nums[i]=i+1;
        }
        System.out.println(Arrays.toString(nums));*/
        System.out.println(Arrays.toString(largestDivisibleSubset(new int[]{5,9,18,54,108,540,90,180,360,720}).toArray()));
    }
}
