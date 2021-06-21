package leetcode;

import datastructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 103. 二叉树的锯齿形层序遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 * @author chenzw
 * @date 2021/6/21
 */
public class Solution103 {
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root==null)return ans;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean reverse = false;
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> layer = new ArrayList<>(size);
            while(size>0){
                root = queue.poll();
                if(reverse)layer.add(0,root.val);
                else layer.add(root.val);
                if(root.left!=null)queue.offer(root.left);
                if(root.right!=null)queue.offer(root.right);
                size--;
            }
            reverse = !reverse;
            ans.add(layer);
        }
        return ans;
    }
}
