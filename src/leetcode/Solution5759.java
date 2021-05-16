package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 5759. 找出所有子集的异或总和再求和
 * 一个数组的 异或总和 定义为数组中所有元素按位 XOR 的结果；如果数组为 空 ，则异或总和为 0 。
 *
 * 例如，数组 [2,5,6] 的 异或总和 为 2 XOR 5 XOR 6 = 1 。
 * 给你一个数组 nums ，请你求出 nums 中每个 子集 的 异或总和 ，计算并返回这些值相加之 和 。
 *
 * 注意：在本题中，元素 相同 的不同子集应 多次 计数。
 *
 * 数组 a 是数组 b 的一个 子集 的前提条件是：从 b 删除几个（也可能不删除）元素能够得到 a 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3]
 * 输出：6
 * 解释：[1,3] 共有 4 个子集：
 * - 空子集的异或总和是 0 。
 * - [1] 的异或总和为 1 。
 * - [3] 的异或总和为 3 。
 * - [1,3] 的异或总和为 1 XOR 3 = 2 。
 * 0 + 1 + 3 + 2 = 6
 * 示例 2：
 *
 * 输入：nums = [5,1,6]
 * 输出：28
 * 解释：[5,1,6] 共有 8 个子集：
 * - 空子集的异或总和是 0 。
 * - [5] 的异或总和为 5 。
 * - [1] 的异或总和为 1 。
 * - [6] 的异或总和为 6 。
 * - [5,1] 的异或总和为 5 XOR 1 = 4 。
 * - [5,6] 的异或总和为 5 XOR 6 = 3 。
 * - [1,6] 的异或总和为 1 XOR 6 = 7 。
 * - [5,1,6] 的异或总和为 5 XOR 1 XOR 6 = 2 。
 * 0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28
 * 示例 3：
 *
 * 输入：nums = [3,4,5,6,7,8]
 * 输出：480
 * 解释：每个子集的全部异或总和值之和为 480 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 12
 * 1 <= nums[i] <= 20
 * @author chenzw
 * @date 2021/5/16
 */
public class Solution5759 {
    //dfs
    /*public int subsetXORSum(int[] nums) {
        List<Integer> result = new ArrayList<>();
        Arrays.sort(nums);
        dfs(0,nums,result,0);
        return result.stream().mapToInt(x->x).sum();
    }

    private void dfs(int cur, int[] nums, List<Integer> result, int sum){
        if (cur == nums.length) {
            result.add(sum);
            return;
        }
        sum^=nums[cur];
        dfs(cur + 1, nums,result,sum);
        sum^=nums[cur];
        dfs(cur + 1, nums,result,sum);
    }*/
    //2.位运算枚举，每一个s表示一个子集，倒数第i为是否为1表示子集是否包含nums[i]
    public static int subsetXORSum(int[] nums) {
        int n = nums.length;
        int max = 1<<n;
        int sum=0;
        for(int s=1;s<max;s++){
            int xor = 0;
            for(int i=0;i<n;i++){
                if(((s>>i)&1)==1)xor^=nums[i];
            }
            sum+=xor;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(subsetXORSum(new int[]{1,3}));
    }
}
