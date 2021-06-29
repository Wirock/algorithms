package leetcode.weekly.contest247;

/**
 * 5799. 最美子字符串的数目
 * 如果某个字符串中 至多一个 字母出现 奇数 次，则称其为 最美 字符串。
 *
 * 例如，"ccjjc" 和 "abab" 都是最美字符串，但 "ab" 不是。
 * 给你一个字符串 word ，该字符串由前十个小写英文字母组成（'a' 到 'j'）。请你返回 word 中 最美非空子字符串 的数目。如果同样的子字符串在 word 中出现多次，那么应当对 每次出现 分别计数。
 *
 * 子字符串 是字符串中的一个连续字符序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：word = "aba"
 * 输出：4
 * 解释：4 个最美子字符串如下所示：
 * - "aba" -> "a"
 * - "aba" -> "b"
 * - "aba" -> "a"
 * - "aba" -> "aba"
 * 示例 2：
 *
 * 输入：word = "aabb"
 * 输出：9
 * 解释：9 个最美子字符串如下所示：
 * - "aabb" -> "a"
 * - "aabb" -> "aa"
 * - "aabb" -> "aab"
 * - "aabb" -> "aabb"
 * - "aabb" -> "a"
 * - "aabb" -> "abb"
 * - "aabb" -> "b"
 * - "aabb" -> "bb"
 * - "aabb" -> "b"
 * 示例 3：
 *
 * 输入：word = "he"
 * 输出：2
 * 解释：2 个最美子字符串如下所示：
 * - "he" -> "h"
 * - "he" -> "e"
 *
 *
 * 提示：
 *
 * 1 <= word.length <= 105
 * word 由从 'a' 到 'j' 的小写英文字母组成
 * @author chenzw
 * @date 2021/6/27
 */
public class Solution5799 {

    //设S(i,j)表示坐标区间为[i,j]的子字符串,则S(i,j)为最美子字符串的充分条件是S(0,j)和S(0,i-1)各个字符的数量奇偶性相同或仅相差1个
    //用掩码mask的低10位表示'a'到'j'各个字符数量的奇偶性,设f(x,mask)表示前x个字符，在各个字符奇偶性为mask时的前缀字符串的数量
    //设f(x)表示前x个字符中以第x个字符结尾的最美子字符串的数量，f(x)各个位置的奇偶情况为mask(x),与mask(x)在第i位不同的掩码位mask(x,i)
    //则有f(x) = pre(x-1,mask(x))+Σpre(x-1,mask'(x,i)),0=<i<10
    //则题目所求为Σf(x), 0<x<=word.length()
    /*public static long wonderfulSubstrings(String word) {
        long[][] pre = new long[word.length()+1][1<<10];
        int[] mask = new int[word.length()+1];
        long[] f = new long[word.length()+1];
        pre[0][0] = 1;
        for(int i=1;i<=word.length();i++){
            mask[i] = mask[i-1]^(1<<(word.charAt(i-1)-'a'));
            for(int k=0;k<(1<<10);k++)pre[i][k] = pre[i-1][k];
            pre[i][mask[i]] = pre[i-1][mask[i]]+1;
            f[i] = pre[i-1][mask[i]];
            for(int j=0;j<10;j++){
                int m = mask[i]^(1<<j);
                f[i]+=pre[i-1][m];
            }
        }
        long ans = 0;
        for(int i=1;i<=word.length();i++){
            ans += f[i];
        }
        return ans;
    }*/

    //滚动数组优化，pre[x][mask]只与pre[x-1][mask]相关，最终结果只与pre[x-1]相关，故用一维数组即可
    //mask[i]只与mask[i-1]相关，每一层循环只与mask[i]相关，故用一个int即可
    //最终结果时f[i]的总和，直接在过程中累加，不需要f[i]数组
    public static long wonderfulSubstrings(String word) {
        long[] pre = new long[1<<10];
        int mask = 0;
        long ans= 0;
        pre[0] = 1;
        for(int i=1;i<=word.length();i++){
            mask ^= 1<<(word.charAt(i-1)-'a');
            ans += pre[mask];
            for(int j=0;j<10;j++){
                int m = mask^(1<<j);
                ans+=pre[m];
            }
            pre[mask]++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(wonderfulSubstrings("aba"));
        System.out.println(wonderfulSubstrings("aabb"));
        System.out.println(wonderfulSubstrings("he"));
    }
}
