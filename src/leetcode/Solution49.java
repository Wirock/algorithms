package leetcode;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * @author chenzw
 * @date 2021/5/26
 */
public class Solution49 {
    //排序
    /*public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for(int i=0;i<strs.length;i++){
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(strs[i]);
            map.put(key,list);
        }
        return new ArrayList<>(map.values());
    }*/

    //计数
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        int[] count = new int[26];
        for(int i=0;i<strs.length;i++){
            for(int j=0;j<strs[i].length();j++){
                count[strs[i].charAt(j)-'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<count.length;j++){
                while(count[j]>0){
                    sb.append('a'+j);
                    count[j]--;
                }
            }
            List<String> list = map.getOrDefault(sb.toString(), new ArrayList<>());
            list.add(strs[i]);
            map.put(sb.toString(),list);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        List<List<String>> lists = groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        for(List<String> l:lists){
            System.out.println(Arrays.toString(l.toArray()));
        }
    }
}
