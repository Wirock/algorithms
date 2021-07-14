package leetcode;

import common.CommonUtil;
import datastructure.list.ListNode;

/**
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 进阶：
 *
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 示例 2：
 *
 *
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 * @author chenzw
 * @date 2021/7/14
 */
public class Solution148 {
    //归并排序
    public ListNode sortList(ListNode head) {
        ListNode cur = head;
        int count = 0;
        while(cur!=null){
            count++;
            cur=cur.next;
        }
        if(count<2)return head;
        ListNode list1 = head;
        ListNode list2 = head;
        count=(count-1)>>1;
        while(count-->0)list2=list2.next;
        cur = list2.next;
        list2.next=null;
        list2=cur;
        return mergeSort(list1,list2);
    }

    private ListNode mergeSort(ListNode list1,ListNode list2){
        if(list1==null)return list2;
        if(list2==null)return list1;
        list1 = sortList(list1);
        list2 = sortList(list2);
        ListNode head;
        if(list1.val<=list2.val){
            head = list1;
            list1 =list1.next;
        }else{
            head = list2;
            list2 = list2.next;
        }
        ListNode tail = head;
        while(list1!=null&&list2!=null){
            if(list1.val<=list2.val){
                tail.next = list1;
                list1 = list1.next;
            }else{
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        if(list1==null)tail.next=list2;
        else tail.next=list1;
        return head;
    }

    public static void main(String[] args) {
        CommonUtil.printListNode(new Solution148().sortList(CommonUtil.array2ListNode(new int[]{4,2,1,3})));
    }
}
