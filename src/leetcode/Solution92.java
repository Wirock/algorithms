package leetcode;

import common.CommonUtil;
import datastructure.list.ListNode;

/**
 *92. 反转链表 II
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * 示例 2：
 *
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 *
 *
 * 提示：
 *
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 * @author chenzw
 * @date 2021/6/11
 */
public class Solution92 {

    /*public static ListNode reverseBetween(ListNode head, int left, int right) {
        if(left>=right){
            return head;
        }
        ListNode sub = head;
        ListNode pre = null;
        ListNode next = null;
        while(sub!=null&&left>1){
            pre = sub;
            sub = sub.next;
            left--;
            right--;
        }
        if(sub==null)return head;
        next = sub;
        while(next!=null&&right>1){
            next = next.next;
            right--;
        }
        if(next!=null){
            ListNode subTail =next;
            next = next.next;
            subTail.next=null;
        }
        ListNode[] border = reverse(sub);
        if(pre==null)head=border[0];
        else pre.next=border[0];
        border[1].next=next;
        return head;
    }

    private static ListNode[] reverse(ListNode head){
        if(head==null)return new ListNode[]{null,null};
        ListNode cur = head;
        ListNode pre = null;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return new ListNode[]{pre,head};
    }*/

    /*public static ListNode reverseBetween(ListNode head, int left, int right) {
        if(head==null){
            return head;
        }
        ListNode leftPre = null;
        ListNode pre = null;
        ListNode post = null;
        ListNode cur = head;
        int count=1;
        while(cur!=null&&count<=right){
            if(count==left-1){
                leftPre = cur;
            }

            if(count>left){
                post = cur.next;
                cur.next = pre;
                pre = cur;
                cur = post;
            }else{
                pre = cur;
                cur=cur.next;
            }
            count++;
        }
        if(count>right){
            if(leftPre!=null){
                leftPre.next.next=cur;
                leftPre.next = pre;
            }else{
                head.next=cur;
                head = pre;
            }
        }
        return head;
    }*/

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if(head==null)return null;
        if(left>=right){
            return head;
        }
        ListNode h = new ListNode(0);
        h.next = head;
        ListNode pre=h;
        right-=left;
        while(pre!=null&&left-->1){
            pre = pre.next;
        }

        if(pre==null)return head;
        ListNode cur = pre.next;//cur是第left个元素
        ListNode next = cur.next;
        while(next!=null&&right-->0){//遍历第left+1到第right个节点，依次把每个节点从原位置删除，插入到left处
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
            next = cur.next;
        }
        return h.next;
    }

    public static void main(String[] args) {
        CommonUtil.printListNode(reverseBetween(CommonUtil.array2ListNode(new int[]{1,2,3,4,5,6}),3,4));
        CommonUtil.printListNode(reverseBetween(CommonUtil.array2ListNode(new int[]{1,2,3,4,5,6}),3,3));
        CommonUtil.printListNode(reverseBetween(CommonUtil.array2ListNode(new int[]{1,2,3,4,5,6}),1,6));
        CommonUtil.printListNode(reverseBetween(CommonUtil.array2ListNode(new int[]{}),3,4));
    }
}
