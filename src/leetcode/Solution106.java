package leetcode;

import datastructure.tree.TreeNode;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * @author chenzw
 * @date 2021/6/21
 */
public class Solution106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder,0,inorder.length-1,postorder,postorder.length-1);
    }

    public TreeNode buildTree(int[] inorder,int l1, int r1, int[] postorder, int r2) {
        if(r2<0||l1>r1)return null;
        TreeNode root = new TreeNode(postorder[r2]);
        for(int i=l1;i<inorder.length;i++){
            if(root.val==inorder[i]){
                root.right = buildTree(inorder,i+1,r1,postorder,r2-1);
                root.left = buildTree(inorder,l1,i-1,postorder,r2-r1+i-1);
                break;
            }
        }
        return root;
    }
}
