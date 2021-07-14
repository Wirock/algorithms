package leetcode;

import datastructure.list.ListNode;

/**
 * 143. 重排链表
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 *
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 *
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * 通过次数106,123提交次数174,005
 * @author chenzw
 * @date 2021/7/14
 */
public class Solution143 {
    //1.链表二等分，节点不平均则左段多一个节点
    //2.右段链表反转
    //3.左右两个链表归并，相间排列
    public void reorderList(ListNode head) {
        ListNode cur = head;
        int count=0;
        while(cur!=null){
            count++;
            cur=cur.next;
        }
        if(count<=2)return;
        ListNode list1 = head;
        ListNode list2 = head;
        count=(count-1)>>1;
        while(count-->0)list2=list2.next;
        cur = list2.next;
        list2.next=null;
        list2=cur;
        ListNode pre = null;
        while(cur.next!=null){
            list2 = list2.next;
            cur.next=pre;
            pre = cur;
            cur = list2;
        }
        list2.next=pre;
        while(list1!=null&&list2!=null){
            cur = list1;
            list1 = list1.next;
            cur.next=list2;
            list2 = list2.next;
            cur = cur.next;
            cur.next = list1;
        }
    }
}
