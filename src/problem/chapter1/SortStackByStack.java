package problem.chapter1;

import java.util.Stack;

/**
 * 1.4用一个栈实现另一个栈的排序,从顶到底按从大到小的顺序排序。只允许申请一个栈和一个新的变量。
 * @author chenzw
 * @date 2021/1/24
 */
public class SortStackByStack {
    public void sortStackByStack(Stack<Integer> stack){
        Stack<Integer> help = new Stack<>();//辅助栈，从顶到底按从小到大排序，这样压回stack就是从顶到底按从大到小的顺序排序
        while(!stack.isEmpty()){
            //把stack的栈顶元素取出，放入help中，放入前help中比该元素小的元素出栈，放回stack。确保在help中该元素以下没有比它小的元素。
            int cur = stack.pop();
            while(!help.isEmpty()&&help.peek()<cur){
                stack.push(help.pop());
            }
            help.push(cur);
        }
        while(!help.isEmpty()){
            stack.push(help.pop());
        }
    }
}
