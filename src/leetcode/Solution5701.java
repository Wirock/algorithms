package leetcode;

/**
 * 5701. 仅执行一次字符串交换能否使两个字符串相等
 * 给你长度相等的两个字符串 s1 和 s2 。一次 字符串交换 操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。
 *
 * 如果对 其中一个字符串 执行 最多一次字符串交换 就可以使两个字符串相等，返回 true ；否则，返回 false 。
 * @author chenzw
 * @date 2021/3/14
 */
public class Solution5701 {
    public static boolean areAlmostEqual(String s1, String s2) {
        if(s1.length()!=s2.length()){
            return false;
        }
        if(s1.length()<2){
            return false;
        }
        int m=-1;
        int n=-1;
        int count = 0;
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                count++;
                if(count>2){
                    return false;
                }
                if(count==1){
                    m=i;
                }else if(count==2){
                    n=i;
                }
            }
        }
        if(m>-1&&n>-1){
            return s1.charAt(m)==s2.charAt(n)&&s1.charAt(n)==s2.charAt(m);
        }
        return count!=1;
    }

    public static void main(String[] args) {
        System.out.println(areAlmostEqual("bank","kanb"));
        System.out.println(areAlmostEqual("attack","defend"));
    }
}
