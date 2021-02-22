package problem.chapter1;

import java.util.Stack;

/**
 * 1.7给定一个不含有重复值的数组，遍历数组的每一个元素。获取每个元素左右两边距离最近，且值比该它小的元素,没有的用-1表示
 *
 * @date 2021/1/28
 */
public class NearLessNoRepeat {

    public int[][] getNearLessNoRepeat(int[] arr){
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();//存下标，保证下标对应数组的元素单调，下比上小
        //每个元素依次入栈，由于是下比上小的单调栈，栈中每个元素下的第一个元素，就是左边最近的比该元素小的元素的坐标
        //若栈顶对应的元素比入栈数大，则可确定栈顶右边最近的比栈顶对应元素小的元素的就是入栈元素
        for(int i=0;i<arr.length;i++){
            while(!stack.isEmpty()&&arr[stack.peek()]>arr[i]){
                int popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty()?-1:stack.peek();
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        //栈中剩下元素右边没有比它小的元素
        while(!stack.isEmpty()){
            int popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty()?-1:stack.peek();
            res[popIndex][0] = leftLessIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }
}
