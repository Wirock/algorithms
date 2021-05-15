package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2：
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 * @author chenzw
 * @date 2021/5/14
 */
public class Solution39 {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans,new ArrayList<>(),candidates,0,0,target);
        return ans;
    }

    /*private static void dfs(List<List<Integer>> result,List<Integer> list,int[] candidates,int index,int sum,int target){
        if(sum>target)return;
        if(sum==target){
            result.add(new ArrayList<>(list));
            return;
        }
        for(int i=index;i<candidates.length;i++){
            list.add(candidates[i]);
            dfs(result,list,candidates,i,sum+candidates[i],target);
            list.remove(list.size()-1);
        }
    }*/

    //优化，减少递归层数
    private static void dfs(List<List<Integer>> result,List<Integer> list,int[] candidates,int index,int sum,int target){
        for(int i=index;i<candidates.length;i++){
            int nextSum = sum+candidates[i];
            if(nextSum>target)continue;
            list.add(candidates[i]);
            if(nextSum==target){
                result.add(new ArrayList<>(list));
            }else{
                dfs(result,list,candidates,i,nextSum,target);
            }
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum(new int[]{2, 3, 6, 7}, 7);
        for(List<Integer> list:lists){
            System.out.println(Arrays.toString(list.toArray()));
        }
    }
}
