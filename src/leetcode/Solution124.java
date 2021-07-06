package leetcode;

import datastructure.tree.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * 示例 2：
 *
 *
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *
 *
 * 提示：
 *
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 * @author chenzw
 * @date 2021/6/23
 */
public class Solution124 {

    //1.记忆化递归
    /*Map<TreeNode,Integer> map = new HashMap<>();
    public int maxPathSum(TreeNode root) {
        if(root==null)return 0;
        getMaxPathSumFromRootToMap(root);
        return getMaxPathSum(root);
    }
    private int getMaxPathSum(TreeNode root) {
        if (root == null) return 0;
        int max = Integer.MIN_VALUE;
        int leftMax = 0;
        int rightMax = 0;
        if (root.left != null) {
            leftMax = Math.max(leftMax, map.get(root.left));
            max = Math.max(max, getMaxPathSum(root.left));
        }
        if (root.right != null) {
            rightMax = Math.max(rightMax, map.get(root.right));
            max = Math.max(max,getMaxPathSum(root.right));
        }
        max = Math.max(max,root.val+leftMax+rightMax);
        return max;
    }
    //所有从父节点到子节点方向的最大路径
    private int getMaxPathSumFromRootToMap(TreeNode root){
        int maxSum = root.val;
        if(root.left!=null) {
            int leftMax = getMaxPathSumFromRootToMap(root.left);
            maxSum = Math.max(maxSum, root.val + leftMax);
        }
        if(root.right!=null) {
            int rightMax = getMaxPathSumFromRootToMap(root.right);
            maxSum = Math.max(maxSum, root.val + rightMax);
        }
        map.put(root,maxSum);
        return maxSum;
    }*/

    int max;
    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        getMaxPathSum(root);
        return max;
    }
    private int getMaxPathSum(TreeNode root) {
        if (root == null) return 0;
        int leftMax = Math.max(0, getMaxPathSum(root.left));
        int rightMax = Math.max(0, getMaxPathSum(root.right));
        max = Math.max(max,root.val+leftMax+rightMax);
        return root.val+Math.max(leftMax,rightMax);
    }
}
