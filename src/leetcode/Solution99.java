package leetcode;

import common.CommonUtil;
import datastructure.tree.TreeNode;

/**
 * 99. 恢复二叉搜索树
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 *
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,3,null,null,2]
 * 输出：[3,1,null,null,2]
 * 解释：3 不能是 1 左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
 * 示例 2：
 *
 *
 * 输入：root = [3,1,4,null,null,2]
 * 输出：[2,1,4,null,null,3]
 * 解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。
 *
 *
 * 提示：
 *
 * 树上节点的数目在范围 [2, 1000] 内
 * -231 <= Node.val <= 231 - 1
 * @author chenzw
 * @date 2021/6/20
 */
public class Solution99 {
    //Morris
    public static void recoverTree(TreeNode root) {
       TreeNode[] nodes = new TreeNode[2];
       int index=0;
       TreeNode last = null;
       TreeNode cur = root;
       while(cur!=null){
           if(cur.left==null){
               if(last!=null&&last.val>cur.val) {
                   if(index==0)nodes[index++] = last;
                   nodes[index] = cur;
               }
               last = cur;
               cur = cur.right;
           }else{
               TreeNode predecessor = cur.left;
               while(predecessor.right!=null&&predecessor.right!=cur)predecessor = predecessor.right;
               if(predecessor.right==null){
                   predecessor.right=cur;
                   cur = cur.left;
               }else{
                   if(last!=null&&last.val>cur.val) {
                       if(index==0)nodes[index++] = last;
                       nodes[index] = cur;
                   }
                   last = cur;
                   predecessor.right = null;
                   cur = cur.right;
               }
           }
       }
       int temp = nodes[0].val;
       nodes[0].val = nodes[1].val;
       nodes[1].val = temp;
    }

    public static void main(String[] args) {
        TreeNode root = CommonUtil.arrays2BSTByLevel(new Integer[]{3,1,4,null,null,2});
        recoverTree(root);
        CommonUtil.printTreeInOrder(root);
    }
}
