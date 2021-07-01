package leetcode;

import datastructure.list.ListNode;

/**
 * 2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 *
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 *
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 *
 * 提示：
 *
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 * @author chenzw
 * @date 2021/3/3
 */
public class Solution2 {
    //归并
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode pre = null;
        int carry = 0;
        while (l1!=null||l2!=null){
            int sum = (l1==null?0:l1.val) + (l2==null?0:l2.val) + carry;
            carry = sum/10;
            if(head==null){
                head = new ListNode(sum%10);
                pre = head;
            }else{
                pre.next = new ListNode(sum%10);
                pre = pre.next;
            }
            if(l1!=null){
                l1=l1.next;
            }
            if(l2!=null){
                l2=l2.next;
            }
        }
        if(carry>0){
            pre.next=new ListNode(carry);
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = arrToListNode(new int[]{9,9,9,9,9,9,9} );
        ListNode l2 = arrToListNode(new int[]{9,9,9,9} );
        printListNodeInReversedOrder(l1);
        printListNodeInReversedOrder(l2);
        printListNodeInReversedOrder(addTwoNumbers(l1,l2));
    }

    //提供测试数据
    private static ListNode arrToListNode(int[] arr){
        ListNode head = null;
        ListNode pre = null;
        for(int i=0;i<arr.length;i++){
            if(head == null){
                head = new ListNode(arr[i]);
                pre = head;
            }else{
                pre.next = new ListNode(arr[i]);
                pre = pre.next;
            }
        }
        return head;
    }
    //打印
    private static void printListNodeInReversedOrder(ListNode head){
        while(head!=null){
            System.out.print(head.val);
            System.out.print(" ");
            head = head.next;
        }
        System.out.println();
    }
}

