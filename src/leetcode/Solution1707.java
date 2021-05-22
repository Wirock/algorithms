package leetcode;

import java.util.Arrays;

/**
 * 1707. 与数组中元素的最大异或值
 * 给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
 * <p>
 * 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] XOR xi) ，其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
 * <p>
 * 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个查询的答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
 * 输出：[3,3,7]
 * 解释：
 * 1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
 * 2) 1 XOR 2 = 3.
 * 3) 5 XOR 2 = 7.
 * 示例 2：
 * <p>
 * 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
 * 输出：[15,-1,5]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length, queries.length <= 105
 * queries[i].length == 2
 * 0 <= nums[j], xi, mi <= 109
 *
 * @author chenzw
 * @date 2021/5/23
 */
public class Solution1707 {
    class Trie {
        Trie left;
        Trie right;
        boolean disabled;
    }

    Trie trie = null;

    public int[] maximizeXor(int[] nums, int[][] queries) {
        trie = new Trie();
        Arrays.sort(nums);
        int[][] q = new int[queries.length][3];
        for (int i = 0; i < queries.length; i++) {
            q[i][0] = queries[i][0];
            q[i][1] = queries[i][1];
            q[i][2] = i;
        }
        Arrays.sort(q, (x, y) -> x[1] - y[1]);
        int[] ans = new int[queries.length];
        int index = 0;
        for (int i = 0; i < q.length; i++) {
            while (index < nums.length && nums[index] <= q[i][1]) {
                add(nums[index++]);
            }
            int val = getVal(q[i][0]);
            if (val == -1) ans[q[i][2]] = -1;
            else ans[q[i][2]] = q[i][0] ^ val;
        }
        return ans;
    }

    private void add(int n) {
        Trie node = trie;
        for (int i = 30; i >= 0; i--) {
            if (((n >> i) & 1) == 0) {
                if (node.left == null) node.left = new Trie();
                node = node.left;
            } else {
                if (node.right == null) node.right = new Trie();
                node = node.right;
            }
        }
    }

    private int getVal(int x) {
        Trie node = trie;
        if (node.left == null && node.right == null) return -1;
        int val = 0;
        for (int i = 30; i >= 0; i--) {
            if (((x >> i) & 1) == 0) {
                if (node.right != null) {
                    val |= 1 << i;
                    node = node.right;
                } else {
                    node = node.left;
                }
            } else {
                if (node.left != null) {
                    node = node.left;
                } else {
                    val |= 1 << i;
                    node = node.right;
                }
            }
        }
        return val;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution1707().maximizeXor(new int[]{0,1,2,3,4},new int[][]{{3,1},{1,3},{5,6}})));
    }
}
