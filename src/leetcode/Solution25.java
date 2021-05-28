package leetcode;

import common.CommonUtil;
import datastructure.list.ListNode;

/**
 *
 * 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 进阶：
 *
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * @author chenzw
 * @date 2021/3/20
 */
public class Solution25 {
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(k<2){
            return head;
        }
        ListNode result = null;
        ListNode kHead = null;
        ListNode kTail = null;
        ListNode kCur = null;
        ListNode tail = null;
        ListNode pre = null;
        ListNode next = head;
        while(next!=null){
            int count = 0;
            kHead = next;
            kTail = next;
            kCur = kHead.next;
            //翻转k个节点
            while(next!=null){
                if(++count==k){
                    while(kCur!=null&&count-->1){
                        pre = kCur.next;
                        //相邻两点翻转
                        kCur.next = kHead;
                        if(kHead.next==kCur){
                            kHead.next=null;
                        }
                        kHead = kCur;
                        kCur = pre;
                    }
                    next = kCur;
                    break;
                }else{
                    next = next.next;
                }
            }

            if(result==null){
                result=kHead;
                tail = kTail;
            }else{
                tail.next=kHead;
                tail = kTail;
            }
        }
        return result;
    }

    public static ListNode reverseKGroup2(ListNode head, int k) {
        if(k<2){
            return head;
        }
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode next = head;
        ListNode ans=null;
        int count = 0;
        while(next!=null){
            if(++count==k){
                //截断k位后的部分
                ListNode tail=next;
                next = next.next;
                tail.next = null;

                //把连续k个节点的部分翻转，前后接上原来的部分
                tail = pre.next;
                ListNode reverse = reverse(pre.next);
                if(ans==null)ans=reverse;
                pre.next = reverse;
                tail.next = next;
                pre = tail;
                count=0;
            }else{
                next = next.next;
            }
        }
        if(ans==null)ans=head;
        return ans;
    }
    private static ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /*public static ListNode reverseKGroup2(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode next = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = next;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }

    public static ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }*/

    public static void main(String[] args) {
        int[] array = new int[100000];
        for(int i=0;i<array.length;i++){
            array[i]=i+1;
        }
        ListNode listNode = CommonUtil.array2ListNode(array);
        long start = System.currentTimeMillis();
        ListNode result = reverseKGroup2(listNode,10);
        long end = System.currentTimeMillis();
        CommonUtil.printListNode(result);
        System.out.println(end-start);
        //CommonUtil.printListNode(reverseKGroup(CommonUtil.array2ListNode(new int[]{1,2,3,4,5}),3));
    }

}
