package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 187. 重复的DNA序列
 * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 *
 * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 * 示例 2：
 *
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 105
 * s[i] 为 'A'、'C'、'G' 或 'T'
 * @author chenzw
 * @date 2021/10/8
 */
public class Solution187 {

    //1.滑动窗口+哈希表
    /*public List<String> findRepeatedDnaSequences(String s) {
        Map<String,Integer> map = new HashMap<>();
        List<String> ans = new LinkedList<>();
        char[] chars = s.toCharArray();
        int n = chars.length;
        if(n<=10)return ans;
        StringBuilder sb = new StringBuilder(s.substring(0,10));
        map.put(sb.toString(),1);
        for(int i=10;i<n;i++){
            sb.append(chars[i]);
            sb.deleteCharAt(0);
            String key = sb.toString();
            map.put(key,map.getOrDefault(key,0)+1);
        }
        for(Map.Entry<String,Integer> e:map.entrySet()){
            if(e.getValue()>1)ans.add(e.getKey());
        }
        return ans;
    }*/

    //在1的基础上进行优化,由于s[i]只有四种值，故可映射为Integer类型

    static final int L = 10;
    Map<Character, Integer> bin = new HashMap<Character, Integer>() {{
        put('A', 0);
        put('C', 1);
        put('G', 2);
        put('T', 3);
    }};
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new LinkedList<>();
        int n = s.length();
        if (n <= L) {
            return ans;
        }
        int x = 0;
        for (int i = 0; i < L - 1; ++i) {
            x = (x << 2) | bin.get(s.charAt(i));
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        int mask = (1 << (L * 2)) - 1;
        for (int i = 0; i <= n - L; ++i) {
            x = ((x << 2) | bin.get(s.charAt(i + L - 1))) & mask;
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            if (cnt.get(x) == 2) {
                ans.add(s.substring(i, i + L));
            }
        }
        return ans;
    }
}
