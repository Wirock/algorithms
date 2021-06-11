package leetcode;

import common.CommonUtil;
import datastructure.list.ListNode;

/**
 * 86. 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 *
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * 示例 2：
 *
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 * @author chenzw
 * @date 2021/6/11
 */
public class Solution86 {
    public static ListNode partition(ListNode head, int x) {
        ListNode leftHead = new ListNode();
        ListNode leftTail = leftHead;
        ListNode rightHead = new ListNode();
        ListNode rightTail = rightHead;
        while(head!=null){
            if(head.val<x) {
                leftTail.next = head;
                leftTail = leftTail.next;
            }else{
                rightTail.next = head;
                rightTail = rightTail.next;
            }
            head=head.next;
        }
        leftTail.next=rightHead.next;
        rightTail.next=null;
        return leftHead.next;
    }

    public static void main(String[] args) {
        CommonUtil.printListNode(partition(CommonUtil.array2ListNode(new int[]{1,4,3,2,5,2}),3));
    }
}
