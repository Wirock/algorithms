package problem.chapter1;

import java.util.Stack;

/**
 * 1.1设计一个有getMin功能的栈
 * 第一种设计方式，只有在当前值是最小值时才入最小栈。空间占用较少，出栈操作较慢
 *时O(1),空O(n)
 * @author chenzw
 * @date 2021/1/23
 */
public class GetMinStack1 {
    private Stack<Integer> stackData;//数据栈
    private Stack<Integer> stackMin;//最小值栈

    public GetMinStack1() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    //压栈，小于或等于当前最小值时入最小栈
    public void push(int newNum) {
        stackData.push(newNum);
        if (stackMin.isEmpty() || newNum <= getMin()) {
            stackMin.push(newNum);
        }
    }

    //入栈时如果当前数时最小值会压入栈，则出栈时出的值时当前的最小值，最小栈也要出一个值。
    public int pop() {
        if (stackData.isEmpty()) {
            throw new RuntimeException("The stack is empty.");
        }
        int value = stackData.pop();
        if (value == getMin()) {
            stackMin.pop();
        }
        return value;
    }

    public int getMin() {
        if (stackMin.isEmpty()) {
            throw new RuntimeException("The stack is empty.");
        }
        return stackMin.peek();
    }
}
