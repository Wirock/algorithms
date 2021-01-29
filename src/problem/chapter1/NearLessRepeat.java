package problem.chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 1.7给定一个可能含有重复值的数组，遍历数组的每一个元素。获取每个元素左右两边距离最近，且值比该它小的元素,没有的用-1表示
 *
 * 有重复值的与无重复值不通在，有重复值的情况下，当数组的所有元素入完栈后，剩下的还在栈中的元素，由于栈的特点，该元素弹出后，只能看到栈顶的元素，
 * 如果这个栈顶的元素与当前弹出元素相等，不能确定栈中的元素是全部与弹出元素相等，还是有小于弹出元素的值。
 * 因此，把单调栈中的元素由整型替换成队列，相邻的相同元素放在同一个队列里，就可以保证栈的每一帧放的是不同的数值对应的下标。
 *
 * @date 2021/1/28
 */
public class NearLessRepeat {

    public int[][] getNearLessNoRepeat(int[] arr){
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();//存下标，保证下标对应数组的元素单调，下小于或等于上
        for(int i=0;i<arr.length;i++){
            while(!stack.isEmpty()&&arr[stack.peek().get(0)]>arr[i]){
                List<Integer> popList = stack.pop();
                for(Integer popIndex:popList){
                    int leftLessIndex = stack.isEmpty()?-1:stack.peek().get(stack.peek().size()-1);
                    res[popIndex][0] = leftLessIndex;
                    res[popIndex][1] = i;
                }
            }
            if(stack.isEmpty()||arr[stack.peek().get(0)]<arr[i]){
                List<Integer> pushList = new ArrayList<>();
                pushList.add(i);
                stack.push(pushList);
            }else{
                stack.peek().add(i);
            }
        }

        while(!stack.isEmpty()){
            List<Integer> popList = stack.pop();
            for(Integer popIndex:popList){
                int leftLessIndex = stack.isEmpty()?-1:stack.peek().get(stack.peek().size()-1);
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = -1;
            }
        }
        return res;
    }
}
