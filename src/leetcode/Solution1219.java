package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1239. 串联字符串的最大长度
 * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
 *
 * 请返回所有可行解 s 中最长长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = ["un","iq","ue"]
 * 输出：4
 * 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
 * 示例 2：
 *
 * 输入：arr = ["cha","r","act","ers"]
 * 输出：6
 * 解释：可能的解答有 "chaers" 和 "acters"。
 * 示例 3：
 *
 * 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
 * 输出：26
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] 中只含有小写英文字母
 * @author chenzw
 * @date 2021/6/19
 */
public class Solution1219 {
    public static int maxLength(List<String> arr) {
        return dfs(arr,0,new int[26],0,0);
    }

    private static int dfs(List<String> arr,int index,int[] count,int total,int max){
        if(index==arr.size()){
            return Math.max(max,total);
        }
        max = dfs(arr, index + 1, count, total, max);
        String s = arr.get(index);
        for(int i=0;i<s.length();i++){
            if(count[s.charAt(i)-'a']++>0) {
                for(int j=0;j<=i;j++)count[s.charAt(j)-'a']--;
                return Math.max(max, total);
            }
        }
        total+=s.length();
        max = dfs(arr, index + 1, count, total, max);
        for(int i=0;i<s.length();i++)count[s.charAt(i)-'a']--;
        return max;
    }

    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();
        String[] strs = new String[]{"abcdefghijklm","bcdefghijklmn","cdefghijklmno","defghijklmnop","efghijklmnopq","fghijklmnopqr","ghijklmnopqrs","hijklmnopqrst","ijklmnopqrstu","jklmnopqrstuv","klmnopqrstuvw","lmnopqrstuvwx","mnopqrstuvwxy","nopqrstuvwxyz","opqrstuvwxyza","pqrstuvwxyzab"};
        for(String s:strs){
            arr.add(s);
        }
        System.out.println(maxLength(arr));
    }
}
