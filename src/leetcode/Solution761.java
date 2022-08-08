package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 761. 特殊的二进制序列
 * 特殊的二进制序列是具有以下两个性质的二进制序列：
 *
 * 0 的数量与 1 的数量相等。
 * 二进制序列的每一个前缀码中 1 的数量要大于等于 0 的数量。
 * 给定一个特殊的二进制序列 S，以字符串形式表示。定义一个操作 为首先选择 S 的两个连续且非空的特殊的子串，然后将它们交换。（两个子串为连续的当且仅当第一个子串的最后一个字符恰好为第二个子串的第一个字符的前一个字符。)
 *
 * 在任意次数的操作之后，交换后的字符串按照字典序排列的最大的结果是什么？
 *
 * 示例 1:
 *
 * 输入: S = "11011000"
 * 输出: "11100100"
 * 解释:
 * 将子串 "10" （在S[1]出现） 和 "1100" （在S[3]出现）进行交换。
 * 这是在进行若干次操作后按字典序排列最大的结果。
 * 说明:
 *
 * S 的长度不超过 50。
 * S 保证为一个满足上述定义的特殊 的二进制序列。
 * @author chenzw
 * @date 2022/8/8
 */
public class Solution761 {
    //分治
    //看作山峰高度变化,1代表高度增加，0代表高度减少
    //题意可看作是对递归对子峰进行由高到低排序
    public String makeLargestSpecial(String s) {
        if(s.length()<=2){
            return s;
        }
        char[] cs = s.toCharArray();

        //寻找最低的山谷高度
        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for(int i=0;i<cs.length;i++){
            if(cs[i]=='1'){
                if(i>0&&cs[i-1]=='0'&&cnt<min){
                    min = cnt;
                }
                cnt++;
            }else{
                cnt--;
            }
        }
        if(min == Integer.MAX_VALUE){
            return s;
        }
        //按最低山谷划分子峰，进行排序
        StringBuilder ans = new StringBuilder();
        int begin = min;
        List<String> list = new ArrayList<>();
        for(int i=0;i<cs.length;i++){
            if(cs[i]=='1'){
                cnt++;
            }else{
                cnt--;
            }
            if(cnt==0&&i>0&&i<cs.length-1){
                list.add(makeLargestSpecial(s.substring(begin,i+1)));
                begin = i+1;
            }
        }

        list.add(makeLargestSpecial(s.substring(begin,cs.length-min)));

        if(min>0){
            ans.append(s,0,min);
        }
        Collections.sort(list, Comparator.reverseOrder());
        for(String x:list){
            ans.append(x);
        }
        if(min>0){
            ans.append(s,cs.length-min,cs.length);
        }
        return ans.toString();
    }
}
