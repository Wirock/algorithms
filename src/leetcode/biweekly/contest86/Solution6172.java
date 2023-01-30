package leetcode.biweekly.contest86;

/**
 * Created by Chenzw on 2022/9/4 0:21
 */
public class Solution6172 {
    //暴力枚举
    public boolean isStrictlyPalindromic(int n) {
        for(int i=2;i<n-1;i++){
            int k = n;
            StringBuilder sb = new StringBuilder();
            while(k>0){
                sb.append(k%i);
                k = k/i;
            }
            if(!sb.toString().equals(sb.reverse().toString())){
                return false;
            }
        }
        return true;
    }
}
