package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 * @author chenzw
 * @date 2021/3/31
 */
public class Solution78 {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        dfs(0,nums,result,new ArrayList<>());
        return result;
    }

    private static void dfs(int cur,int[] nums,List<List<Integer>> result,List<Integer> list){
        if (cur == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        list.add(nums[cur]);
        dfs(cur + 1, nums,result,list);
        list.remove(list.size() - 1);
        dfs(cur + 1, nums,result,list);
    }
}
