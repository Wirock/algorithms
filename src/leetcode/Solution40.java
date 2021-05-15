package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 * @author chenzw
 * @date 2021/5/14
 */
public class Solution40 {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(true,ans,new ArrayList<>(),candidates,0,0,target);
        return ans;
    }

    /*private static void dfs(boolean choose,List<List<Integer>> result,List<Integer> list,int[] candidates,int index,int sum,int target){
        if(sum>target)return;
        if(sum==target){
            result.add(new ArrayList<>(list));
            return;
        }
        if(index==candidates.length)return;

        dfs(false,result,list,candidates,index+1,sum,target);
        if(!choose&&candidates[index]==candidates[index-1])return;
        list.add(candidates[index]);
        dfs(true,result,list,candidates,index+1,sum+candidates[index],target);
        list.remove(list.size()-1);
    }*/

    //优化，减少递归层数
    private static void dfs(boolean choose,List<List<Integer>> result,List<Integer> list,int[] candidates,int index,int sum,int target){
        int nextIndex = index+1;
        if(nextIndex<candidates.length) {
            dfs(false, result, list, candidates, nextIndex, sum, target);
        }
        if(!choose&&candidates[index]==candidates[index-1])return;
        int nextSum = sum+candidates[index];
        if(nextSum>target)return;
        list.add(candidates[index]);
        if(nextSum==target){
            result.add(new ArrayList<>(list));
        }else if(nextIndex<candidates.length) {
            dfs(true, result, list, candidates, nextIndex, nextSum, target);
        }
        list.remove(list.size()-1);
    }

    public static void main(String[] args) {
        //List<List<Integer>> lists = combinationSum2(new int[]{2, 3, 6, 7,1,1}, 7);
        List<List<Integer>> lists = combinationSum2(new int[]{2,5,2,1,2}, 5);
        for(List<Integer> list:lists){
            System.out.println(Arrays.toString(list.toArray()));
        }
    }
}
