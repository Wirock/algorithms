package leetcode;

import java.util.*;

/**
 * @author chenzw
 * @date 2021/3/20
 */
public class SolutionTest {

    /*public static boolean areSentencesSimilar(String sentence1, String sentence2) {
        String max = sentence1.length()>sentence2.length()?sentence1:sentence2;
        String min = sentence1.length()>sentence2.length()?sentence2:sentence1;
        String[] s1 = max.split(" ");
        String[] s2 = min.split(" ");
        int left1=0;
        int right1=s1.length-1;
        int left2=0;
        int right2=s2.length-1;
        int count=0;
        while(left1<=right1&&left2<=right2&&s2[left2].equals(s1[left1])){
            left1++;
            left2++;
        }
        while(left1<=right1&&left2<=right2&&s2[right2].equals(s1[right1])) {
            right1--;
            right2--;
        }
        if(left2>right2){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(areSentencesSimilar("My name is Haley","My Haley"));
        System.out.println(areSentencesSimilar("of", "A lot of words"));
        System.out.println(areSentencesSimilar("Eating right now","Eating"));
        System.out.println(areSentencesSimilar("Luky","Luccky"));
        System.out.println(areSentencesSimilar("CwFfRo regR","CwFfRo H regR"));
        System.out.println(areSentencesSimilar("S a C PMgzqylRCITUX A jF f LD","S A jF f LD"));
    }*/


    /*public static int countNicePairs(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int count=0;
        int mod=(int)1e9+7;
        for(int i=0;i<nums.length;i++){
            Integer diff = nums[i]-rev(nums[i]);
            int cur = map.getOrDefault(diff,0);
            count=(count+cur)%mod;
            map.put(diff,cur+1);
        }
        return count;
    }

    private static int rev(int num){
        int result=0;
        while(num>0){
            result=result*10+num%10;
            num/=10;
        }
        return result;
    }*/

    /*public String truncateSentence(String s, int k) {
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<k;i++){
            sb.append(strs[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }*/

    /*public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Integer,Integer> countMap = new HashMap<>();
        int[] answer = new int[k];
        for(int i=0;i<logs.length;i++){
            Set<Integer> set = map.get(logs[i][0]);
            if(set==null){
                set=new HashSet<>();
                map.put(logs[i][0],set);
            }
            if(!set.contains(logs[i][1])){
                set.add(logs[i][1]);
                countMap.put(logs[i][0],countMap.getOrDefault(logs[i][0],0)+1);
            }
        }
        for(Integer value:countMap.values()){
            if(value<k){
                answer[value-1]++;
            }
        }
        return answer;
    }*/

    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int[] diffAbs = new int[nums1.length];
        for(int i=0;i<nums1.length;i++){
            diffAbs[i] = Math.abs(nums1[i]-nums2[i]);
        }
        return 0;
    }

    public static int countDifferentSubsequenceGCDs(int[] nums) {
        Map<String,Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums);
        dfs(true,nums,0,new StringBuilder(),set,map);
        return set.size();
    }

    private static void dfs(boolean choosePre,int[] nums,int index,StringBuilder sb,Set<Integer> set,Map<String,Integer> map){
        if(index==nums.length){
            return;
        }
        dfs(false,nums,index+1,sb,set,map);
        if(!choosePre&&nums[index]==nums[index-1]){
            return;
        }
        if("".equals(sb.toString())){
            sb.append(","+nums[index]);
            map.put(sb.toString(),nums[index]);
            set.add(nums[index]);
        }else{
            int gcd = gcd(map.get(sb.toString()), nums[index]);
            sb.append(","+nums[index]);
            map.put(sb.toString(),gcd);
            set.add(gcd);
        }
        dfs(true,nums,index+1,sb,set,map);
        sb.delete(sb.lastIndexOf(","),sb.length());
    }
    private static int gcd(int x,int y){
        int max = x>y?x:y;
        int min = x>y?y:x;
        int r = max%min;
        if(r==0)return min;
        return gcd(min,r);
    }

    public int purchasePlans(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;
        int left = 0;
        int right = nums.length-1;
        while(left<right){
            if(nums[left]+nums[right]<=target){
                count+=right-left;
                left++;
            }else{
                right--;
            }
        }
        return count%1000000007;
    }

    public static int orchestraLayout(int num, int xPos, int yPos) {
        long n = 1;
        long margin = Math.min(xPos,yPos);
        long nums = num;
        margin = Math.min(margin,nums-1-xPos);
        margin = Math.min(margin,nums-1-yPos);
        /*for(int i=0;i<margin;i++){
            n = (n+4*(nums-1))%9;
            nums -= 2;
        }*/
        n+=(((nums+nums-2*margin)%9)*(2*margin)%9)%9;
        nums = nums-2*margin;
        xPos-=margin;
        yPos-=margin;
        if(xPos==0){
            n=(n+yPos)%9;
        }else if(yPos==0){
            n=(n+4*(nums-1)-xPos)%9;
        }else if(xPos==nums-1){
            n=(n+3*(nums-1)-yPos)%9;
        }else if(yPos==nums-1){
            n=(n+nums+xPos-1)%9;
        }
        return (int)(n==0?9:n);
    }

    /*public static int arraySign(int[] nums) {
        int product = 1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                return 0;
            }
            product*=nums[i]>0?1:-1;
        }
        return product;
    }*/

    /*public static int findTheWinner(int n, int k) {
        LinkedList<Integer> list = new LinkedList();
        for(int i=1;i<=n;i++){
            list.addLast(i);
        }
        int index = 1;
        while(list.size()>1){
            int count=k%list.size();
            if(count==0){
                count=list.size();
            }
            index+=count-1;
            index%=list.size();
            if(index==0){
                index=list.size();
            }
            list.remove(index-1);
        }
        return list.peekFirst();
    }*/
    public static int minSideJumps(int[] obstacles) {
        return  minSideJumps(obstacles,0,2);
    }

    public static int minSideJumps(int[] obstacles,int i,int row) {
        if(i<obstacles.length-1){
            if(row==obstacles[i+1]){
                if(row==2){
                    if(obstacles[i]==0){
                        return 1+Math.min(minSideJumps(obstacles,i,1),minSideJumps(obstacles,i,3));
                    }else{
                        return 1+minSideJumps(obstacles,i,obstacles[i]==1?3:1);
                    }
                }else{
                    if(obstacles[i]==2){
                        return 1+minSideJumps(obstacles,i,row==1?3:1);
                    }else{
                        return 1+minSideJumps(obstacles,i,2);
                    }
                }
            }else{
                return minSideJumps(obstacles,i+1,row);
            }
        }
        return 0;
    }
    public static void main(String[] args) {
     /*   System.out.println(countDifferentSubsequenceGCDs(new int[]{6,10,3}));
        System.out.println(countDifferentSubsequenceGCDs(new int[]{5,15,40,5,6}));*/
        /*System.out.println(orchestraLayout(4,1,1));
        System.out.println(orchestraLayout(4,1,2));
        System.out.println(orchestraLayout(4,1,3));
        System.out.println(orchestraLayout(4,2,1));
        System.out.println(orchestraLayout(4,2,2));
        System.out.println(orchestraLayout(4,2,3));
        System.out.println(orchestraLayout(4,3,1));
        System.out.println(orchestraLayout(4,3,2));
        System.out.println(orchestraLayout(4,3,3));
        System.out.println(orchestraLayout(5,1,1));
        System.out.println(orchestraLayout(5,1,2));
        System.out.println(orchestraLayout(5,1,3));
        System.out.println(orchestraLayout(5,2,1));
        System.out.println(orchestraLayout(5,2,2));
        System.out.println(orchestraLayout(5,2,3));
        System.out.println(orchestraLayout(5,3,1));
        System.out.println(orchestraLayout(5,3,2));
        System.out.println(orchestraLayout(5,3,3));
        long start = System.currentTimeMillis();
        System.out.println(orchestraLayout((int)1e9,(int)1e5,(int)1e5));
        System.out.println(orchestraLayout((int)1e9,(int)1e5,(int)1e7));
        System.out.println(orchestraLayout((int)1e9,(int)1e3,(int)1e5));
        System.out.println(orchestraLayout((int)1e9,(int)1e5,(int)1e6));
        System.out.println(System.currentTimeMillis()-start);*/
        //System.out.println(arraySign(new int[]{7,36,96,70,85,23,5,18,4,12,89,92,9,30,53,14,96,32,13,43,37,60,75,7,83,68,20,8,-24,-80,-27,-92,-96,-20,-16,-52,-49,-38}));
        /*System.out.println(findTheWinner(5,2));
        System.out.println(findTheWinner(6,5));
        System.out.println(findTheWinner(1,1));
        System.out.println(findTheWinner(1,2));
        System.out.println(findTheWinner(2,3));
        System.out.println(findTheWinner(8,8));
        System.out.println(findTheWinner(5,4));*/
        /*System.out.println(minSideJumps(new int[]{0,1,2,3,0}));
        System.out.println(minSideJumps(new int[]{0,1,1,3,3,0}));
        System.out.println(minSideJumps(new int[]{0,2,1,0,3,0}));*/
        //System.out.println(minSideJumps(new int[]{0,2,2,1,0,3,0,3,0,1,3,1,1,0,1,3,1,1,1,0,2,0,0,3,3,0,3,2,2,0,0,3,3,3,0,0,2,0,0,3,3,0,3,3,0,0,3,1,0,1,0,2,3,1,1,0,3,3,0,3,1,3,0,2,2,0,1,3,0,1,0,3,0,1,3,1,2,2,0,0,3,0,1,3,2,3,2,1,0,3,2,2,0,3,3,0,3,0,0,1,0}));
    }
}
