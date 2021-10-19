package leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * 求数组中最小的k个元素
 * @author chenzw
 * @date 2021/9/4
 */
public class Solution17_14 {

    //最大堆
    /*public int[] smallestK(int[] arr, int k) {
        if(k==0)return new int[0];
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y)->y-x);
        for(int i:arr){
            if(queue.size()==k) {
                if(i>=queue.peek())continue;
                queue.poll();
            }
            queue.offer(i);
        }
        int[] ans = new int[k];
        for(int i=k-1;i>=0;i--){
            ans[i] = queue.poll();
        }
        return ans;
    }*/

    //优化，由于不要求排序，可以使用快速排序的二分法
    private static final Random random = new Random(System.currentTimeMillis());

    public int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }

        int len = arr.length;
        int left = 0;
        int right = len - 1;
        // 找下标是 k - 1 的那个数，由于在循环过程中 left <= right 一定成立，因此写 while (true)  就可以
        while (true) {
            int index = partition(arr, left, right);
            if (index == k - 1) {
                break;
            } else if (index < k - 1) {
                left = index + 1;
            } else {
                right = index - 1;
            }
        }

        int[] res = new int[k];
        System.arraycopy(arr, 0, res, 0, k);
        return res;
    }

    private int partition(int[] arr, int left, int right) {
        // 随机选择 arr[left..right] 中的元素作为 pivot，为什么传 right - left + 1 请见代码后的「说明」
        int randomIndex = left + random.nextInt(right - left + 1);
        swap(arr, left, randomIndex);

        // 保持循环不变的性质：lt 是 less than 的缩写
        // arr[left + 1..lt] < pivot
        // arr(lt..i) >= pivot
        int lt = left;
        int pivot = arr[left];
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] < pivot) {
                lt++;
                swap(arr, i, lt);
            }
        }

        // 这一步比较容易忘掉，必须要交换以后返回 lt
        swap(arr, left, lt);
        return lt;
    }

    private void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution17_14().smallestK(new int[]{1,5,2,4,3,7,6},4)));
        System.out.println(Arrays.toString(new Solution17_14().smallestK(new int[]{1,5,2,4,3,7,6},5)));
        System.out.println(Arrays.toString(new Solution17_14().smallestK(new int[]{1,5,2,4,3,7,6},3)));
    }
}
