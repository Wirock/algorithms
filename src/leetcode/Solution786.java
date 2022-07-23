package leetcode;

import java.util.PriorityQueue;

/**
 * 786. 第 K 个最小的素数分数
 * 给你一个按递增顺序排序的数组 arr 和一个整数 k 。数组 arr 由 1 和若干 素数  组成，且其中所有整数互不相同。
 *
 * 对于每对满足 0 < i < j < arr.length 的 i 和 j ，可以得到分数 arr[i] / arr[j] 。
 *
 * 那么第 k 个最小的分数是多少呢?  以长度为 2 的整数数组返回你的答案, 这里 answer[0] == arr[i] 且 answer[1] == arr[j] 。
 *
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,3,5], k = 3
 * 输出：[2,5]
 * 解释：已构造好的分数,排序后如下所示:
 * 1/5, 1/3, 2/5, 1/2, 3/5, 2/3
 * 很明显第三个最小的分数是 2/5
 * 示例 2：
 *
 * 输入：arr = [1,7], k = 1
 * 输出：[1,7]
 *
 *
 * 提示：
 *
 * 2 <= arr.length <= 1000
 * 1 <= arr[i] <= 3 * 104
 * arr[0] == 1
 * arr[i] 是一个 素数 ，i > 0
 * arr 中的所有数字 互不相同 ，且按 严格递增 排序
 * 1 <= k <= arr.length * (arr.length - 1) / 2
 * @author chenzw
 * @date 2021/11/29
 */
public class Solution786 {
    //最大堆 + 枚举 o(n^2logn)
    public int[] kthSmallestPrimeFraction1(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((x,y)->x[1]*y[0]-x[0]*y[1]);
        for(int i=0;i<n-1;i++){
            for(int j=n-1;j>i;j-- ){
                int[] num = new int[]{arr[i],arr[j]};
                if(queue.size()<k){
                    queue.offer(num);
                    continue;
                }
                int[] p = queue.peek();
                if(num[0]*p[1]-num[1]*p[0]>=0){
                    break;
                }
                queue.poll();
                queue.offer(num);
            }
        }
        return queue.peek();
    }

    //最小堆+归并 o(n^2logn)
    public int[] kthSmallestPrimeFraction2(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((x,y)->arr[x[0]]*arr[y[1]]-arr[x[1]]*arr[y[0]]);
        for(int i=1;i<n;i++){
            queue.offer(new int[]{0,i});
        }
        while(k-->1){
            int[] poll = queue.poll();
            int i = poll[0]+1;
            if(i<poll[1]){
                queue.offer(new int[]{i,poll[1]});
            }
        }
        int[] p = queue.poll();
        return new int[]{arr[p[0]],arr[p[1]]};
    }


    //二分
    public int[] kthSmallestPrimeFraction3(int[] _arr, int k) {
        //TODO
        return new int[]{};
    }


}
