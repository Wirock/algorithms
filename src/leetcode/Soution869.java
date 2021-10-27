package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 869. 重新排序得到 2 的幂
 * 给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 *
 * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：1
 * 输出：true
 * 示例 2：
 *
 * 输入：10
 * 输出：false
 * 示例 3：
 *
 * 输入：16
 * 输出：true
 * 示例 4：
 *
 * 输入：24
 * 输出：false
 * 示例 5：
 *
 * 输入：46
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= N <= 10^9
 * @author chenzw
 * @date 2021/10/28
 */
public class Soution869 {
    //hash计数预处理
    Set<String> set = new HashSet<>();
    {
        int k=1;
        for(int i=0;i<31;i++){
            set.add(getCountStr(k));
            k*=2;
        }
    }

    private String getCountStr(int n){
        char[] count = new char[10];
        while(n>0){
            count[n%10]++;
            n/=10;
        }
        return new String(count);
    }

    public boolean reorderedPowerOf2(int n) {
        return set.contains(getCountStr(n));
    }
}
