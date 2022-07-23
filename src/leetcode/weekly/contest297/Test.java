package leetcode.weekly.contest297;

import datastructure.list.ListNode;
import datastructure.tree.TreeNode;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author chenzw
 * @date 2022/6/12
 */
public class Test {
    public double calculateTax(int[][] brackets, int income) {
        double tax = 0;
        int last = 0;
        for (int i = 0; i < brackets.length; i++) {
            int[] b = brackets[i];
            if (income > b[0]) {
                tax += (b[0] - last) * b[1] / 100.0;
            } else {
                tax += (income - last) * b[1] / 100.0;
                break;
            }
            last = b[0];
        }
        return tax;
    }

    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < n; i++) {
            dp[0][i] = grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    dp[i][j] = Math.min(dp[i][j], grid[i][j] + dp[i - 1][k] + moveCost[grid[i - 1][k]][j]);
                }
            }
        }
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            minCost = Math.min(minCost, dp[m - 1][i]);
        }
        return minCost;
    }

    public int distributeCookies(int[] cookies, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> x - y);
        Arrays.sort(cookies);
        for (int i = cookies.length - 1; i >= 0; i--) {
            if (i >= cookies.length - k) {
                queue.add(cookies[i]);
            } else {
                Integer poll = queue.poll();
                if (poll == null) {
                    poll = 0;
                }
                queue.add(poll + cookies[i]);
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            ans = queue.poll();
        }
        return ans;
    }

    public String greatestLetter(String s) {
        List<Character> list = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                list.add(c);
            } else {
                set.add(c);
            }
        }
        int dif = 'a' - 'A';
        Collections.sort(list, (x, y) -> y - x);
        for (char c : list) {
            if (set.contains((char) (c + dif))) {
                return c + "";
            }
        }
        return "";
    }

    public int minimumNumbers(int num, int k) {
        if (num == 0) {
            return 0;
        }

        int m = num % 10;
        if (k == 0) {
            if (m == 0) {
                return 1;
            }
            return -1;
        }
        int n = -1;
        for (int i = 1; ; i++) {
            int p = k * i;
            if (p > num) {
                break;
            }
            if (p % 10 == m) {
                n = i;
            }
        }
        return n;
    }

    /**
     * 2311. 小于等于 K 的最长二进制子序列
     * 给你一个二进制字符串 s 和一个正整数 k 。
     * <p>
     * 请你返回 s 的 最长 子序列，且该子序列对应的 二进制 数字小于等于 k 。
     * <p>
     * 注意：
     * <p>
     * 子序列可以有 前导 0 。
     * 空字符串视为 0 。
     * 子序列 是指从一个字符串中删除零个或者多个字符后，不改变顺序得到的剩余字符序列。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "1001010", k = 5
     * 输出：5
     * 解释：s 中小于等于 5 的最长子序列是 "00010" ，对应的十进制数字是 2 。
     * 注意 "00100" 和 "00101" 也是可行的最长子序列，十进制分别对应 4 和 5 。
     * 最长子序列的长度为 5 ，所以返回 5 。
     * 示例 2：
     * <p>
     * 输入：s = "00101001", k = 1
     * 输出：6
     * 解释："000001" 是 s 中小于等于 1 的最长子序列，对应的十进制数字是 1 。
     * 最长子序列的长度为 6 ，所以返回 6 。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 1000
     * s[i] 要么是 '0' ，要么是 '1' 。
     * 1 <= k <= 109
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubsequence(String s, int k) {
        int n = s.length();
        int max = 1;
        //以第i个数结尾，长为j的字串的最小值
        //j=0,dp[i][j]=0
        //j>0,dp[i][j] = min(dp[k][j-1](0<=k<=i-1))*2+s[i]
        //dp[i][j]的递推只与dp[k][j-1]相关，可优化空间
        //int[][] dp = new int[n][n+1];
        int[][] dp = new int[n][2];
        int[] min = new int[n];//min[i]表示每个长度0到i之间的组成的最小值
        for (int i = 0; i < n; i++) {
            dp[i][1] = s.charAt(i) - '0';
            if (i == 0) {
                min[i] = dp[i][1];
            } else {
                min[i] = Math.min(min[i - 1], dp[i][1]);
            }
        }
        for (int len = 2; len <= n; len++) {
            for (int i = len - 1; i < n; i++) {
                if (min[i - 1] > k) {
                    dp[i][len % 2] = Integer.MAX_VALUE;
                } else {
                    dp[i][len % 2] = min[i - 1] * 2 + s.charAt(i) - '0';
                    if (dp[i][len % 2] <= k) {
                        max = Math.max(max, len);
                    }
                }
                if (i == len - 1) {
                    min[i] = dp[i][len % 2];
                } else {
                    min[i] = Math.min(min[i - 1], dp[i][len % 2]);
                }
            }
        }
        return max;
    }


    public int countAsterisks(String s) {
        int count = 0;
        boolean between = false;
        for (char c : s.toCharArray()) {
            if (c == '|') {
                between = !between;
            } else if (c == '*' && !between) {
                count++;
            }
        }
        return count;
    }

    public long countPairs(int n, int[][] edges) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] e : edges) {
            int p0 = findParent(parent, e[0]);
            int p1 = findParent(parent, e[1]);
            if (p0 == p1) {
                continue;
            }
            parent[p1] = p0;
        }
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            count[findParent(parent, i)]++;
        }
        long ans = 0;
        long left = n;
        for (int i : count) {
            if (i > 0) {
                left = left - i;
                ans += i * left;
            }
        }
        return ans;
    }

    private int findParent(int[] parent, int i) {
        if (parent[i] == i) return i;
        return findParent(parent, parent[i]);
    }

    public int maximumXOR(int[] nums) {
        int[] count = new int[32];
        for (int n : nums) {
            for (int i = 0; i < 32; i++) {
                int k = 1 << i;
                if ((n & k) > 0) {
                    count[i]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (count[i] > 0) {
                ans += 1 << i;
            }
        }
        return ans;
    }


    public int distinctSequences(int n) {
        int mod = (int) 1E9 + 7;
        if (n == 1) return 6;
        long[][][] dp = new long[7][7][n + 1];
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                if (i != j && prime(i, j) == 1) {
                    dp[i][j][2] = 1;
                }
            }
        }
        for (int k = 3; k <= n; k++) {
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 6; j++) {
                    if (i != j && prime(i, j) == 1) {
                        for (int m = 1; m <= 6; m++) {
                            if (m != i) {
                                dp[i][j][k] = (dp[i][j][k] + dp[j][m][k - 1]) % mod;
                            }
                        }
                    }
                }
            }
        }
        long ans = 0;
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                ans = (ans + dp[i][j][n]) % mod;
            }
        }
        return (int) ans;
    }

    private int prime(int a, int b) {
        if (a < b) {
            return prime(b, a);
        }
        return a % b == 0 ? b : prime(b, a % b);
    }


    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || i == n - 1 - j) {
                    if (grid[i][j] == 0) {
                        return false;
                    }
                } else {
                    if (grid[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int countHousePlacements(int n) {
        int mod = (int) 1e9 + 7;
        int[] dp = new int[2];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i <= n; i++) {
            dp[i % 2] = (dp[i % 2] + dp[(i - 1) % 2]) % mod;
        }
        BigDecimal x = new BigDecimal(dp[n % 2]);
        return x.multiply(x).remainder(new BigDecimal(mod)).intValue();
    }


    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] dif = new int[n];
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < n; i++) {
            dif[i] = nums1[i] - nums2[i];
            sum1 += nums1[i];
            sum2 += nums2[i];
        }
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + dif[i - 1];
        }
        int max = 0;
        int min = 0;
        int maxDif = 0;
        int minDif = 0;
        for (int i = 1; i <= n; i++) {
            maxDif = Math.max(maxDif, sum[i] - min);
            minDif = Math.min(minDif, sum[i] - max);
            if (sum[i] > max) {
                max = sum[i];
            }
            if (sum[i] < min) {
                min = sum[i];
            }
        }

        return Math.max(sum1 - minDif, sum2 + maxDif);
    }

    public String decodeMessage(String key, String message) {
        Map<Character, Character> map = new HashMap<>();
        char a = 'a';
        for (char c : key.toCharArray()) {
            if (a > 'z') {
                break;
            }
            if (c != ' ' && !map.containsKey(c)) {
                map.put(c, a++);
            }
        }
        char[] msg = message.toCharArray();
        for (int i = 0; i < msg.length; i++) {
            msg[i] = map.getOrDefault(msg[i], msg[i]);
        }
        return new String(msg);
    }

    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(matrix[i], -1);
        }
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = m - 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                matrix[top][i] = head.val;
                head = head.next;
                if (head == null) {
                    return matrix;
                }
            }
            top++;
            if (left <= right) {
                for (int i = top; i <= bottom; i++) {
                    matrix[i][right] = head.val;
                    head = head.next;
                    if (head == null) {
                        return matrix;
                    }
                }
                right--;
            }
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    matrix[bottom][i] = head.val;
                    head = head.next;
                    if (head == null) {
                        return matrix;
                    }
                }
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    matrix[i][left] = head.val;
                    head = head.next;
                    if (head == null) {
                        return matrix;
                    }
                }
                left++;
            }
        }
        return matrix;
    }

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        /*int[][] count = new int[delay+1][forget+1];
        count[0][0] = 1;
        int ans = 0;
        int f = 1;
        for(int i=0;i<n; i++){

            for(int y=forget;y>0;y--){
                for(int x=delay;x>0;x--){
                    count[x][y] = count[x-1][y-1];
                }
            }
            for(int x=0;x<delay+1;x++){
                count[0][0] = 0;
            }
            for(int x=delay;x<=delay+1;x++){
                for(int y=0;y<forget;y++){
                    count[0][0] += count[x][y];
                }
            }
        }*/
        int MOD = (int) 1e9 + 7;
        long[] count = new long[n];
        count[0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = n - 1; j > 0; j--) {
                count[j] = count[j - 1];
            }
            count[0] = 0;
            for (int j = delay; j < forget; j++) {
                count[0] = (count[0] + count[j]) % MOD;
            }
        }
        long ans = 0;
        for (int i = 0; i < forget; i++) {
            ans = (ans + count[i]) % MOD;
        }
        return (int) ans;
    }

    /*public int countPaths(int[][] grid) {
        int MOD = (int)1e9+7;
        int m = grid.length;
        int n= grid[0].length;
        int[][] dp = new int[m][n];
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                set.add(grid[i][j]);
                dp[i][j] = 1;
            }
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        for(int v:list){
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(grid[i][j]==v){
                        if(i>0&&grid[i][j]>grid[i-1][j]){
                            dp[i][j] = (dp[i][j]+dp[i-1][j])%MOD;
                        }
                        if(i<m-1&&grid[i][j]>grid[i+1][j]){
                            dp[i][j] = (dp[i][j]+dp[i+1][j])%MOD;
                        }
                        if(j>0&&grid[i][j]>grid[i][j-1]){
                            dp[i][j] = (dp[i][j]+dp[i][j-1])%MOD;
                        }
                        if(j<n-1&&grid[i][j]>grid[i][j+1]){
                            dp[i][j] = (dp[i][j]+dp[i][j+1])%MOD;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                ans = (ans+dp[i][j])%MOD;
            }
        }
        return ans;
    }*/

    /*public int countPaths(int[][] grid) {
        int MOD = (int) 1e9 + 7;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<int[]> indexList = map.get(grid[i][j]);
                if (indexList == null) {
                    indexList = new LinkedList<>();
                    map.put(grid[i][j], indexList);
                }
                indexList.add(new int[]{i, j});
                dp[i][j] = 1;
            }
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        for (int v : list) {
            List<int[]> indexList = map.get(v);
            if (indexList != null) {
                for (int[] index : indexList) {
                    int i = index[0];
                    int j = index[1];
                    if (i > 0 && grid[i][j] > grid[i - 1][j]) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j]) % MOD;
                    }
                    if (i < m - 1 && grid[i][j] > grid[i + 1][j]) {
                        dp[i][j] = (dp[i][j] + dp[i + 1][j]) % MOD;
                    }
                    if (j > 0 && grid[i][j] > grid[i][j - 1]) {
                        dp[i][j] = (dp[i][j] + dp[i][j - 1]) % MOD;
                    }
                    if (j < n - 1 && grid[i][j] > grid[i][j + 1]) {
                        dp[i][j] = (dp[i][j] + dp[i][j + 1]) % MOD;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = (ans + dp[i][j]) % MOD;
            }
        }
        return ans;
    }*/


    int[][] memo;
    int m;
    int n;
    int[][] g;
    int MOD = (int) 1e9 + 7;
    public int countPaths(int[][] grid) {
        g = grid;
        m = grid.length;
        n = grid[0].length;
        memo = new int[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(memo[i], -1);
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = (ans + dfs(i, j)) % MOD;
            }
        }
        return ans;
    }

    private int dfs(int i, int j){
        if(memo[i][j]>-1){
            return memo[i][j];
        }
        memo[i][j] = 1;
        if (i > 0 && g[i][j] > g[i - 1][j]) {
            memo[i][j] = (memo[i][j] + dfs(i - 1,j)) % MOD;
        }
        if (i < m - 1 && g[i][j] > g[i + 1][j]) {
            memo[i][j] = (memo[i][j] + dfs(i + 1,j)) % MOD;
        }
        if (j > 0 && g[i][j] > g[i][j - 1]) {
            memo[i][j] = (memo[i][j] + dfs(i,j - 1)) % MOD;
        }
        if (j < n - 1 && g[i][j] > g[i][j + 1]) {
            memo[i][j] = (memo[i][j] + dfs(i,j + 1)) % MOD;
        }
        return memo[i][j];
    }


    public boolean evaluateTree(TreeNode root) {
        if(root.left==null){
            return root.val>0;
        }
        if(root.val == 3){
            return evaluateTree(root.left)&&evaluateTree(root.right);
        }else{
            return evaluateTree(root.left)||evaluateTree(root.right);
        }
    }

    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int max = 0;
        int i = 0;
        Set<Integer> set = new HashSet<>();
        for(int b:buses){
            int count = 0;
            while(i<passengers.length){
                set.add(passengers[i]);
                if(passengers[i]<=b&&count<capacity){
                    max = passengers[i];
                    i++;
                    count++;
                }else{
                    if(count<capacity){
                        max = b+1;
                    }
                    break;
                }
            }
        }
        while(max>0){
            max--;
            if(!set.contains(max)){
                return max;
            }
        }
        return max;
    }

    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] dif = new int[n];
        for (int i = 0; i < n; i++) {
            dif[i] = Math.abs(nums1[i] - nums2[i]);
        }
        int total = k1 + k2;
        Arrays.sort(dif);
        int[] sub = new int[n];
        sub[0] = dif[0];
        for(int i=1;i<n;i++){
            sub[i] = dif[i]-dif[i-1];
        }
        for(int i=n-1;i>=0;i--){
            if(sub[i]>0){
                long sum = (long)sub[i]*(n-i);
                if(sum<=total){
                    sub[i] = 0;
                    total -= sum;
                }else{
                    int a = total/(n-i);
                    if(a>0){
                        sub[i]-=a;
                        total = total%(n-i);
                    }
                    break;
                }
            }
        }

        dif[0] = sub[0];
        for(int i=1;i<n;i++){
            dif[i] = dif[i-1] + sub[i];
        }
        for(int i=n-1;i>=0;i--){
            if(dif[i]==0||total==0){
                break;
            }
            total--;
            dif[i]--;
        }
        long ans = 0;
        for(long i:dif){
            ans += i*i;
        }
        return ans;
    }

    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        int a = amount[2];
        int b = amount[0]+amount[1];
        int d = amount[1]-amount[0];
        if(a>b){
            return a;
        }
        return a + amount[0] - (a-d)/2;

    }


    class SmallestInfiniteSet {
        int min;
        Set<Integer> set ;
        public SmallestInfiniteSet() {
            min = 1;
            set = new HashSet<>();
        }

        public int popSmallest() {
            int result = min;
            min++;
            while(set.contains(min)){
                min++;
            }
            set.add(result);
            return result;
        }

        public void addBack(int num) {
            if(set.contains(num)){
                set.remove(num);
                min = Math.min(min, num);
            }
        }
    }


    public boolean canChange(String start, String target) {
        int n = start.length();
        int[] scount = new int[3];
        int[] tcount = new int[3];
        Deque<Integer>[] s = new Deque[3];
        Deque<Integer>[] t = new Deque[3];
        for(int i=0;i<3;i++){
            s[i] = new LinkedList<>();
            t[i] = new LinkedList<>();
        }
        for(int i=0;i<n;i++){
            if(start.charAt(i)=='_'){
                scount[0]++;
                s[0].add(i);
            }else if(start.charAt(i)=='L'){
                scount[1]++;
                s[1].add(i);
            }else if(start.charAt(i)=='R'){
                scount[2]++;
                s[2].add(i);
            }
            if(target.charAt(i)=='_'){
                tcount[0]++;
                t[0].add(i);
            }else if(target.charAt(i)=='L'){
                tcount[1]++;
                t[1].add(i);
            }else if(target.charAt(i)=='R'){
                tcount[2]++;
                t[2].add(i);
            }
        }
        for(int i=0;i<3;i++){
            if(scount[i]!=tcount[i]){
                return false;
            }
        }
        while(!s[1].isEmpty()){
            int l1 = s[1].poll();
            int l2 = t[1].poll();
            if(l1==l2){
                continue;
            }
            if(l1<l2){
                return false;
            }
            if(!s[2].isEmpty()&&l1>s[2].peek()){
                return false;
            }
        }
        while(!s[2].isEmpty()){
            int r1 = s[2].pollLast();
            int r2 = t[2].pollLast();
            if(r1==r2){
                continue;
            }
            if(r1>r2){
                return false;
            }
            if(!s[1].isEmpty()&&r1<s[1].peek()){
                return false;
            }
        }

        return true;
    }

    public int idealArrays(int n, int maxValue) {
        return 0;
    }

    public static void main(String[] args) {

    }
}
