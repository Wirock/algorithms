package leetcode;

import java.util.*;

/**
 * @author chenzw
 * @date 2021/4/17
 */
public class Test {

    public String sortSentence(String s) {
        StringBuilder ans = new StringBuilder();
        String[] strs = s.split(" ");
        Arrays.sort(strs,(x,y)->x.charAt(x.length()-1)-y.charAt(y.length()-1));
        for(int i=0;i<strs.length;i++){
            ans.append(strs[i].substring(0,strs[i].length()-1)+" ");
        }
        ans.deleteCharAt(ans.length()-1);
        return ans.toString();
    }

    public int[] memLeak(int memory1, int memory2) {
        int i=1;
        while(memory1>=i||memory2>=i){
            if(memory1>=memory2)memory1-=i;
            else memory2-=i;
            i++;
        }
        return new int[]{i,memory1,memory2};
    }

    public char[][] rotateTheBox(char[][] box) {
        char[][] ans = new char[box[0].length][box.length];
        for(int i=0;i<box.length;i++){
            for(int j=box[0].length-2;j>=0;j--){
                if(box[i][j]=='#'){
                    int k=j+1;
                    while(k<box[i].length&&box[i][k]=='.'){
                        box[i][k] = '#';
                        box[i][k-1] = '.';
                        k++;
                    }
                }
            }
        }
        for(int i=0;i<box.length;i++){
            for(int j=0;j<box[i].length;j++){
                ans[box[i].length-j-1][i] = box[i][j];
            }
        }
        return ans;
    }

    public static int sumOfFlooredPairs(int[] nums) {
        int mod = (int)1e9+7;
        Arrays.sort(nums);
        int ans = 0;
        for(int i=nums.length-1;i>0;i--){
            if(nums[i]==nums[i-1]){
                ans+=2;
            }else{
                for(int j=i;j>=0;j--){
                    int num = nums[i]/nums[j];
                    ans = (ans+num)%mod;
                }
            }
        }
        ans+=nums.length;
        return ans;
    }


    public int subsetXORSum(int[] nums) {
        List<Integer> result = new ArrayList<>();
        Arrays.sort(nums);
        dfs(0,nums,result,0);
        return result.stream().mapToInt(x->x).sum();
    }

    private void dfs(int cur, int[] nums, List<Integer> result, int sum){
        if (cur == nums.length) {
            result.add(sum);
            return;
        }
        sum^=nums[cur];
        dfs(cur + 1, nums,result,sum);
        sum^=nums[cur];
        dfs(cur + 1, nums,result,sum);
    }

    public static int minSwaps(String s) {
        char[] chars = s.toCharArray();
        int count=0;
        int odd = 0;
        int even = 0;
        for(int i=0;i<chars.length;i++){
            if(chars[i]=='1'){
                if(i%2==0)even++;
                else odd++;
                count++;
            }else{
                count--;
            }
        }
        if(count>1||count<-1)return -1;
        if(chars.length%2==1){
            if(even+odd==chars.length/2+1)return odd;
            return even;
        }
        return Math.min(odd,even);
    }

    public static boolean canReach(String s, int minJump, int maxJump) {
        boolean[] dp = new boolean[s.length()];
        Queue<Integer> queue = new LinkedList<>();
        dp[0]=true;
        queue.add(0);
        for(int i=1;i<dp.length;i++){
            if(s.charAt(i)=='0'){
                int start = Math.max(i-maxJump,0);
                int end = i-minJump;
                while(!queue.isEmpty()&&queue.peek()<start){
                    queue.poll();
                }
                if(!queue.isEmpty()&&queue.peek()<=end){
                    dp[i]=true;
                    queue.add(i);
                }
            }
        }
        return dp[s.length()-1];
    }

    public int minSpeedOnTime(int[] dist, double hour) {
        if(hour<=dist.length-1) return -1;
        int max = 0;
        for(int i=0;i<dist.length;i++){
            max = Math.max(max,dist[i]);
        }
        return (int)Math.ceil(Math.max(max,dist[dist.length-1]/(hour-dist.length+1)));
    }

    public int countGoodSubstrings(String s) {
        int ans = 0;
        for(int i=0;i<s.length()-2;i++){
            if(s.charAt(i)!=s.charAt(i+1)&&s.charAt(i)!=s.charAt(i+2)&&s.charAt(i+1)!=s.charAt(i+2)){
                ans++;
            }
        }
        return ans;
    }
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int left=0;
        int right=nums.length-1;
        int ans = 0;
        while(left<right){
            ans = Math.max(ans,nums[left++]+nums[right--]);
        }
        return ans;
    }
    public int[] getBiggestThree(int[][] grid) {
        Queue<Integer> sum = new PriorityQueue<>((x,y)->y-x);
        Set<Integer> set = new HashSet<>();
        int len=0;
        int top=0;
        int bottom = grid.length-1;
        int left=0;
        int right = grid[0].length-1;
        while(left<=right&&top<=bottom){
            for(int i=top;i<=bottom;i++){
                for(int j=left;j<=right;j++){
                    int s=0;
                    if(len==0){
                        s+=grid[i][j];
                    }else{
                        for(int k=0;k<=len;k++){
                            s+=grid[i+k][j+len-k];
                            s+=grid[i+k][j-len+k];
                            s+=grid[i-k][j+len-k];
                            s+=grid[i-k][j-len+k];
                        }
                        s-=grid[i+len][j];
                        s-=grid[i-len][j];
                        s-=grid[i][j-len];
                        s-=grid[i][j+len];
                    }
                    if(!set.contains(s)){
                        sum.add(s);
                        set.add(s);
                    }
                }
            }
            top++;
            bottom--;
            left++;
            right--;
            len++;
        }
        int[] ans;
        if(sum.size()<3){
            ans = new int[sum.size()];
        }else{
            ans = new int[3];
        }
        for(int i=0;i<ans.length;i++){
            ans[i]=sum.poll();
        }
        return ans;
    }
    public int minimumXORSum(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        LinkedList<Integer> list1 =new LinkedList<>();
        LinkedList<Integer> list2 =new LinkedList<>();
        LinkedList<Integer> rest1 =new LinkedList<>();
        LinkedList<Integer> rest2 =new LinkedList<>();
        int i=0;
        int j=0;
        int n=0;
        int ans = 0;
        while(n<32){
            int x = 1<<n;
            while(i<nums1.length&&nums1[i]<x){
                list1.add(nums1[i]);
                i++;
            }
            while(j<nums2.length&&nums2[j]<x){
                list2.add(nums2[j]);
                j++;
            }
            while(!list1.isEmpty()&&!list2.isEmpty()){
                ans += list1.pollFirst()^list2.pollFirst();
            }
            while(!list1.isEmpty()){
                rest1.addLast(list1.pollFirst());
            }
            while(!list2.isEmpty()){
                rest2.addLast(list2.pollFirst());
            }
            n++;
        }
        while(!rest1.isEmpty()&&!rest2.isEmpty()){
            ans += rest1.pollFirst()^rest2.pollFirst();
        }
        return ans;
    }

    public static boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        int len = Math.max(firstWord.length(),secondWord.length());
        int num = 0;
        while(firstWord.length()<len){
            firstWord = 'a'+firstWord;
        }
        while(secondWord.length()<len){
            secondWord = 'a'+secondWord;
        }
        for(int i=0;i<len;i++){
            int add=(firstWord.length()>i?firstWord.charAt(i)-'a':0)+(secondWord.length()>i?secondWord.charAt(i)-'a':0);
            num = num*10+add;
        }
        int target=0;
        for(int i=0;i<targetWord.length();i++){
            target=target*10+targetWord.charAt(i)-'a';
        }
        System.out.println(num);
        System.out.println(target);
        return num==target;
    }

    public String maxValue(String n, int x) {
        int pos = -1;
        if(n.charAt(0)=='-'){
            for(int i=1;i<n.length();i++){
                if(n.charAt(i)-'0'>x){
                    pos = i;
                    break;
                }
            }
            if(pos>0){
                return n.substring(0,pos)+x+n.substring(pos);
            }else{
                return n+x;
            }

        }else{
            for(int i=0;i<n.length();i++){
                if(n.charAt(i)-'0'<x){
                    pos = i;
                    break;
                }
            }
            if(pos>0){
                return n.substring(0,pos)+x+n.substring(pos);
            }else if(pos<0){
                return n+x;
            }else{
                return x+n;
            }
        }
    }
    /*public int[] assignTasks(int[] servers, int[] tasks) {
        Queue<int[]> queue = new PriorityQueue<>((x,y)->x[0]-y[0]);
        for(int i=0;i<servers.length;i++){
            int[] s = new int[2];
            s[0] = servers[i];
            s[1] = i;
            queue.add(s);
        }
        Arrays.sort(s,(x,y)->x[0]-y[0]);
        int[] ans = new int[tasks.length];
        for(int i=0;i<ans.length;i++){
            for(int j=0;j<s.length;j++){
                if(s[j][2]>0) s[j][2]--;
            }
            for(int j=0;j<s.length;j++){
                if(s[j][2]==0) {
                    s[j][2]=tasks[i];
                    ans[i]=s[j][1];
                    break;
                }
            }
        }
        return ans;
    }*/

    public static int[] assignTasks(int[] servers, int[] tasks) {
        Queue<int[]> sq = new PriorityQueue<>((x,y)->{
            if(x[0]==y[0])return x[1]-y[1];
            return x[0]-y[0];
        });
        Queue<int[]> usq = new PriorityQueue<>((x,y)->x[2]-y[2]);
        for(int i=0;i<servers.length;i++){
            sq.add(new int[]{servers[i],i,0});
        }
        int[] ans = new int[tasks.length];
        LinkedList<Integer> list = new LinkedList<>();
        int i=0;
        int r=0;
        while(i<tasks.length){
            while(!usq.isEmpty()&&usq.peek()[2]==i){
                sq.add(usq.poll());
            }
            list.addLast(tasks[i]);
            while(!sq.isEmpty()&&!list.isEmpty()){
                int[] s = sq.poll();
                int t = list.pollFirst();
                s[2]=i+t;
                ans[r++]=s[1];
                usq.add(s);
            }
            i++;
        }
        //超过stack.length的部分独立出来，时间i没必要逐一累加，取执行队列第一个的完成时间即可
        while(!list.isEmpty()){
            i=usq.peek()[2];
            while(!usq.isEmpty()&&usq.peek()[2]==i){
                sq.add(usq.poll());
            }
            while(!sq.isEmpty()&&!list.isEmpty()){
                int[] s = sq.poll();
                int t = list.pollFirst();
                s[2]=i+t;
                ans[r++]=s[1];
                usq.add(s);
            }
        }
        return ans;
    }


    public boolean findRotation(int[][] mat, int[][] target) {
        return findRotation(mat,target,3);
    }

    public boolean findRotation(int[][] mat, int[][] target,int t) {
        int n = mat.length;
        boolean ans = true;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(mat[i][j]!=target[i][j]) {
                    ans = false;
                    break;
                }
            }
        }
        if(!ans&&t>0){
            for(int i=0;i<n/2;i++){
                for(int j=i;j<n-1-i;j++){
                    int temp = mat[i][j];
                    mat[i][j] = mat[n-1-j][i];
                    mat[n-1-j][i] = mat[n-1-i][n-1-j];
                    mat[n-1-i][n-1-j] = mat[j][n-1-i];
                    mat[j][n-1-i] = temp;
                }
            }
            return findRotation(mat,target,--t);
        }
        return ans;
    }



    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);
        int level=0;
        int bottom=nums[0];
        int ans=0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]>bottom){
                level++;
                bottom = nums[i];
            }
            ans+=level;
        }
        return ans;
    }

    /*public int minFlips(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for(int i=0;i<s.length();i++){
            for(int j=i+1;j<s.length();j++){
                if(s.charAt(j)!=s.charAt(j-1)){
                    dp[i][j] = dp[i][j-1];
                }else{
                    if(){

                    }
                }
            }
        }
    }*/

    public int minFlips(String s) {
        /*char[] chars = s.toCharArray();
        int count=0;
        int odd = 0;
        int even = 0;
        for(int i=0;i<chars.length;i++){
            if(chars[i]=='1'){
                if(i%2==0)even++;
                else odd++;
                count++;
            }else{
                count--;
            }
        }
        if(count>1||count<-1)return -1;
        if(chars.length%2==1){
            if(even+odd==chars.length/2+1)return odd;
            return even;
        }
        return Math.min(odd,even);*/

        if(s.length()<2)return 0;
        int ans = count01(s);
        ans = Math.min(ans,count10(s));
        if(s.length()%2==1){
            if(s.charAt(0)=='0'){
                ans = Math.min(ans,count01(s.substring(1))+1);
            }else{
                ans = Math.min(ans,count10(s.substring(1))+1);
            }
        }
        return ans;
    }

    private int count01(String s){
        int count = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)-'0'!=i%2){
                count++;
            }
        }
        return count;
    }
    private int count10(String s){
        int count = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)-'0'==i%2){
                count++;
            }
        }
        return count;
    }
    public boolean isCovered(int[][] ranges, int left, int right) {
        for(int i=left;i<=right;i++){
            boolean flag=false;
            for(int j=0;j<ranges.length;j++){
                if(i>=ranges[j][0]&&i<=ranges[j][1])flag = true;
            }
            if(!flag)return false;
        }
        return true;
    }

    public int chalkReplacer(int[] chalk, int k) {
        long[] sum = new long[chalk.length];
        sum[0]=chalk[0];
        for(int i=1;i<chalk.length;i++){
            sum[i]=sum[i-1]+chalk[i];
        }
        k%=sum[chalk.length-1];
        for(int i=0;i<chalk.length;i++){
            if(k<sum[i])return i;
        }
        return 0;
    }

    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int len = Math.min(m,n);
        long[][] rowSum = new long[m][n+1];
        long[][] colSum = new long[n][m+1];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                rowSum[i][j+1]=rowSum[i][j]+grid[i][j];
                colSum[j][i+1]=colSum[j][i]+grid[i][j];
            }
        }
        while(len>0){
            for(int i=0;i<=m-len;i++){
                for(int j=0;j<=n-len;j++){
                    boolean flag = false;
                    long r = rowSum[i][j+len]-rowSum[i][j];
                    for(int k=i+1;k<i+len;k++){
                        if(r!=rowSum[k][j+len]-rowSum[k][j]) {
                            flag=true;
                            break;
                        }
                    }
                    if(flag)continue;
                    long c = colSum[j][i+len]-colSum[j][i];
                    if(r!=c)continue;
                    for(int k=j+1;k<j+len;k++){
                        if(c!=colSum[k][i+len]-colSum[k][i]){
                            flag=true;
                            break;
                        }
                    }
                    if(flag)continue;
                    if(r!=c)continue;
                    long slash=0;
                    long backSlash=0;
                    for(int l=0;l<len;l++){
                        slash+=grid[i+l][j+l];
                        backSlash+=grid[i+l][j+len-1-l];
                    }
                    if(slash==backSlash&&slash==r)return len;
                }
            }
            len--;
        }
        return 1;
    }

    /*public int minOperationsToFlip(String expression) {

    }*/


    /*public static int maximumRemovals(String s, String p, int[] removable) {
        int[] pcount = new int[26];
        int[] scount = new int[26];
        for(int i=0;i<s.length();i++){
            scount[s.charAt(i)-'a']++;
        }
        for(int i=0;i<p.length();i++){
            pcount[p.charAt(i)-'a']++;
        }
        for(int i=0;i<26;i++){
            if(scount[i]<pcount[i])return 0;
        }
        for(int i=0;i<removable.length;i++){
            int index = s.charAt(removable[i])-'a';
            if(--scount[index]<pcount[index])return i;
        }
        return removable.length;
    }*/


    /*public static int maximumRemovals(String s, String p, int[] removable) {
        int[] pcount = new int[26];
        int[] scount = new int[26];
        for(int i=0;i<s.length();i++){
            scount[s.charAt(i)-'a']++;
        }
        for(int i=0;i<p.length();i++){
            pcount[p.charAt(i)-'a']++;
        }
        for(int i=0;i<26;i++){
            if(scount[i]<pcount[i])return 0;
        }
        for(int i=0;i<removable.length;i++){
            int index = s.charAt(removable[i])-'a';
            if(--scount[index]<pcount[index])return i;
        }
        return removable.length;
    }*/

    public static int maximumRemovals(String s, String p, int[] removable) {
        boolean[] del = new boolean[s.length()];
        int left = 0;
        int right = removable.length-1;
        while(left<=right){
            int mid = (left+right)>>1;
            Arrays.fill(del,false);
            for(int i=0;i<=mid;i++){
                del[removable[i]]=true;
            }
            int k = 0;
            for(int j=0;j<s.length();j++){
                if(del[j])continue;
                if(s.charAt(j)==p.charAt(k))k++;
                if(k==p.length())break;
            }
            if(k==p.length()){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return left;
    }


    public String largestOddNumber(String num) {
        int i=num.length()-1;
        while(i>=0){
            if((num.charAt(i)-'0')%2==1)break;
            i--;
        }
        if(i<0){
            return "";
        }
        int j=i;
        while(j>=0&&num.charAt(j)>='0'&&num.charAt(j)<='9')j--;
        return num.substring(j+1,i+1);
    }

    public int numberOfRounds(String startTime, String finishTime) {
        String[] start = startTime.split(":");
        int startHour = Integer.parseInt(start[0]);
        int startMin = Integer.parseInt(start[1]);
        String[] end = finishTime.split(":");
        int endHour = Integer.parseInt(end[0]);
        int endMin = Integer.parseInt(end[1]);
        if(startMin%15!=0){
            if(startMin<15)startMin=15;
            else if(startMin<30)startMin=30;
            else if(startMin<45)startMin=45;
            else {
                startMin=0;
                startHour++;
            }
        }
        if(endMin%15!=0){
            if(endMin>45)endMin=45;
            else if(endMin>30)endMin=30;
            else if(endMin>15)endMin=15;
            else {
                endMin=0;
            }
        }
        System.out.println(startHour+":"+startMin+","+endHour+":"+endMin);
        if(endHour<startHour||(endHour==startHour&&endMin<startMin)){endHour+=24;}
        int rounds = ((endHour-startHour)*60+endMin-startMin)/15;
        return rounds>0?rounds:0;
    }



    public int countSubIslands(int[][] grid1, int[][] grid2) {
        if (grid1 == null || grid1.length == 0) {
            return 0;
        }

        int nr = grid1.length;
        int nc = grid1[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid1[r][c] == 1&&grid2[r][c]==1) {
                    if(dfs(grid2,grid1, r, c))++num_islands;
                }
            }
        }

        return num_islands;
    }
    boolean dfs(int[][] grid,int[][] grid2, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;
        boolean ans = true;
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == 0) {
            return true;
        }
        if(grid2[r][c]==0)ans = false;
        grid[r][c] = 0;
        ans&=dfs(grid,grid2, r - 1, c);
        ans&=dfs(grid,grid2, r + 1, c);
        ans&=dfs(grid,grid2, r, c - 1);
        ans&=dfs(grid,grid2, r, c + 1);
        return ans;
    }



    public long wonderfulSubstrings(String word) {
        int[][]dp = new int[word.length()+1][11];
        dp[1][word.charAt(0)-'a'] = 1;
        int sum = 1;
        for(int i=2;i<=word.length();i++){
            int j = word.charAt(i-1)-'a';
            dp[i][j] = dp[i-1][10]+1;
            sum+=dp[i][j];
            dp[i][10] = dp[i-1][j];
            sum+=dp[i][10];
        }
        return sum;
    }

    public static int eliminateMaximum(int[] dist, int[] speed) {
        int[] count = new int[(int)1e5+1];
        for(int i=0;i<dist.length;i++){
            int time = (int)Math.ceil((double)dist[i]/speed[i]);
            if(time==0)return 0;
            count[time]++;
        }
        int sum = 0;
        for(int i=1;i<count.length;i++){
            System.out.println("count:"+count[i]+":"+sum);
            sum += count[i];
            if(sum>i)return i;
        }
        return dist.length;
    }

    public int countGoodNumbers(long n) {
        int mod = (int)1e9+7;
        return (int)(mod(5,(n+1)/2,mod)*mod(4,(n)/2,mod)%mod);
    }

    private long mod(long a,long b,int m){
        long result = 1;
        long base = a;
        while(b>0){
            if((b & 1)==1){
                result = (result*base) % m;
            }
            base = (base*base) %m;
            b>>>=1;
        }
        return result;
    }

   /* public static void main(String[] args) {
        //System.out.println(maximumRemovals("abcacb","ab",new int[]{3,1,0}));
        //System.out.println(maximumRemovals("qlevcvgzfpryiqlwy","qlecfqlw",new int[]{12,5}));
        //System.out.println(sumOfFlooredPairs(new int[]{2,5,9}));
        //System.out.println(minSwaps("00011110110110000000000110110101011101111011111101010010010000000000000001101101010010001011110000001101111111110000110101101101001011000011111011101101100110011111110001100110001110000000001100010111110100111001001111100001000110101111010011001"));
        //System.out.println(canReach("000000000000",1,100));
        *//*System.out.println(isSumEqual("h",
                "fhjfdghj",
                "fhjfdgig"));*//*
       *//* int[] s = new int[]{338,890,301,532,284,930,426,616,919,267,571,140,716,859,980,469,628,490,195,664,925,652,503,301,917,563,82,947,910,451,366,190,253,516,503,721,889,964,506,914,986,718,520,328,341,765,922,139,911,578,86,435,824,321,942,215,147,985,619,865};
        int[] t = new int[]{773,537,46,317,233,34,712,625,336,221,145,227,194,693,981,861,317,308,400,2,391,12,626,265,710,792,620,416,267,611,875,361,494,128,133,157,638,632,2,158,428,284,847,431,94,782,888,44,117,489,222,932,494,948,405,44,185,587,738,164,356,783,276,547,605,609,930,847,39,579,768,59,976,790,612,196,865,149,975,28,653,417,539,131,220,325,252,160,761,226,629,317,185,42,713,142,130,695,944,40,700,122,992,33,30,136,773,124,203,384,910,214,536,767,859,478,96,172,398,146,713,80,235,176,876,983,363,646,166,928,232,699,504,612,918,406,42,931,647,795,139,933,746,51,63,359,303,752,799,836,50,854,161,87,346,507,468,651,32,717,279,139,851,178,934,233,876,797,701,505,878,731,468,884,87,921,782,788,803,994,67,905,309,2,85,200,368,672,995,128,734,157,157,814,327,31,556,394,47,53,755,721,159,843};
        System.out.println(Arrays.toString(assignTasks(s,t)));*//*
        //System.out.println(eliminateMaximum(new int[]{1,3,4},new int[]{1,1,1}));
        System.out.println(Math.log(Long.MAX_VALUE)/Math.log(5));
        System.out.println(Math.log(Long.MAX_VALUE)/Math.log(4));
    }*/


    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int ans = 0;
        for(int i=0;i<seats.length;i++){
            ans += Math.abs(seats[i]-students[i]);
        }
        return ans;
    }

    public boolean winnerOfGame(String colors) {
        char[] chars = colors.toCharArray();
        int countA = 0;
        int countB = 0;
        for(int i=1;i<chars.length-1;i++){
            if(chars[i-1]!=chars[i]||chars[i]!=chars[i+1])continue;
            if(chars[i]=='A')countA++;
            else countB++;
        }
        return countA>countB;
    }

    public static int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        List<Integer>[] edgesList = new List[n];
        for(int i=0;i<n;i++){
            edgesList[i] = new LinkedList<>();
        }
        for(int[] e:edges){
            edgesList[e[0]].add(e[1]);
            edgesList[e[1]].add(e[0]);
        }
        int[] distance = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int dis = 0;
        Set<Integer> visited = new HashSet<>();
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int s = queue.poll();
                if(visited.contains(s))continue;
                visited.add(s);
                distance[s] = dis;
                for(int j:edgesList[s]){
                    queue.offer(j);
                }
            }
            dis++;
        }
        int ans = 0;
        for(int i=1;i<n;i++){
            int cost = distance[i]*2;
            if(cost<=patience[i])ans=Math.max(ans,cost+1);
            else{
                int repeat = cost/patience[i];
                if(cost%patience[i]==0)repeat--;
                int lastTime = repeat*patience[i];
                ans = Math.max(ans,cost+lastTime+1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(networkBecomesIdle(new int[][]{{5,7},{15,18},{12,6},{5,1},{11,17},{3,9},{6,11},{14,7},{19,13},{13,3},{4,12},{9,15},{2,10},{18,4},{5,14},{17,5},{16,2},{7,1},{0,16},{10,19},{1,8}},
new int[]{0,2,1,1,1,2,2,2,2,1,1,1,2,1,1,1,1,2,1,1}));
    }

}
