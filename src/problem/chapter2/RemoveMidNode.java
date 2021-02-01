package problem.chapter2;

/**
 * 2.3删除链表的中间节点
 * @author chenzw
 * @date 2021/2/1
 */
public class RemoveMidNode {
    public Node removeMidNode(Node head){
        if(head == null||head.next == null){
            return head;
        }
        if(head.next.next == null){
            return head.next;
        }
        //cur以pre的两倍速往下走，cur到底时pre为中点
        //而需要的时时中点的前继，故第一步pre不走
        Node pre = head;
        Node cur = head.next.next;
        while (cur.next != null && cur.next.next != null){
            pre = pre.next;
            cur = cur.next.next;
        }
        pre.next = pre.next.next;
        return head;
    }
}
