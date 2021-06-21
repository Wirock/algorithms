package offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenzw
 * @date 2021/6/22
 */
public class Offer38 {
    public String[] permutation(String s) {
        List<String> ans = new ArrayList<>();
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        ans.add(new String(chars));
        while(true){
            int i=chars.length-2;
            int j=chars.length-1;
            while(i>=0&&chars[i]>=chars[i+1])i--;
            if(i<0)break;
            while(j>i&&chars[i]>=chars[j])j--;
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            if(i<chars.length-2) Arrays.sort(chars,i+1,chars.length);
            ans.add(new String(chars));
        }
        String[] strs = new String[ans.size()];
        ans.toArray(strs);
        return strs;
    }
}
