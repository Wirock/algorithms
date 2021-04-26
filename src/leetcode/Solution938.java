package leetcode;

import common.CommonUtil;
import datastructure.tree.TreeNode;

/**
 * 938. 二叉搜索树的范围和
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
 * 输出：32
 * 示例 2：
 *
 *
 * 输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 * 输出：23
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 2 * 104] 内
 * 1 <= Node.val <= 105
 * 1 <= low <= high <= 105
 * 所有 Node.val 互不相同
 * @author chenzw
 * @date 2021/4/27
 */
public class Solution938 {
    public static int rangeSumBST(TreeNode root, int low, int high) {
        int sum = 0;
        if(root==null)return sum;
        if(root.val>low)sum += rangeSumBST(root.left,low,high);
        if(root.val>=low&&root.val<=high)sum+=root.val;
        if(root.val<high)sum += rangeSumBST(root.right,low,high);
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(rangeSumBST(CommonUtil.arrays2BSTByLevel(new Integer[]{10,5,15,3,7,null,18}),7,15));
        System.out.println(rangeSumBST(CommonUtil.arrays2BSTByLevel(new Integer[]{10,5,15,3,7,13,18,1,null,6}),6,10));
    }
}
