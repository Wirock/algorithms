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
public class Solution24 {
    /*public static ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode first = head;//两两交换的第一个节点
        ListNode second = head.next;//两两交换的第二个节点
        ListNode nextFirst = second.next;
        ListNode tail = null;
        head = second;
        while(second != null){
            second.next = first;
            if(tail!=null){
                tail.next = second;
            }
            tail = first;
            first = nextFirst;
            if(nextFirst!=null){
                second = nextFirst.next;
                if(second!=null){
                    nextFirst = second.next;
                }
            }else{
                second = null;
            }
        }
        tail.next = first;
        return head;
    }*/

    public static ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode first = head;//两两交换的第一个节点
        ListNode second = head.next;//两两交换的第二个节点
        ListNode nextFirst = second.next;
        ListNode tail = null;
        head = second;
        while(second != null){
            second.next = first;
            if(tail!=null){
                tail.next = second;
            }
            tail = first;
            first = nextFirst;
            if(nextFirst!=null){
                second = nextFirst.next;
                if(second!=null){
                    nextFirst = second.next;
                }
            }else{
                second = null;
            }
        }
        tail.next = first;
        return head;
    }
    public static void main(String[] args) {
        CommonUtil.printListNode(swapPairs(CommonUtil.array2ListNode(new int[]{1,2,3,4})));
    }
}
