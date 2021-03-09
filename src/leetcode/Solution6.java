package leetcode;

/**
 * 6. Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 * @author chenzw
 * @date 2021/3/9
 */
public class Solution6 {
    public static String convert(String s, int numRows) {
        if(numRows==1||s.length()<=numRows){
            return s;
        }
        char[] chars = s.toCharArray();
        char[] resultArr = new char[chars.length];
        int index=0;
        for(int i=0;i<numRows;i++){
            int j=i;
            while(j<chars.length){
                resultArr[index++] = chars[j];
                j+=2*(numRows-1);
                if(i>0&&i<numRows-1&&j-2*i<chars.length){
                    resultArr[index++] = chars[j-2*i];
                }
            }
        }
        return  String.valueOf(resultArr);
    }

    public static void main(String[] args) {
        System.out.println("PAHNAPLSIIGYIR".equals(convert("PAYPALISHIRING",3)));
        System.out.println(convert("PAYPALISHIRING",3));
        System.out.println("PINALSIGYAHRPI".equals(convert("PAYPALISHIRING",4)));
        System.out.println(convert("PAYPALISHIRING",4));
        System.out.println(convert("AB",1));
    }
}
