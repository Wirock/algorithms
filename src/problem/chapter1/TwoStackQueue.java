package problem.chapter1;

import java.util.Stack;

/**
 * 1.2 使用两个栈实现一个队列
 *
 * 实现方式，两个栈，一个用于入对，一个用于出队。入队栈栈顶是队尾，出队栈栈顶是队头。把数据从一个栈弹出压入另一个栈，栈顶栈底顺序会改变。
 * 只要每次入队前把数据从出队栈弹出压入入队栈，每次出队前把数据从入队栈弹出压入出队栈即可。
 * 进一步优化，入队前不需要把数据从出队栈弹出压入入队栈，出队时，如果出队栈为空，把入队栈整体压入出队栈。
 * 只要保证两个条件，数据顺序就不会异常：1.出队栈不为空时不能把入队栈压入出队栈；2.入队栈压入出队栈必须整体压入，不能残留
 * @author chenzw
 * @date 2021/1/23
 */
public class TwoStackQueue {
    private Stack<Integer> stackAdd;//入队栈
    private Stack<Integer> stackPoll;//出队栈

    public TwoStackQueue() {
        this.stackAdd = new Stack<>();
        this.stackPoll = new Stack<>();
    }


    public void add(int newNum){
        /*while(!stackPoll.isEmpty()){
            stackAdd.push(stackPoll.pop());
        }*/
        stackAdd.push(newNum);
    }

    public int poll(){
        /*while(!stackAdd.isEmpty()){
            stackPoll.push(stackAdd.pop());
        }*/
        if(stackPoll.isEmpty()){
            while(!stackAdd.isEmpty()){
                stackPoll.push(stackAdd.pop());
            }
        }
        if(stackPoll.isEmpty()){
            throw new RuntimeException("Queue is empty.");
        }
        return stackPoll.pop();
    }

    public int peek(){
        /*while(!stackAdd.isEmpty()){
            stackPoll.push(stackAdd.pop());
        }*/
        if(stackPoll.isEmpty()){
            while(!stackAdd.isEmpty()){
                stackPoll.push(stackAdd.pop());
            }
        }
        if(stackPoll.isEmpty()){
            throw new RuntimeException("Queue is empty.");
        }
        return stackPoll.peek();
    }
}
