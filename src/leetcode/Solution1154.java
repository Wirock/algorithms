package leetcode;

/**
 * @author chenzw
 * @date 2021/12/21
 */
public class Solution1154 {
    int[] daysOfMonth = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
    public int dayOfYear(String date) {
        String[] split = date.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        int ans = 0;
        for(int i=0;i<month-1;i++){
            ans += daysOfMonth[i];
        }
        ans += day;
        if(month>2&&(year%4==0||year%400==0)){
            ans += 1;
        }
        return ans;
    }
}
