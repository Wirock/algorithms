package leetcode;

import datastructure.tree.TreeNode;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
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
public class Solution105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder,0,inorder,0,inorder.length-1);
    }

    public TreeNode buildTree(int[] preorder,int l1, int[] inorder,int l2, int r2) {
        if(l1>=preorder.length||l2>r2)return null;
        TreeNode root = new TreeNode(preorder[l1]);
        for(int i=l2;i<=r2;i++){
            if(root.val==inorder[i]){
                root.left = buildTree(preorder,l1+1,inorder,l2,i-1);
                root.right = buildTree(preorder,l1+i-l2+1,inorder,i+1,r2);
                break;
            }
        }
        return root;
    }
}
