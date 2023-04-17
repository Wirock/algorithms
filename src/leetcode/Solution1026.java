package leetcode;

import datastructure.tree.TreeNode;

/**
 * 1026. 节点与其祖先之间的最大差值
 * 给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。
 * <p>
 * （如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * 输出：7
 * 解释：
 * 我们有大量的节点与其祖先的差值，其中一些如下：
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * 在所有可能的差值中，最大值 7 由 |8 - 1| = 7 得出。
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,null,2,null,0,3]
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中的节点数在 2 到 5000 之间。
 * 0 <= Node.val <= 105
 * Created by Chenzw on 2023/4/18 6:34
 */
public class Solution1026 {
    /*Deque<int[]> stack = new LinkedList<>();
    int max = 0;

    public int maxAncestorDiff(TreeNode root) {
        dfs(root);
        return max;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        int[] couple = new int[2];
        if (stack.isEmpty()) {
            couple[0] = node.val;
            couple[1] = node.val;
        } else {
            int[] p = stack.peek();
            couple[0] = Math.min(node.val, p[0]);
            couple[1] = Math.max(node.val, p[1]);
        }
        max = Math.max(max, couple[1] - couple[0]);
        stack.push(couple);
        if (node.left != null) {
            dfs(node.left);
            stack.pop();
        }
        if (node.right != null) {
            dfs(node.right);
            stack.pop();
        }
    }*/

    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
    }

    private int  dfs(TreeNode node, int max, int min) {
        if(node == null){
            return 0;
        }
        max = Math.max(max, node.val);
        min = Math.min(min, node.val);
        int ans = max - min;
        ans = Math.max(ans, dfs(node.left, max, min));
        ans = Math.max(ans, dfs(node.right, max, min));
        return ans;
    }
}
