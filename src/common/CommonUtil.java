package common;

import datastructure.list.ListNode;
import datastructure.tree.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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

    public static void printArray(int[][] array){
        for(int[] a:array){
            System.out.println(Arrays.toString(a));
        }
    }
    public static void printArray(double[][] array){
        for(double[] a:array){
            System.out.println(Arrays.toString(a));
        }
    }

    public static TreeNode arrays2BSTByLevel(Integer[] array){
        LinkedList<TreeNode> queue = new LinkedList<>();
        if(array==null||array.length==0)return null;
        int i=0;
        TreeNode tree = null;
        while(i<array.length){
            if(queue.isEmpty()){
                tree = new TreeNode(array[i++]);
                queue.addLast(tree);
            }else{
                TreeNode root=queue.pollFirst();

                if(array[i]==null){
                    root.left=null;
                }else{
                    root.left=new TreeNode(array[i]);
                    queue.addLast(root.left);
                }
                i++;
                if(i<array.length){
                    if(array[i]==null){
                        root.right=null;
                    }else{
                        root.right=new TreeNode(array[i]);
                        queue.addLast(root.right);
                    }
                    i++;
                }
            }
        }
        return tree;
    }

    public static int height(TreeNode root){
        if(root==null)return 0;
        return 1+Math.max(height(root.left),height(root.right));
    }

    public static void levelOrder(TreeNode root) {
        if(root==null) return;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        System.out.print(root.val+" ");
        while(!queue.isEmpty()){
            root = queue.pollFirst();
            if(root.left!=null){
                queue.addLast(root.left);
                System.out.print(root.left.val+" ");
            }
            if(root.right!=null){
                queue.addLast(root.right);
                System.out.print(root.right.val+" ");
            }
        }
        System.out.println();
    }

    public static void printTree(TreeNode root) {
        int height = height(root);
        int x = height;
        int y = 3*new Double(Math.pow(2, height - 1)).intValue();
        Object[][] screen = new Object[x][y];
        fillScreen(screen, 0, 0, y - 1, root);
        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[i].length; j++) {
                System.out.print(screen[i][j] == null ? "  " : screen[i][j]);
            }
            System.out.println();
        }
    }

    private static void fillScreen(Object[][] screen, int row, int columnBegin, int columnEnd, TreeNode node) {
        int mid = (columnBegin + columnEnd) / 2;
        screen[row][mid] = node.val;
        if (node.left != null) {
            fillScreen(screen, row + 1, columnBegin, mid, node.left);
        }
        if (node.right != null) {
            fillScreen(screen, row + 1, mid, columnEnd, node.right);
        }
    }


    public static void printTreeInOrder(TreeNode root){
        inOrder(root);
        System.out.println();
    }

    private static void inOrder(TreeNode root){
        if(root==null)return;
        if(root.left!=null)inOrder(root.left);
        System.out.print(root.val+" ");
        if(root.right!=null)inOrder(root.right);
    }
}
