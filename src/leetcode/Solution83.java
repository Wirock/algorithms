package leetcode;

import datastructure.list.ListNode;

/**
 * 83. 删除排序链表中的重复元素
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 *
 * 返回同样按升序排列的结果链表。
 * @author chenzw
 * @date 2021/3/26
 */
public class Solution83 {
    public static ListNode deleteDuplicates(ListNode head) {
        if(head==null)return null;
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode pre = newHead;
        ListNode cur = head;
        ListNode next = cur.next;
        while(next!=null){
            if(cur.val==next.val){
                pre.next=next;
            }else{
                pre=cur;
            }
            cur = next;
            next = next.next;
        }
        return newHead.next;
    }
}
