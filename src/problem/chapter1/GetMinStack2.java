package problem.chapter1;

import java.util.Stack;

/**
 * 1.1设计一个有getMin功能的栈
 * 第二种设计方式，数据栈每入一个值，最小值栈就对应入一个最小值。空间占用较多，出栈操作较快
 * 时O(1),空O(n)
 * @author chenzw
 * @date 2021/1/23
 */
public class GetMinStack2 {
    private Stack<Integer> stackData;//数据栈
    private Stack<Integer> stackMin;//最小值栈

    public GetMinStack2() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int newNum) {
        stackData.push(newNum);
        if (stackMin.isEmpty()) {
            stackMin.push(newNum);
        }else{
            stackMin.push(newNum < getMin()?newNum:getMin());
        }
    }

    public int pop() {
        if (stackData.isEmpty()) {
            throw new RuntimeException("The stack is empty.");
        }
        stackMin.pop();
        return stackData.pop();
    }

    public int getMin() {
        if (stackMin.isEmpty()) {
            throw new RuntimeException("The stack is empty.");
        }
        return stackMin.peek();
    }
}
