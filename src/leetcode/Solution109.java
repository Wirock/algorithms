package leetcode;

import datastructure.list.ListNode;
import datastructure.tree.TreeNode;

/**
 * 109. 有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 * @author chenzw
 * @date 2021/6/21
 */
public class Solution109 {
    public TreeNode sortedListToBST(ListNode head) {
        int length = 0;
        ListNode cur = head;
        while(cur!=null){
            cur = cur.next;
            length++;
        }
        return sortedListToBST(head,length);
    }

    private TreeNode sortedListToBST(ListNode head,int length){
        if(length<=0)return null;
        if(length==1)return new TreeNode(head.val);
        int mid = (length+1)/2;
        ListNode cur = head;
        int i=mid;
        while(--i>0){
            cur = cur.next;
        }
        TreeNode root = new TreeNode(cur.val);
        root.left = sortedListToBST(head,mid-1);
        root.right = sortedListToBST(cur.next,length-mid);
        return root;
    }
}

