package problem.chapter2;

/**
 * 2.1打印两个有序链表的公共部分
 * @author chenzw
 * @date 2021/1/31
 */
public class PrintCommonPart {

    public void printCommonPart(Node head1,Node head2){
        while(head1!=null&&head2!=null){
            if(head1.value<head2.value){
                head1 = head1.next;
            }else if(head1.value>head2.value){
                head2 = head2.next;
            }else{
                System.out.print(head1.value+" ");
                head1 = head1.next;
                head2 = head2.next;
            }
        }
        System.out.println();
    }
}
