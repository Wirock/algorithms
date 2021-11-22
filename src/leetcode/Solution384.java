package leetcode;

import java.util.Random;

/**
 * @author chenzw
 * @date 2021/11/22
 */
public class Solution384 {
    class Solution {

        int[] nums;
        public Solution(int[] nums) {
            this.nums = nums;
        }

        public int[] reset() {
            return nums;
        }

        public int[] shuffle() {
            int[] s = nums.clone();
            int n = s.length;
            Random random = new Random();
            for(int i=0;i<n;i++){
                int x = i+random.nextInt(n-i);
                int temp = s[x];
                s[x] = s[i];
                s[i] = temp;
            }
            return s;
        }
    }
}
