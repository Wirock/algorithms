package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 * @author chenzw
 * @date 2021/5/24
 */
public class Solution47 {
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] exists = new boolean[nums.length];
        dfs(ans, new ArrayList<>(),nums,exists);
        return ans;
    }

    private static void dfs(List<List<Integer>> ans,List<Integer> list,int[] nums,boolean[] exists){
        if(list.size()==nums.length){
            ans.add(new ArrayList<>(list));
            return;
        }
        int pre = Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++) {
            if(exists[i]||pre==nums[i])continue;
            pre = nums[i];
            list.add(nums[i]);
            exists[i]=true;
            dfs(ans, list, nums,exists);
            list.remove(list.size()-1);
            exists[i]=false;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> permute = permuteUnique(new int[]{0,1,0,0,9});
        for(List<Integer> l: permute){
            System.out.println(Arrays.toString(l.toArray()));
        }
    }
}
