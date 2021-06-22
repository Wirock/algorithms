package leetcode;

import datastructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 3
 * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[[1]]
 *
 *
 * 提示：
 *
 * 1 <= n <= 8
 * @author chenzw
 * @date 2021/6/16
 */
public class Solution95 {
    public static List<TreeNode> generateTrees(int n) {
        return generateTrees(1,n);
    }
    /*public static List<TreeNode> generateTrees(int left,int right) {
        List<TreeNode> ans = new ArrayList<>();
        for(int i=left;i<=right;i++){
            List<TreeNode> temp = new ArrayList<>();
            if(i>left){
                List<TreeNode> leftNodes = generateTrees(left, i - 1);
                for(TreeNode t:leftNodes){
                    TreeNode root = new TreeNode(i);
                    root.left = t;
                    temp.add(root);
                }
            }else{
                TreeNode root = new TreeNode(i);
                temp.add(root);
            }
            if(i<right){
                List<TreeNode> rightNodes = generateTrees(i+1, right);
                for(TreeNode root:temp){
                    for(TreeNode t:rightNodes){
                        TreeNode copy = copy(root);
                        copy.right = t;
                        ans.add(copy);
                    }
                }
            }else{
                ans.addAll(temp);
            }
        }
        return ans;
    }

    private static TreeNode copy(TreeNode root){
        if(root==null)return null;
        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = copy(root.left);
        newRoot.right = copy(root.right);
        return newRoot;
    }*/

    //可以不必复制，包左右子树
    public static List<TreeNode> generateTrees(int left,int right) {
        List<TreeNode> ans = new ArrayList<>();
        if(left>right){
            ans.add(null);
            return ans;
        }
        for(int i=left;i<=right;i++){
            List<TreeNode> leftTrees = generateTrees(left, i - 1);
            List<TreeNode> rightTrees = generateTrees(i+1, right);
            for(TreeNode l:leftTrees){
                for(TreeNode r:rightTrees){
                    TreeNode root = new TreeNode(i);
                    root.left=l;
                    root.right=r;
                    ans.add(root);
                }
            }
        }
        return ans;
    }

}
