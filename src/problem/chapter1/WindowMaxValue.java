package problem.chapter1;

import java.util.LinkedList;

/**
 * 1.6生成窗口最大值数组
 *
 * 如果数组长度为n，窗口大小为w,窗口从左往右，每次滑动一格。则一共产生n-w+1个窗口的最大值
 * 实现一个函数，输入是 整型数组arr,窗口大小w
 * 输出一个长度我n-w+1的数组res,res[i]表示窗口头部为arr[i]时的最大值
 * @author chenzw
 * @date 2021/1/28
 */
public class WindowMaxValue {

    public int[] getMaxWindowValue(int[] arr,int w){
        if(arr == null|| w<1 ||arr.length<w){
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        int index = 0;
        int[] res = new int[arr.length-w+1];
        for(int i=0;i<arr.length;i++){
            while(!qmax.isEmpty()&&arr[qmax.peekLast()]<arr[i]){
                qmax.pollLast();
            }
            qmax.addLast(i);
            if(qmax.peekFirst() == i-w){
                qmax.pollFirst();
            }
            if(i>=w-1){
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }
}
