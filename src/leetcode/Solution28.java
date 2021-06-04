package leetcode;

/**
 * 28. 实现 strStr()
 * 实现 strStr() 函数。
 *
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
 *
 *
 *
 * 说明：
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 *
 *
 *
 * 示例 1：
 *
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 * 示例 2：
 *
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 * 示例 3：
 *
 * 输入：haystack = "", needle = ""
 * 输出：0
 *
 *
 * 提示：
 *
 * 0 <= haystack.length, needle.length <= 5 * 104
 * haystack 和 needle 仅由小写英文字符组成
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
        int[] nextArray = getNextArray(needle);
        int i=0;
        int j=0;
        while(i<haystack.length()&&j<needle.length()){
            if(haystack.charAt(i)==needle.charAt(j)){
                i++;
                j++;
            }else if(nextArray[j]==-1){
                i++;
            }else{
                j=nextArray[j];
            }
        }
        if(j==needle.length()){
            return i-j;
        }
        return -1;
    }
    private static int[] getNextArray(String needle){
        if(needle.length()<2){
            return new int[]{-1};
        }
        int[] nextArray = new int[needle.length()];
        //next数组前两位是固定的
        nextArray[0]=-1;//在第0位就不匹配，则haystack往右走一位，next数组用-1表示
        nextArray[1]=0;//在第1位不匹配，则haystack不动，needle从第0位开始与haystack当前位匹配
        //从i=2开始，若在i处不匹配，要根据实际情况来选择
        int i=2;
        int j = 0;
        //若在haystack第m位与needle的第n位不匹配，则haystack的第m-n到m-1位与needle的前n位相同
        //计算needle.substring(0,i)的后缀与neddle前缀的公共部分长度,本质
        while(i<needle.length()){
            if(needle.charAt(i-1)==needle.charAt(j)){
                nextArray[i++]=++j;//连续匹配累加1
            }else if(j==0){//即nextArray[j]==-1,跳过此位
                nextArray[i++]=0;//一子字符都不匹配则为0；
            }else{
                j = nextArray[j];//部分匹配，根据next数组回退
            }
        }
        return nextArray;
    }

    public static void main(String[] args) {
        System.out.println(strStr("a","a"));
        System.out.println(strStr("mississippi","issip"));
    }
}
