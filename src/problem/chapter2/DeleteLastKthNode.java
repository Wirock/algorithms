package problem.chapter2;

/**
 * 2.2 在单链表和双链表中删除倒数第K个节点
 * @author chenzw
 * @date 2021/1/31
 */
public class DeleteLastKthNode {

    public Node removeLastKthNode(Node head,int k){
        if(k<1){
            return head;
        }
        Node cur = head;
        while(head!=null){
            cur = head.next;
            k--;
        }
        if(k==0){
            return head.next;
        }
        if(k<0){
            cur = head;
            while(++k<0){//cur要指向被删节点的前一个节点，++要在前
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
    }

    public DoubleNode removeLastKthNode(DoubleNode head,int k){
        if(k<1){
            return head;
        }
        DoubleNode cur = head;
        while(head!=null){
            cur = head.next;
            k--;
        }
        if(k==0){
            return head.next;
        }
        if(k<0){
            cur = head;
            while(++k<0){//cur要指向被删节点的前一个节点，++要在前
                cur = cur.next;
            }
            cur.next = cur.next.next;
            if(cur.next!=null){
                cur.next.last = cur;
            }
        }
        return head;
    }
}
