package leetcode;

/**
 * 13. 罗马数字转整数
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 *
 * @author chenzw
 * @date 2021/3/14
 */
public class Solution13 {
    /*static int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    static String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static int romanToInt(String s) {
        int result = 0;
        for (int i = 0; i < symbols.length; i++) {
            while (s.startsWith(symbols[i])) {
                result += values[i];
                s = s.substring(symbols[i].length());
            }
        }
        return result;
    }*/

    //改进
    static int[] values = {1000, 500, 100, 50, 10, 5, 1};
    static char[] symbols = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};

    public static int romanToInt(String s) {
        int result = 0;
        int pre = 0;
        for (int j = 0; j < symbols.length; j++) {
            if (s.charAt(0) == symbols[j]) {
                pre = values[j];
                break;
            }
        }
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < symbols.length; j++) {
                if (s.charAt(i) == symbols[j]) {
                    if (pre < values[j]) {
                        result -= pre;
                    } else {
                        result += pre;
                    }
                    pre = values[j];
                    break;
                }
            }
        }
        result += pre;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }
}
