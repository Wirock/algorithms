package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 131. 分割回文串
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 * 回文串 是正着读和反着读都一样的字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 * @author chenzw
 * @date 2021/7/7
 */
public class Solution131 {
    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        dfs(ans,new ArrayList<>(),s,0);
        return ans;
    }
    private static void dfs(List<List<String>> ans,List<String> combine,String s,int index){
        if(index==s.length()){
            ans.add(new ArrayList<>(combine));
            return;
        }
        for(int i=1;i<=s.length()-index;i++){
            String str = s.substring(index,index+i);
            int left = 0;
            int right = str.length()-1;
            boolean valid = true;
            while(left<right){
                if(str.charAt(left++)!=str.charAt(right--)){
                    valid = false;
                    break;
                }
            }
            if(!valid)continue;
            combine.add(str);
            dfs(ans,combine,s,index+i);
            combine.remove(combine.size()-1);
        }
    }

    public static void main(String[] args) {
        List<List<String>> ret = partition("aab");
        for(List<String> l:ret){
            System.out.println(Arrays.toString(l.toArray()));
        }
    }
}
