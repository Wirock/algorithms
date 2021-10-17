package leetcode;

import datastructure.tree.TreeNode;

/**
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * 示例 2：
 *
 *
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 *
 *
 *
 *
 * 提示：
 *
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 *
 *
 * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 * @author chenzw
 * @date 2021/10/17
 */
public class Solution230 {
    //morris遍历
    public int kthSmallest(TreeNode root, int k) {
        int ans=0;
        TreeNode cur = root;
        while(cur!=null){
            if(cur.left==null) {
                if(--k==0)return cur.val;
                cur = cur.right;
            }else{//当前节点存在左子树，则单点节点的前驱节点是左子树最右侧的节点
                TreeNode predecessor = cur.left;
                while(predecessor.right!=null&&predecessor.right!=cur)predecessor=predecessor.right;
                if(predecessor.right!=cur){//未建立前驱关系,建立前驱关系
                    predecessor.right = cur;
                    cur = cur.left;
                }else{//已建立前驱关系，则是第二次遍历到该节点，输出节点值，并解除前驱关系
                    predecessor.right = null;
                    if(--k==0)ans = cur.val;
                    cur = cur.right;
                }
            }
        }
        return ans;
    }
}
