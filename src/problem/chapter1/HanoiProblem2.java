package problem.chapter1;

import java.util.Stack;

/**
 * 1.5汉诺塔问题,左右不能直接移动给对方，必须经过中间。打印过程，返回步数。
 *栈实现
 * @author chenzw
 * @date 2021/1/25
 */
public class HanoiProblem2 {

    public enum Action {
        No,LToM,MToL,MToR,RToM//无，左->中，中->左，中->右，右>中
    }

    public int process(int num ,String left,String mid,String right,String from,String to){
        Stack<Integer> ls = new Stack<>();
        Stack<Integer> ms = new Stack<>();
        Stack<Integer> rs = new Stack<>();
        ls.push(Integer.MAX_VALUE);
        ms.push(Integer.MAX_VALUE);
        rs.push(Integer.MAX_VALUE);
        for(int i=num;i>0;i--){
            ls.push(i);
        }
        Action[] record = {Action.No};
        int step = 0;
        while(rs.size() != num+1){
            step += fStackTotStack(record,Action.MToL,Action.LToM,ls,ms,left,mid);
            step += fStackTotStack(record,Action.LToM,Action.MToL,ms,ls,mid,left);
            step += fStackTotStack(record,Action.RToM,Action.MToR,ms,rs,mid,right);
            step += fStackTotStack(record,Action.MToR,Action.RToM,rs,ms,right,mid);
        }
        return step;
    }
    //不是互斥动作，且被移动的元素比目标位置顶部元素小则可执行。如果起点再左或右，则之后的步骤都是唯一的
    //TODO起点为mid的情况
    public int fStackTotStack(Action[] record,Action preNoAct,Action nowAct,Stack<Integer> fStack,Stack<Integer> tStack,String from ,String to){
        if(record[0]!=preNoAct&&fStack.peek()<tStack.peek()){
            tStack.push(fStack.pop());
            System.out.println("Move "+tStack.peek()+" from "+from+" to "+to);
            record[0] = nowAct;
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new HanoiProblem2().process(3,"A","B","C","A","C"));
    }
}
