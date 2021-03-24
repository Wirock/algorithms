package leetcode;

import common.CommonUtil;
import datastructure.list.ListNode;

/**
 * 82. 删除排序链表中的重复元素 II
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 *
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 * @author chenzw
 * @date 2021/3/25
 */
public class Solution82 {
    public static ListNode deleteDuplicates(ListNode head) {
        if(head==null||head.next==null)return head;
        ListNode pre = null;
        ListNode last = null;
        ListNode cur = head;
        boolean duplicated = false;
        while(cur.next!=null){
            last = cur;
            cur = cur.next;
            if(last.val==cur.val){
                duplicated = true;
                continue;
            }
            if(duplicated) {
                if(pre!=null) {
                    pre.next = cur;
                    last = pre;
                }else{
                    head = cur;
                }
                duplicated = false;
            }else{
                pre = last;
            }
        }
        if(last.val==cur.val){
            if(pre!=null) pre.next=null;
            else return null;
        }
        return head;
    }

    public static void main(String[] args) {
        CommonUtil.printListNode(deleteDuplicates(CommonUtil.array2ListNode(new int[]{1,2,3,3,4,4,5})));
        CommonUtil.printListNode(deleteDuplicates(CommonUtil.array2ListNode(new int[]{1,1,1,2,3})));
        CommonUtil.printListNode(deleteDuplicates(CommonUtil.array2ListNode(new int[]{1,1,1,2,2,2,3,3,3})));
        CommonUtil.printListNode(deleteDuplicates(CommonUtil.array2ListNode(new int[]{1,2,3})));
    }
}
