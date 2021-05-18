package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1442. 形成两个异或相等数组的三元组数目
 * 给你一个整数数组 arr 。
 *
 * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 *
 * a 和 b 定义如下：
 *
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * 注意：^ 表示 按位异或 操作。
 *
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [2,3,1,6,7]
 * 输出：4
 * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
 * 示例 2：
 *
 * 输入：arr = [1,1,1,1,1]
 * 输出：10
 * 示例 3：
 *
 * 输入：arr = [2,3]
 * 输出：0
 * 示例 4：
 *
 * 输入：arr = [1,3,5,7,9]
 * 输出：3
 * 示例 5：
 *
 * 输入：arr = [7,11,12,9,5,2,7,17,22]
 * 输出：8
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 300
 * 1 <= arr[i] <= 10^8
 * @author chenzw
 * @date 2021/5/18
 */
public class Solution1442 {
    //暴力
    /*public static int countTriplets(int[] arr) {
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for(int i=1;i<arr.length;i++){
            sum[i] = sum[i-1]^arr[i];
        }
        int count=0;
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                for(int k=j;k<arr.length;k++){
                    if(((i>0?sum[i-1]:0)^sum[j-1])==(sum[j-1]^sum[k])){
                        count ++;
                    }
                }
            }
        }
        return count;
    }*/

    //改进，只要a^b=sum[k]^sum[i-1]=0,就有a=b,此j可以是[i+1,k]之间
    /*public static int countTriplets(int[] arr) {
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for(int i=1;i<arr.length;i++){
            sum[i] = sum[i-1]^arr[i];
        }
        int count=0;
        for(int i=0;i<arr.length;i++){
            for(int k=i+1;k<arr.length;k++){
                if((i>0?sum[i-1]:0)==sum[k]){
                    count += k-i;
                }
            }
        }
        return count;
    }*/
    //再改进，借助两个哈希表缩减为一层循环
    public static int countTriplets(int[] arr) {
        Map<Integer,Integer> count = new HashMap<>();
        Map<Integer,Integer> total = new HashMap<>();

        int sum = 0;
        int ans = 0;
        count.put(0,1);
        total.put(0,0);
        for(int i=0;i<arr.length;i++){
            sum ^= arr[i];
            if(count.containsKey(sum)){
                ans += i*count.get(sum)-total.get(sum);
            }
            count.put(sum,count.getOrDefault(sum,0)+1);
            total.put(sum,total.getOrDefault(sum,0)+i+1);//sum[k]==sum[i],j的范围是[i+2,k],j的数目是k减去i+1
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(countTriplets(new int[]{2,3,1,6,7}));
    }
}
