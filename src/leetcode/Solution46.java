package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 * @author chenzw
 * @date 2021/5/23
 */
public class Solution46 {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans,new ArrayList<>(),nums,0);
        return ans;
    }

    private static void dfs(List<List<Integer>> ans,List<Integer> list,int[] nums,int index){
        if(index==nums.length){
            ans.add(new ArrayList<>(list));
            return;
        }
        for(int num:nums){
            if(!list.contains(num)){
                list.add(num);
                dfs(ans,list,nums,index+1);
                list.remove(list.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> permute = permute(new int[]{1, 2, 3});
        for(List<Integer> l: permute){
            System.out.println(Arrays.toString(l.toArray()));
        }
    }
}
