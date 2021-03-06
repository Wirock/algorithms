package leetcode;

import common.CommonUtil;
import datastructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
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
 * 输出：[2,1]
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
 * @author chenzw
 * @date 2021/6/12
 */
public class Solution94 {
    //递归的方式
    /*public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root==null)return ans;
        if(root.left!=null)ans.addAll(inorderTraversal(root.left));
        ans.add(root.val);
        if(root.right!=null)ans.addAll(inorderTraversal(root.right));
        return ans;
    }*/

    //迭代
    /*public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root==null)return ans;
        Deque<TreeNode> stack = new LinkedList<>();
        while(root!=null||!stack.isEmpty()){
            while(root!=null) {
                stack.push(root);
                root=root.left;
            }
            root = stack.pop();
            ans.add(root.val);
            root = root.right;
        }
        return ans;
    }*/

    //Morris，对每个节点寻找它的前继节点，把当前节点及其左子树构建成右指针关联的链表关系。
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root==null)return ans;
        TreeNode cur = root;
        while(cur!=null){
            if(cur.left==null) {
                ans.add(cur.val);
                cur = cur.right;
            }else{//当前节点存在左子树，则单点节点的前驱节点是左子树最右侧的节点
                TreeNode predecessor = cur.left;
                while(predecessor.right!=null&&predecessor.right!=cur)predecessor=predecessor.right;
                if(predecessor.right!=cur){//未建立前驱关系,建立前驱关系
                    predecessor.right = cur;
                    cur = cur.left;
                }else{//已建立前驱关系，则是第二次遍历到该节点，输出节点值，并解除前驱关系
                    predecessor.right = null;
                    ans.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(inorderTraversal(CommonUtil.arrays2BSTByLevel(new Integer[]{1,null,2})).toArray()));
    }
}
