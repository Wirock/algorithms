package common;

import datastructure.list.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenzw
 * @date 2021/3/18
 */
public class CommonUtil {
    public static void printListNode(ListNode head){
        List<Integer> list = new ArrayList<>();
        while(head!=null){
            list.add(head.val);
            head = head.next;
        }
        System.out.println(Arrays.toString(list.toArray()));
    }

    public static ListNode array2ListNode(int[] array){
        if(array==null||array.length==0){
            return null;
        }
        ListNode head = new ListNode(array[0]);
        ListNode cur = head;
        for(int i=1;i<array.length;i++){
            cur.next = new ListNode(array[i]);
            cur = cur.next;
        }
        return head;
    }

    public void printArray(int[][] array){
        for(int[] a:array){
            System.out.println(Arrays.toString(a));
        }
    }
}
