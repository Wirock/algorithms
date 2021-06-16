package leetcode;

import common.CommonUtil;
import datastructure.tree.TreeNode;

/**
 * 101. 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 *
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *
 * 进阶：
 *
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 * @author chenzw
 * @date 2021/6/16
 */
public class Solution101 {
    //1.递归
    public static boolean isSymmetric(TreeNode root) {
        if(root==null)return true;
        if(root.left==null&&root.right==null)return true;
        if(root.left==null||root.right==null)return false;
        return isSymmetric(root.left,root.right);
    }
    private static boolean isSymmetric(TreeNode p, TreeNode q) {
        if(p==null&&q==null)return true;
        if(p==null||q==null)return false;
        if(p.val==q.val)return isSymmetric(p.left,q.right)&&isSymmetric(p.right,q.left);
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isSymmetric(CommonUtil.arrays2BSTByLevel(new Integer[]{1,2,2,3,4,4,3})));
        System.out.println(isSymmetric(CommonUtil.arrays2BSTByLevel(new Integer[]{1,2,2,null,3,null,3})));
    }
}
