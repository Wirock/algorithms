package leetcode;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 * @author chenzw
 * @date 2021/3/17
 */
public class Solution19 {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        ListNode result = null;
        int count=1;
        while(cur.next!=null){
            if(++count>n){
                if(result==null)result = head;
                else result = result.next;
            }
            cur = cur.next;
        }
        if(count==n){
            result = head.next;
        }else if(result!=null){
            result.next = result.next.next;
            result = head;
        }
        return result;
    }
}
