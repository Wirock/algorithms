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
        for(int i=0;i<arr.length;i++){
            while(!stack.isEmpty()&&arr[stack.peek()]>arr[i]){
                int popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty()?-1:stack.peek();
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = i;
            }
        }
        while(!stack.isEmpty()){
            int popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty()?-1:stack.peek();
            res[popIndex][0] = leftLessIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }
}
