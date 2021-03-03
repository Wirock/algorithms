package problem.chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * 1.9给定数组arr和整数num，求出arr的所有子数组中满足组数组的最大值与最小值的和小于或等于num的子数组数量
 * 要求算法的时间复杂度为o(N)
 * @author chenzw
 * @date 2021/3/2
 */
public class GetSumLessThanOrEuqualsNum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] a = br.readLine().split(" ");
        int n = Integer.parseInt(a[0]);
        int num = Integer.parseInt(a[1]);
        String[] b = br.readLine().split(" ");
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(b[i]);
        }
        int sum = getNum(arr,num);
        System.out.println(sum);
    }
    public static int getNum(int[] arr,int num){
        if(arr==null||arr.length==0){
            return 0;
        }
        LinkedList<Integer> maxQueue = new LinkedList<>();
        LinkedList<Integer> minQueue = new LinkedList<>();
        int i=0;
        int j=0;
        int res=0;
        while(i<arr.length){
            //两个队列的对头分别是子窗口i到j的最大值和最小值的坐标
            while(j<arr.length){
                if(maxQueue.isEmpty()||maxQueue.peekLast()!=j){//若break了，会出现maxQueue.peekLast()==j，不需要重复入队，提高效率
                    while (!maxQueue.isEmpty() && arr[j] >= arr[maxQueue.peekLast()]) {
                        maxQueue.pollLast();
                    }
                    maxQueue.addLast(j);
                    while (!minQueue.isEmpty() && arr[j] <= arr[minQueue.peekLast()]) {
                        minQueue.pollLast();
                    }
                    minQueue.addLast(j);
                }
                if(arr[maxQueue.peekFirst()]-arr[minQueue.peekFirst()]>num){//只要当前数组的最大最小值相差大于num,在这个数组基础上扩展的数组的最大最小值的和就一定大于num
                    break;
                }
                j++;
            }
            //经过j的循环,若break,maxQueue的队头是i,j间最大值的坐标，minQueue的队头是i,j间最小值的坐标
            //若未break,maxQueue的队头是i,j-1间最大值的坐标，minQueue的队头是i,j-1间最小值的坐标
            //两种情况，符合条件的以arr[i]开头的子数组是arr[i,j-1]和arr[i,j-1]以arr[i]开头的所有子数组
            res += j-i;//加上i到j-1之间以arr[i]开头的符合条件的子数组的数量
            if(maxQueue.peekFirst()==i){
                maxQueue.pollFirst();
            }
            if(minQueue.peekFirst()==i){
                minQueue.pollFirst();
            }
            i++;
        }
        return res;
    }
}
