package leetcode;

/**
 * @author chenzw
 * @date 2021/5/6
 */
public class Solution1720 {
    public int[] decode(int[] encoded, int first) {
        int[] arr = new int[encoded.length+1];
        arr[0] = first;
        for(int i=0;i<encoded.length;i++){
            arr[i+1]=encoded[i]^arr[i];
        }
        return arr;
    }
}
