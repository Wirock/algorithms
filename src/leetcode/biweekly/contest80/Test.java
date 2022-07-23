package leetcode.biweekly.contest80;

import java.util.Arrays;

/**
 * @author chenzw
 * @date 2022/6/11
 */
public class Test {

    public boolean strongPasswordCheckerII(String password) {
        if(password.length()<8){
            return false;
        }
        Character last = null;
        boolean lower = false;
        boolean upper = false;
        boolean digit = false;
        boolean special = false;
        for(char c:password.toCharArray()){
            if(last!=null&&last==c){
                return false;
            }
            last = c;
            if(c>='a'&&c<='z'){
                lower = true;
            }else if(c>='A'&&c<='Z'){
                upper = true;
            }else if(c>='0'&&c<='9'){
                digit = true;
            }else if("!@#$%^&*()-+".contains(c+"")){
                special = true;
            }
        }
        return lower&&upper&&digit&&special;
    }

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int[] ans = new int[spells.length];
        for(int i=0;i<spells.length;i++){
            int left = 0;
            int right = potions.length;
            while(left<right){
                int mid = (right-left)/2 + left;
                if(((long)spells[i])*potions[mid]<success){
                    left = mid+1;
                }else{
                    right = mid;
                }
            }
            ans[i] = potions.length - left;
        }
        return ans;
    }

    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        long[] sum = new long[n+1];
        for(int i=0;i<n;i++){
            sum[i+1] = sum[i]+nums[i];
        }
        long ans = 0;
        for(int i=0;i<n;i++){
            int left = i+1;
            int right = n;
            while(left <= right){
                int mid = left + (right-left)/2;
                if((sum[mid]-sum[i])*(mid-i)<k){
                   left = mid+1;
                }else{
                    right = mid-1;
                }
            }
            ans += right-i;
        }
        return ans;
    }


    public boolean matchReplacement(String s, String sub, char[][] mappings) {
        int m = s.length();
        int n = sub.length();
        //dp[i][j]表示 s.substring(i,i+j+1)与sub.substring(0,j+1)是否可替换相等
        //有 dp[i][j] = dp[i][j-1] && s.charAt(i+j) 与 sub.charAt(j)是否可替换相等
        boolean[][] dp = new boolean[m][n];

        for(int i=0;i<=m-n;i++){
            dp[i][0] = check(s.charAt(i), sub.charAt(0), mappings);
        }
        for(int i=1;i<=m-n;i++){
            for(int j=0;j<n;j++){

            }
        }

        for(int i=n-1;i<m;i++){
            if(dp[i][n-1]){
                return true;
            }
        }
        return false;
    }
    private boolean check(char a, char b, char[][] mappings ){
        if(a == b){
            return true;
        }
        for(char[] c:mappings){
            if(b == c[0] && a == c[1]){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
