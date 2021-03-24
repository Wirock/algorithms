package leetcode;

import common.CommonUtil;
import datastructure.list.ListNode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 *
 *
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 *
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：lists = [[]]
 * 输出：[]
 *
 * @author chenzw
 * @date 2021/3/18
 */
public class Solution23 {
    /*public static ListNode mergeKLists(ListNode[] lists) {
        if(lists==null||lists.length==0){
            return null;
        }
        if(lists.length==1){
            return lists[0];
        }
        sort(lists);
        ListNode head = lists[0];
        if(head==null){
            return head;
        }
        ListNode cur = head;
        while(cur!=null){
            lists[0] = lists[0].next;
            if(lists[1]==null)break;
            sort(lists);
            cur.next = lists[0];
            cur = cur.next;
        }
        return head;
    }

    private static void sort(ListNode[] lists){
        Arrays.sort(lists,new Comparator<ListNode>(){
            @Override
            public int compare(ListNode l1,ListNode l2){
                if(l1==null) return 1;
                if(l2==null) return -1;
                return l1.val-l2.val;
            }
        });
    }*/
    public static ListNode mergeKLists(ListNode[] lists) {
        return mergeLists(lists,0,lists.length-1);
    }
    private static ListNode mergeLists(ListNode[] lists,int left,int right) {
        if(left==right)return lists[left];
        int mid = (left+right)/2;
        ListNode l1 = mergeLists(lists, left, mid);
        ListNode l2 = mergeLists(lists, mid+1, right);
        return merge2Lists(l1,l2);
    }
    private static ListNode merge2Lists(ListNode l1,ListNode l2){
        if(l1==null)return l2;
        if(l2==null)return l1;
        if(l1.val<l2.val) {
            l1.next = merge2Lists(l1.next, l2);
            return l1;
        }else{
            l2.next = merge2Lists(l1,l2.next);
            return l2;
        }
    }
    public static void main(String[] args) {
        CommonUtil.printListNode(mergeKLists(new ListNode[]{CommonUtil.array2ListNode(new int[]{1,2,3}),CommonUtil.array2ListNode(new int[]{3,14,21}),CommonUtil.array2ListNode(new int[]{11,12,32})}));
    }
}
