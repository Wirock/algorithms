package leetcode;

/**
 * 5760. 构成交替字符串需要的最小交换次数 显示英文描述
 * 通过的用户数190
 * 尝试过的用户数303
 * 用户总通过次数190
 * 用户总提交次数395
 * 题目难度Medium
 * 给你一个二进制字符串 s ，现需要将其转化为一个 交替字符串 。请你计算并返回转化所需的 最小 字符交换次数，如果无法完成转化，返回 -1 。
 *
 * 交替字符串 是指：相邻字符之间不存在相等情况的字符串。例如，字符串 "010" 和 "1010" 属于交替字符串，但 "0100" 不是。
 *
 * 任意两个字符都可以进行交换，不必相邻 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "111000"
 * 输出：1
 * 解释：交换位置 1 和 4："111000" -> "101010" ，字符串变为交替字符串。
 * 示例 2：
 *
 * 输入：s = "010"
 * 输出：0
 * 解释：字符串已经是交替字符串了，不需要交换。
 * 示例 3：
 *
 * 输入：s = "1110"
 * 输出：-1
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s[i] 的值为 '0' 或 '1'
 * @author chenzw
 * @date 2021/5/16
 */
public class Solution5760 {
    public int minSwaps(String s) {
        char[] chars = s.toCharArray();
        int count=0;
        int odd = 0;
        int even = 0;
        for(int i=0;i<chars.length;i++){
            if(chars[i]=='1'){
                if(i%2==0)even++;
                else odd++;
                count++;
            }else{
                count--;
            }
        }
        if(count>1||count<-1)return -1;
        if(chars.length%2==1){
            if(even+odd==chars.length/2+1)return odd;
            return even;
        }
        return Math.min(odd,even);
    }
}
