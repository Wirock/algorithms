package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
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
