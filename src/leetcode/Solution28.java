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
        nextArray[0]=-1;
        nextArray[1]=0;
        int i=2;
        int j = 0;
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
