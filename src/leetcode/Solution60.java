package leetcode;

/**
 * 60. 排列序列
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3, k = 3
 * 输出："213"
 * 示例 2：
 *
 * 输入：n = 4, k = 9
 * 输出："2314"
 * 示例 3：
 *
 * 输入：n = 3, k = 1
 * 输出："123"
 *
 *
 * 提示：
 *
 * 1 <= n <= 9
 * 1 <= k <= n!
 * @author chenzw
 * @date 2021/5/27
 */
public class Solution60 {
    public static String getPermutation(int n, int k) {
        if(n==1)return "1";
        StringBuilder str = new StringBuilder("123456789");
        StringBuilder ans = new StringBuilder();
        //计算n-1的阶乘
        int factorial = 1;
        int m = n-1;
        while(m>0){
            factorial*=m--;
        }
        //第i位取每个数的情况都有(n-i的阶乘)种
        int a=(k-1)/factorial;//算出第一位取值
        ans.append(str.charAt(a));
        str.deleteCharAt(a);//待选元素中删掉已选的元素
        n--;
        while(n>1){//同理，计算后面n-1位的数字
            k = k-a*factorial;
            factorial/=n;
            a = (k-1)/factorial;
            ans.append(str.charAt(a));
            str.deleteCharAt(a);
            n--;
        }
        ans.append(str.charAt(0));
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(getPermutation(3,3));
        System.out.println(getPermutation(4,9));
        System.out.println(getPermutation(3,1));
    }
}
