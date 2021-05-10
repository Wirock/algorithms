package leetcode;

import common.CommonUtil;
import datastructure.tree.TreeNode;

/**
 * 872. 叶子相似的树
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 *
 *
 *
 * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
 *
 * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 *
 * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * 输出：true
 * 示例 2：
 *
 * 输入：root1 = [1], root2 = [1]
 * 输出：true
 * 示例 3：
 *
 * 输入：root1 = [1], root2 = [2]
 * 输出：false
 * 示例 4：
 *
 * 输入：root1 = [1,2], root2 = [2,2]
 * 输出：true
 * 示例 5：
 *
 *
 *
 * 输入：root1 = [1,2,3], root2 = [1,3,2]
 * 输出：false
 *
 *
 * 提示：
 *
 * 给定的两棵树可能会有 1 到 200 个结点。
 * 给定的两棵树上的值介于 0 到 200 之间。
 * @author chenzw
 * @date 2021/5/10
 */
public class Solution872 {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        StringBuilder leaf1 = new StringBuilder();
        getLeafString(leaf1,root1);
        StringBuilder leaf2 = new StringBuilder();
        getLeafString(leaf2,root2);
        System.out.println(leaf1);
        System.out.println(leaf2);
        return leaf1.toString().equals(leaf2.toString());
    }

    private void getLeafString(StringBuilder leafStr,TreeNode root){
        if(root==null)return;
        if(root.left==null&&root.right==null){
            leafStr.append(root.val);
            leafStr.append(",");
            return;
        }
        getLeafString(leafStr,root.left);
        getLeafString(leafStr,root.right);
    }

    public static void main(String[] args) {
        //System.out.println(new Solution872().leafSimilar(CommonUtil.arrays2BSTByLevel(new Integer[]{3,5,1,6,2,9,8,null,null,7,4}),CommonUtil.arrays2BSTByLevel(new Integer[]{3,5,1,6,7,4,2,null,null,null,null,null,null,9,8})));
        System.out.println(new Solution872().leafSimilar(CommonUtil.arrays2BSTByLevel(new Integer[]{3,5,1,6,2,9,8,null,null,7,14}),CommonUtil.arrays2BSTByLevel(new Integer[]{3,5,1,6,71,4,2,null,null,null,null,null,null,9,8})));
    }
}
