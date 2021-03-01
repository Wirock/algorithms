package problem.chapter2;

/**
 * 2.4 反转链表只要按顺序吧原链表的头节点移出，作为新链表的头节点。则新链表即为反转后的链表
 * 1.反转单链表
 * 2.反转双链表
 * 要求：时间复杂度O(n),额外空间复杂度O(1)
 * @author chenzw
 * @date 2021/2/2
 */
public class ReverseList {

    public Node reverse(Node head){
        Node pre = null;
        Node cur = head;
        while(head!=null){
            head = head.next;
            cur.next = pre;
            pre = cur;
            cur = head;
        }
        return pre;
    }

    public DoubleNode reverse(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode cur = head;
        while(head!=null){
            head = head.next;
            cur.next = pre;
            pre.last = cur;
            pre = cur;
            cur = head;
        }
        return pre;
    }
}
