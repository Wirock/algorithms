package leetcode.weekly.contest266;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzw
 * @date 2021/11/7
 */
public class Solution5918 {
    public int countVowelSubstrings(String word) {
        int n = word.length();
        char[] cs = word.toCharArray();
        Set<Character> candidate = new HashSet();
        candidate.add('a');
        candidate.add('e');
        candidate.add('i');
        candidate.add('o');
        candidate.add('u');
        Set<Character> set = new HashSet();
        int ans=0;
        for(int i=0;i<=n-5;i++){
            if(!candidate.contains(cs[i]))continue;
            for(int j=i;j<n;j++){
                if(candidate.contains(cs[j])){
                    set.add(cs[j]);
                    if(set.size()==5) {
                        ans++;
                    }
                }else{
                    break;
                }
            }
            set.clear();
        }
        return ans;
    }
}
