package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 89. 格雷编码
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 *
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
 *
 * 格雷编码序列必须以 0 开头。
 *
 *
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 *
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 *
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * 示例 2:
 *
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 *      给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
 *      因此，当 n = 0 时，其格雷编码序列为 [0]。
 * @author chenzw
 * @date 2021/6/11
 */
public class Solution89 {
    /*public static List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        if(n==0)return ans;
        ans.add(1);
        for(int i=1;i<n;i++){
            List<Integer> list = new ArrayList<>();
            int k = 1<<i;
            for(int j=ans.size()-1;j>=0;j--){
                list.add(ans.get(j)^k);
            }
            ans.addAll(list);
        }
        return ans;
    }*/
    public static List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<>();
        for(int i = 0; i < 1<<n; ++i)
            ret.add(i ^ i>>1);
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(grayCode(2).toArray()));
        System.out.println(Arrays.toString(grayCode(3).toArray()));
        System.out.println(Arrays.toString(grayCode(4).toArray()));
        System.out.println(Arrays.toString(grayCode(10).toArray()));
    }
}
