package leetcode;

import common.CommonUtil;
import datastructure.list.ListNode;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * @author chenzw
 * @date 2021/3/18
 */
public class Solution21 {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null)return l2;
        if(l2==null)return l1;
        ListNode result = null;
        if(l1.val<l2.val){
            result = l1;
            l1 = l1.next;
        }else{
            result = l2;
            l2 = l2.next;
        }
        ListNode cur = result;
        while(l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if(l1!=null){
            cur.next=l1;
        }else{
            cur.next=l2;
        }
        return result;
    }

    public static void main(String[] args) {
        CommonUtil.printListNode(mergeTwoLists(CommonUtil.array2ListNode(new int[]{1,3,5,7,9}),CommonUtil.array2ListNode(new int[]{2,3,4,6,8})));
    }

}
