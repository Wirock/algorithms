package problem.chapter2;

import datastructure.list.ListNode;

/**
 * 交换链表两个节点位置
 * @author chenzw
 * @date 2021/3/18
 */
public class ExchangeNode {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head==null){
            return head;
        }
        ListNode pre1 = null;
        ListNode pre2 = null;
        int count=0;
        ListNode newHead = new ListNode();
        newHead.next=head;
        ListNode cur = newHead;
        while(cur.next!=null&&count<=Math.max(left,right)){
            count++;
            if(count==left){
                pre1 = cur;
            }
            if(count==right){
                pre2 = cur;
            }
            cur=cur.next;
        }
        if(cur!=null){
            ListNode m = pre1.next;
            ListNode n = pre2.next;
            pre1.next = pre2.next;
            pre2.next = m;
            cur = m.next;
            m.next = n.next;
            n.next = cur;
        }
        return newHead.next;
    }
}
