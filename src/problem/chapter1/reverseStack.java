package problem.chapter1;

import java.util.Stack;

/**
 * 1.3 仅使用递归和栈操作逆序一个栈
 * @author chenzw
 * @date 2021/1/23
 */
public class reverseStack {

    public void reverse(Stack<Integer> stack){
        if(stack.size()<=1)return;//如果栈只有一个元素，逆序就是本身
        //取出最后一个元素，把剩下的逆序后再把这个元素放到顶部，则实现整体逆序
        int last = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(last);
    }

    private int getAndRemoveLastElement(Stack<Integer> stack){
        if(stack.size()==1){
            return stack.pop();
        }
        Integer elem = stack.pop();
        int last = getAndRemoveLastElement(stack);
        stack.push(elem);
        return last;
    }
}
