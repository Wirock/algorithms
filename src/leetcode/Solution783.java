package leetcode;

import common.CommonUtil;
import datastructure.tree.TreeNode;

/**
 * 783. 二叉搜索树节点最小距离
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 *
 * 注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 * 示例 2：
 *
 *
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [2, 100] 内
 * 0 <= Node.val <= 105
 * @author chenzw
 * @date 2021/4/13
 */
public class Solution783 {
    /*public static int minDiffInBST(TreeNode root) {
        return minDiffInBST(root,Integer.MAX_VALUE);
    }
    public static int minDiffInBST(TreeNode root,int min) {
        if(root==null){
            return min;
        }
        if(root.left!=null){
            TreeNode leftChild = root.left;
            while(leftChild.right!=null){
                leftChild = leftChild.right;
            }
            min = Math.min(min,root.val-leftChild.val);
        }
        if(root.right!=null){
            TreeNode rightChild = root.right;
            while(rightChild.left!=null){
                rightChild = rightChild.left;
            }
            min = Math.min(min,rightChild.val-root.val);
        }
        min = minDiffInBST(root.left,min);
        min = minDiffInBST(root.right,min);
        return min;
    }*/

    private static int pre = -1;
    private static int min = Integer.MAX_VALUE;
    public static int minDiffInBST(TreeNode root) {
        pre = -1;
        min = Integer.MAX_VALUE;
        inOrder(root);
        return min;
    }
    public static void inOrder(TreeNode root){
        if(root==null){
            return;
        }
        inOrder(root.left);
        if(pre>-1){
            min=Math.min(min,root.val-pre);
        }
        pre=root.val;
        inOrder(root.right);
    }

    public static void main(String[] args) {
        System.out.println(minDiffInBST(CommonUtil.arrays2BSTByLevel(new Integer[]{4,2,6,1,3})));
        System.out.println(minDiffInBST(CommonUtil.arrays2BSTByLevel(new Integer[]{1,0,48,null,null,12,49})));
        System.out.println(minDiffInBST(CommonUtil.arrays2BSTByLevel(new Integer[]{90,69,null,49,89,null,52})));
    }
}
