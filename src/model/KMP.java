package model;

/**
 * @author chenzw
 * @date 2022/8/11
 */
public class KMP {
    public boolean kmp(String str, String pattern){
        // s[]是长文本，p[]是模式串，n是s的长度，m是p的长度
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        int n = s.length;
        int m = p.length;
        //求模式串的Next数组：
        int[] ne = new int[m];
        for (int i = 2, j = 0; i <= m; i ++ )
        {
            while (j>0 && p[i] != p[j + 1]) {
                j = ne[j];
            }
            if (p[i] == p[j + 1]) {
                j++;
            }
            ne[i] = j;
        }

        // 匹配
        for (int i = 1, j = 0; i <= n; i ++ ){
            while (j>0 && s[i] != p[j + 1]) {
                j = ne[j];
            }
            if (s[i] == p[j + 1]) {
                j++;
            }
            if (j == m){
                j = ne[j];
                // 匹配成功后的逻辑
                return true;
            }
        }
        return false;
    }
}
