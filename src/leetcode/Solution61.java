package leetcode;

import common.CommonUtil;
import datastructure.list.ListNode;

/**
 * 61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * @author chenzw
 * @date 2021/3/27
 */
public class Solution61 {
    /*public static ListNode rotateRight(ListNode head, int k) {
        if(k==0||head==null||head.next==null) return head;
        ListNode cur=head;
        ListNode kpre=null;
        int count=1;
        while(count<=k){
            if(count>1){
                cur=head;
                k=k%count;
                count=1;
            }
            while(cur.next!=null){
                count++;
                cur=cur.next;
                if(count==k+1){
                    kpre=head;
                }else if(kpre!=null){
                    kpre=kpre.next;
                }
            }
        }
        if(kpre==null){
            return head;
        }
        cur.next=head;
        head=kpre.next;
        kpre.next=null;
        return head;
    }*/
    public static ListNode rotateRight(ListNode head, int k) {
        if(k==0||head==null||head.next==null) return head;
        ListNode cur=head;
        int len=1;
        while(cur.next!=null){
            cur = cur.next;
            len++;
        }
        cur.next=head;
        k = len-k%len;
        while(k-->0){
            cur=cur.next;
        }
        head=cur.next;
        cur.next=null;
        return head;
    }

    public static void main(String[] args) {
        CommonUtil.printListNode(rotateRight(CommonUtil.array2ListNode(new int[]{1,2,3,4,5}),6));
    }
}
