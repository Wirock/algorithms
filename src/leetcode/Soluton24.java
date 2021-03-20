package leetcode;

import common.CommonUtil;
import datastructure.list.ListNode;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * @author chenzw
 * @date 2021/3/20
 */
public class Soluton24 {
    public static ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        ListNode next = cur.next;
        ListNode temp = null;
        head = cur;
        while(cur != null){
            cur.next = pre;
            if(temp!=null){
                temp.next = cur;
            }
            temp = pre;
            pre = next;
            if(next!=null){
                cur = next.next;
                if(cur!=null){
                    next = cur.next;
                }
            }else{
                cur = null;
            }
        }
        temp.next = pre;
        return head;
    }

    public static void main(String[] args) {
        CommonUtil.printListNode(swapPairs(CommonUtil.array2ListNode(new int[]{1,2,3,4})));
    }
}
