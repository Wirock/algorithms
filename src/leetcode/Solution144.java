package leetcode;

import datastructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 144. 二叉树的前序遍历
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 *
 *
 * 输入：root = [1,2]
 * 输出：[1,2]
 * 示例 5：
 *
 *
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 * @author chenzw
 * @date 2021/6/30
 */
public class Solution144 {
    //递归
    /*public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root==null)return list;
        preorderTraversal(root,list);
        return list;
   }
    private void preorderTraversal(TreeNode root,List<Integer> list) {
        list.add(root.val);
        if(root.left!=null)preorderTraversal(root.left,list);
        if(root.right!=null)preorderTraversal(root.right,list);
    }*/

    //迭代
    /*public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while(root!=null){
            while(root!=null){
                list.add(root.val);
                if(root.right!=null)stack.push(root.right);
                root = root.left;
            }
            if(!stack.isEmpty())root = stack.pop();
        }
        return list;
   }*/

    //Morris单次遍历，没有还原节点
    /*public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root==null)return list;
        while(root!=null){
           list.add(root.val);
           if(root.left==null){
               root = root.right;
           }else{
               TreeNode predecessor = root.left;
               while(predecessor.right!=null)predecessor = predecessor.right;
               predecessor.right = root.right;
               root = root.left;
           }
        }
        return list;
    }*/

    //Morris两次遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root==null)return list;
        while(root!=null){
            if(root.left==null){
                list.add(root.val);
                root = root.right;
            }else{
                TreeNode predecessor = root.left;
                while(predecessor.right!=null&&predecessor.right!=root)predecessor = predecessor.right;
                if(predecessor.right==null) {
                    list.add(root.val);
                    predecessor.right = root;
                    root = root.left;
                }else{
                    root = root.right;
                }
            }
        }
        return list;
    }
}
