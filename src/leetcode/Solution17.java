package leetcode;

import java.util.*;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 * <p>
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 * 通过次数233,663提交次数416,467
 *
 * @author chenzw
 * @date 2021/3/14
 */
public class Solution17 {
    /*public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        if(digits==null||digits.length()==0){
            return result;
        }
        String value = phoneMap.get(digits.charAt(0));
        for(int i=0;i<value.length();i++){
            result.add(value.charAt(i)+"");
        }
        for(int i=1;i<digits.length();i++){
            ListIterator<String> iterator = result.listIterator();
            String str = phoneMap.get(digits.charAt(i));
            while(iterator.hasNext()){
                String s = iterator.next();
                iterator.remove();
                for(int j=0;j<str.length();j++){
                    iterator.add(s+str.charAt(j));
                }
            }
        }
        return result;
    }*/
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        if (digits == null || digits.length() == 0) {
            return result;
        }
        findCombinations(result, digits, 0, new StringBuilder(), phoneMap);//使用空字符作为起始节点
        return result;
    }

    //回溯获取所有分支，放入result
    // index是下一个数字的坐标，StringBuilder为上一级的结果，使用StringBuilder增删末尾的字符来进行回溯
    private static void findCombinations(List<String> result, String digits, int index, StringBuilder sb, Map<Character, String> phoneMap) {
        if (index == digits.length()) {//下探到底，获取一条完整分支，装入result
            result.add(sb.toString());
            return;
        }
        String c = phoneMap.get(digits.charAt(index));
        //遍历当前节点的所有分支
        for (int i = 0; i < c.length(); i++) {
            sb.append(c.charAt(i));//下探一级
            findCombinations(result, digits, index + 1, sb, phoneMap);//获取当前分支节点的所有结果,放入result
            sb.deleteCharAt(index);//回溯
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(letterCombinations("234").toArray()));
    }
}
