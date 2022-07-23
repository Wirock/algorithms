package leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 1713. 得到子序列的最少操作次数
 * 给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。
 *
 * 每一次操作中，你可以在 arr 的任意位置插入任一整数。比方说，如果 arr = [1,4,1,2] ，那么你可以在中间添加 3 得到 [1,4,3,1,2] 。你可以在数组最开始或最后面添加整数。
 *
 * 请你返回 最少 操作次数，使得 target 成为 arr 的一个子序列。
 *
 * 一个数组的 子序列 指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的子序列（加粗元素），但 [2,4,2] 不是子序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：target = [5,1,3], arr = [9,4,2,3,4]
 * 输出：2
 * 解释：你可以添加 5 和 1 ，使得 arr 变为 [5,9,4,1,2,3,4] ，target 为 arr 的子序列。
 * 示例 2：
 *
 * 输入：target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= target.length, arr.length <= 105
 * 1 <= target[i], arr[i] <= 109
 * target 不包含任何重复元素。
 * @author chenzw
 * @date 2021/7/26
 */
public class Solution1713 {
    public int minOperations(int[] target, int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<target.length;i++)
            map.put(target[i], i);
        ArrayList<Integer> stack = new ArrayList<>();
        for(int num:arr){
            Integer idx = map.get(num);
            if(idx != null){
                int l=0,r=stack.size();
                while(l<r){
                    int mid = l + r >> 1;
                    if(stack.get(mid) >= idx)
                        r = mid;
                    else
                        l = mid + 1;
                }
                if(l == stack.size())
                    stack.add(idx);
                else
                    stack.set(l, idx);
            }
        }
        return target.length - stack.size();
    }

}
