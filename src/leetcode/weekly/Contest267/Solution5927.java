package leetcode.weekly.Contest267;

import datastructure.list.ListNode;

/**
 * 5927. 反转偶数长度组的节点 显示英文描述
 * 通过的用户数1666
 * 尝试过的用户数2075
 * 用户总通过次数1686
 * 用户总提交次数4917
 * 题目难度Medium
 * 给你一个链表的头节点 head 。
 *
 * 链表中的节点 按顺序 划分成若干 非空 组，这些非空组的长度构成一个自然数序列（1, 2, 3, 4, ...）。一个组的 长度 就是组中分配到的节点数目。换句话说：
 *
 * 节点 1 分配给第一组
 * 节点 2 和 3 分配给第二组
 * 节点 4、5 和 6 分配给第三组，以此类推
 * 注意，最后一组的长度可能小于或者等于 1 + 倒数第二组的长度 。
 *
 * 反转 每个 偶数 长度组中的节点，并返回修改后链表的头节点 head 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [5,2,6,3,9,1,7,3,8,4]
 * 输出：[5,6,2,3,9,1,4,8,3,7]
 * 解释：
 * - 第一组长度为 1 ，奇数，没有发生反转。
 * - 第二组长度为 2 ，偶数，节点反转。
 * - 第三组长度为 3 ，奇数，没有发生反转。
 * - 最后一组长度为 4 ，偶数，节点反转。
 * 示例 2：
 *
 *
 *
 * 输入：head = [1,1,0,6]
 * 输出：[1,0,1,6]
 * 解释：
 * - 第一组长度为 1 ，没有发生反转。
 * - 第二组长度为 2 ，节点反转。
 * - 最后一组长度为 1 ，没有发生反转。
 * 示例 3：
 *
 *
 *
 * 输入：head = [2,1]
 * 输出：[2,1]
 * 解释：
 * - 第一组长度为 1 ，没有发生反转。
 * - 最后一组长度为 1 ，没有发生反转。
 * 示例 4：
 *
 * 输入：head = [8]
 * 输出：[8]
 * 解释：只有一个长度为 1 的组，没有发生反转。
 *
 *
 * 提示：
 *
 * 链表中节点数目范围是 [1, 105]
 * 0 <= Node.val <= 105
 * @author chenzw
 * @date 2021/11/14
 */
public class Solution5927 {
    //分组反转链表
    public ListNode reverseEvenLengthGroups(ListNode head) {
        int group = 1;
        ListNode hair = new ListNode(0);
        hair.next=head;
        ListNode pre = hair;
        ListNode tail = head;
        ListNode next = head.next;
        int count=0;
        while(true){
            if(count>0&&count%2==0) {
                tail.next=null;
                ListNode reverse = reverse(pre.next);
                pre.next=reverse;
                while(pre.next!=null){
                    pre = pre.next;
                }
                pre.next=next;
            }else{
                for(int i=0;i<group;i++){
                    if(pre.next!=null)pre = pre.next;
                }
            }
            if(pre.next==null)break;
            group++;
            tail = pre;
            count=0;
            for(int i=0;i<group;i++){
                if(tail.next==null)break;
                tail = tail.next;
                count++;
            }
            next = tail.next;
        }
        return head;
    }

    private ListNode reverse(ListNode head){
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
}
