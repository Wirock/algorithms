package leetcode;

import java.util.Arrays;

/**
 * 2517. 礼盒的最大甜蜜度
 * 给你一个正整数数组 price ，其中 price[i] 表示第 i 类糖果的价格，另给你一个正整数 k 。
 *
 * 商店组合 k 类 不同 糖果打包成礼盒出售。礼盒的 甜蜜度 是礼盒中任意两种糖果 价格 绝对差的最小值。
 *
 * 返回礼盒的 最大 甜蜜度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：price = [13,5,1,8,21,2], k = 3
 * 输出：8
 * 解释：选出价格分别为 [13,5,21] 的三类糖果。
 * 礼盒的甜蜜度为 min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8 。
 * 可以证明能够取得的最大甜蜜度就是 8 。
 * 示例 2：
 *
 * 输入：price = [1,3,1], k = 2
 * 输出：2
 * 解释：选出价格分别为 [1,3] 的两类糖果。
 * 礼盒的甜蜜度为 min(|1 - 3|) = min(2) = 2 。
 * 可以证明能够取得的最大甜蜜度就是 2 。
 * 示例 3：
 *
 * 输入：price = [7,7,7,7], k = 2
 * 输出：0
 * 解释：从现有的糖果中任选两类糖果，甜蜜度都会是 0 。
 *
 *
 * 提示：
 *
 * 1 <= price.length <= 105
 * 1 <= price[i] <= 109
 * 2 <= k <= price.length
 * Created by Chenzw on 2023/6/2 1:09
 */
public class Solution2517 {
    //排序+二分+贪心
    public int maximumTastiness(int[] price, int k) {
        int n = price.length;
        Arrays.sort(price);
        int left=0;
        int right = price[n-1]-price[0];
        while(left<right){
            int mid = (left+right+1)/2;
            if(check(price, mid, k)){
                left = mid;
            }else{
                right = mid-1;
            }
        }
        return left;
    }

    private boolean check(int[] price, int v, int k){
        int cnt = 1;
        int pre = price[0];
        for(int i=1;i<price.length;i++){
            if(price[i]-pre>=v){
                cnt++;
                pre = price[i];
                if(cnt==k){
                    return true;
                }
            }
        }
        return false;
    }
}
