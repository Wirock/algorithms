package problem.chapter2;

/**
 * 2.3
 * 1.删除链表的中间节点
 * 2.给定表头head和整数a、b,删除位于链表a/b处的节点
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

    public Node removeRatioNode(Node head,int a,int b){
        if(a<1||a>b){
            return head;
        }
        int n = 0;
        Node cur = head;
        if(cur != null){
            cur = cur.next;
            n++;
        }
        n = (int)Math.ceil(n*((double)a/(double)b));

        if(n==1){
            return head.next;
        }
        Node pre = head;
        while (--n>1){
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return head;
    }
}
