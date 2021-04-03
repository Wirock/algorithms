package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 * @author chenzw
 * @date 2021/3/31
 */
public class Solution90 {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        dfs(false,0,nums,result,new ArrayList<>());
        return result;
    }
    public static void dfs(boolean choosePre, int cur, int[] nums,List<List<Integer>> result,List<Integer> list) {
        if (cur == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        dfs(false, cur + 1, nums,result,list);
        //在递归时，若发现没有选择上一个数，且当前数字与上一个数相同，则可以跳过当前生成的子集
        //若当前节点nums[cur]与父节点nums[cur-1]相同，则跳过父节点nums[cur-1]且选择当前节点nums[cur]的分支与选择nums[cur-1]且跳过当前节点nums[index]的分支相同
        //此处进行剪枝
        if (!choosePre && cur > 0 && nums[cur - 1] == nums[cur]) {
            return;
        }
        list.add(nums[cur]);
        dfs(true, cur + 1, nums,result,list);
        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
    }
}
