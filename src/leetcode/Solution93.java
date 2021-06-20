package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 93. 复原 IP 地址
 * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 *
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 *
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 *
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 示例 4：
 *
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 示例 5：
 *
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 3000
 * s 仅由数字组成
 * @author chenzw
 * @date 2021/6/12
 */
public class Solution93 {
    public static List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        dfs(ans,new StringBuilder(),s,0,0,0,0);
        return ans;
    }

    private static void dfs(List<String> ans,StringBuilder sb,String s,int index,int countValue,int value,int countDot){
        if(countDot>3)return;
        if(countValue==2&&value<10)return;
        if(countValue==3&&value<100)return;
        if(value>255)return;
        if(index==s.length()){
            if(countDot==3)ans.add(sb.toString());
            return;
        }
        if(countValue>0){
            sb.append(".");
            sb.append(s.charAt(index));
            dfs(ans,sb,s,index+1,1,s.charAt(index)-'0',countDot+1);
            sb.delete(sb.length()-2,sb.length());
        }
        sb.append(s.charAt(index));
        dfs(ans,sb,s,index+1,countValue+1,value*10+s.charAt(index)-'0',countDot);
        sb.deleteCharAt(sb.length()-1);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(restoreIpAddresses("25525511135").toArray()));
        System.out.println(Arrays.toString(restoreIpAddresses("0000").toArray()));
        System.out.println(Arrays.toString(restoreIpAddresses("1111").toArray()));
        System.out.println(Arrays.toString(restoreIpAddresses("010010").toArray()));
        System.out.println(Arrays.toString(restoreIpAddresses("101023").toArray()));
    }
}
