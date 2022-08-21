package leetcode;

import java.util.*;

/**
 * 768. 最多能完成排序的块 II
 * 这个问题和“最多能完成排序的块”相似，但给定数组中的元素可以重复，输入数组最大长度为2000，其中的元素最大为10**8。
 *
 * arr是一个可能包含重复元素的整数数组，我们将这个数组分割成几个“块”，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。
 *
 * 我们最多能将数组分成多少块？
 *
 * 示例 1:
 *
 * 输入: arr = [5,4,3,2,1]
 * 输出: 1
 * 解释:
 * 将数组分成2块或者更多块，都无法得到所需的结果。
 * 例如，分成 [5, 4], [3, 2, 1] 的结果是 [4, 5, 1, 2, 3]，这不是有序的数组。
 * 示例 2:
 *
 * 输入: arr = [2,1,3,4,4]
 * 输出: 4
 * 解释:
 * 我们可以把它分成两块，例如 [2, 1], [3, 4, 4]。
 * 然而，分成 [2, 1], [3], [4], [4] 可以得到最多的块数。
 * 注意:
 *
 * arr的长度在[1, 2000]之间。
 * arr[i]的大小在[0, 10**8]之间。
 * Created by Chenzw on 2022/8/13 2:10
 */
public class Solution768 {

    //只有当前元素及后面部分的最小值大于前面所有元素的最大值，此元素处才可以分组
    //反过来，只有当前元素及前面部分的最大值小于后面所有元素的最小值，此元素处才可以分组
    //1.前缀数组
    public int maxChunksToSorted1(int[] arr) {
        int n = arr.length;
        int[] max = new int[n];
        max[0] = arr[0];
        for(int i=1;i<n;i++){
            max[i] = Math.max(arr[i],max[i-1]);
        }
        int min = Integer.MAX_VALUE;
        int ans = 1;
        for(int i=n-1;i>0;i--){
            min = Math.min(min,arr[i]);
            if(min>=max[i-1]){
                ans ++;
            }
        }
        return ans;
    }

    //2.单调栈，原理和1一样，使用栈来存放最大值或最小值
    //在数据量大，重复元素多的情况下可以减少空间复杂度
    public int maxChunksToSorted2(int[] arr) {
        int n = arr.length;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        for(int i=1;i<n;i++){
            if(arr[i]>arr[stack.peek()]){
                stack.push(i);
            }
        }
        int min = Integer.MAX_VALUE;
        int ans = 1;
        for(int i=n-1;i>0;i--){
            min = Math.min(min,arr[i]);
            if(i==stack.peek()){
                stack.pop();
            }
            if(min>=arr[stack.peek()]){
                ans ++;
            }
        }
        return ans;
    }

    //3.排序+哈希
    //排序后的数组为最终结果，如果一个子数组可作为分组，那么在该数组的坐标范围内对应的排序后的数组元素出现的频次一样
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[] a = new int[n];
        for(int i=0;i<n;i++){
            a[i] = arr[i];
        }
        Arrays.sort(a);
        Map<Integer,Integer> map = new HashMap<>();
        int ans = 0;
        for(int i=0;i<n;i++){
            int c = map.getOrDefault(arr[i], 0) + 1;
            if(c==0){
                map.remove(arr[i]);
            }else{
                map.put(arr[i],c);
            }
            c = map.getOrDefault(a[i], 0) - 1;
            if(c==0){
                map.remove(a[i]);
                if(map.size()==0){
                    ans ++;
                }
            }else{
                map.put(a[i],c);
            }
        }
        return ans;
    }
}
