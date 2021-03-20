package leetcode;

/**
 * 28. 实现 strStr()
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * @author chenzw
 * @date 2021/3/20
 */
public class Solution28 {
    public static int strStr(String haystack, String needle) {
        if("".equals(needle)){
            return 0;
        }
        if("".equals(haystack)){
            return -1;
        }
        for(int i=0;i<=haystack.length()-needle.length();){
            boolean match = true;
            for(int j=0;j<needle.length();j++){
                if(haystack.charAt(i+j)!=needle.charAt(j)){
                    match = false;
                    i += j+1;
                    break;
                }
            }
            if(match){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(strStr("a","a"));
        System.out.println(strStr("mississippi","issip"));
    }
}
