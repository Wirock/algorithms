package leetcode;

/**
 * 423. 从英文中重建数字
 * 给你一个字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按 升序 返回原始的数字。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "owoztneoer"
 * 输出："012"
 * 示例 2：
 *
 * 输入：s = "fviefuro"
 * 输出："45"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s[i] 为 ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"] 这些字符之一
 * s 保证是一个符合题目要求的字符串
 * @author chenzw
 * @date 2021/11/24
 */
public class Solution423 {
    public String originalDigits(String s) {
        int[] count = new int[26];
        for(int i=0;i<s.length();i++){
            count[s.charAt(i)-'a']++;
        }
        int[] digitCount = new int[10];
        //0,2,4,6,8有唯一字母，先算出来
        int c = count['z'-'a'];
        if(c>0){
            digitCount[0]+=c;
            sub(count,"zero",c);
        }
        c = count['w'-'a'];
        if(c>0){
            digitCount[2]+=c;
            sub(count,"two",c);
        }
        c = count['u'-'a'];
        if(c>0){
            digitCount[4]+=c;
            sub(count,"four",c);
        }
        c = count['x'-'a'];
        if(c>0){
            digitCount[6]+=c;
            sub(count,"six",c);
        }
        c = count['g'-'a'];
        if(c>0){
            digitCount[8]+=c;
            sub(count,"eight",c);
        }
        //去掉0,2,4,6,8后
        //1，3，5，7有唯一字母
        c = count['o'-'a'];
        if(c>0){
            digitCount[1]+=c;
            sub(count,"zero",c);
        }
        c = count['t'-'a'];
        if(c>0){
            digitCount[3]+=c;
            sub(count,"three",c);
        }
        c = count['f'-'a'];
        if(c>0){
            digitCount[5]+=c;
            sub(count,"five",c);
        }
        c = count['s'-'a'];
        if(c>0){
            digitCount[7]+=c;
            sub(count,"seven",c);
        }
        //剩下9，算i或e的数量即可
        c = count['i'-'a'];
        if(c>0){
            digitCount[9]+=c;
            sub(count,"nine",c);
        }

        StringBuilder ans = new StringBuilder();
        for(int i=0;i<digitCount.length;i++){
            for(int j=0;j<digitCount[i];j++)ans.append(i+"");
        }
        return ans.toString();
    }

    private void sub(int[] count,String word,int s){
        for(int i=0;i<word.length();i++){
            count[word.charAt(i)-'a']-=s;
        }
    }
    public static void main(String[] args) {
        new Solution423().originalDigits("");
    }
}
