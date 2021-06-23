package leetcode;

import common.CommonUtil;
import datastructure.tree.TreeNode;

/**
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * @author chenzw
 * @date 2021/6/19
 */
public class Solution98 {
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }
    public static boolean isValidBST(TreeNode root,long min,long max) {
        if(root==null)return true;
        if(root.left!=null) {
            if(root.left.val<min||root.left.val>max)return false;
            if(root.left.val >= root.val)return false;
            if(!isValidBST(root.left,min,(long)root.val-1L))return false;
        }
        if(root.right!=null) {
            if(root.right.val<min||root.right.val>max)return false;
            if(root.right.val<=root.val)return false;
            if(!isValidBST(root.right,(long)root.val+1L,max))return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isValidBST(CommonUtil.arrays2BSTByLevel(new Integer[]{5,1,4,null,null,3,6})));
        System.out.println(isValidBST(CommonUtil.arrays2BSTByLevel(new Integer[]{2,1,3})));
        System.out.println(isValidBST(CommonUtil.arrays2BSTByLevel(new Integer[]{5,4,6,null,null,3,7})));
        System.out.println(isValidBST(CommonUtil.arrays2BSTByLevel(new Integer[]{1,null,1})));
    }
}
