package leetcode;

import java.util.*;

/**
 * 127. 单词接龙
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
 *
 * 序列中第一个单词是 beginWord 。
 * 序列中最后一个单词是 endWord 。
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典 wordList 中的单词。
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0。
 *
 *
 * 示例 1：
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * 示例 2：
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 *
 *
 * 提示：
 *
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有字符串 互不相同
 * @author chenzw
 * @date 2021/6/23
 */
public class Solution127 {
    //bfs
    /*public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordList.add(0,beginWord);
        int n = wordList.size();
        int end = -1;
        int[][] adj = new int[n][n];
        for(int i=0;i<n;i++){
            if(endWord.equals(wordList.get(i)))end=i;
            for(int j=0;j<n;j++){
                if(i!=j)adj[i][j]=distance(wordList.get(i),wordList.get(j));
            }
        }
        if(end==-1)return 0;
        Queue<int[]> queue = new LinkedList<>();
        for(int i=1;i<n;i++){
            if(adj[0][i]>0){
                queue.add(new int[]{0,i});
            }
        }
        bfs(adj,queue,end);
        return adj[0][end]>0?adj[0][end]+1:0;
    }

    private void bfs(int[][] adj,Queue<int[]> queue,int end){
        if(queue.isEmpty())return;
        int[] index = queue.poll();
        int r = index[0];
        int c = index[1];
        if(adj[r][c]>0){
            int distance = adj[0][r]+adj[r][c];
            if(adj[0][c]==0||distance<=adj[0][c]){
                adj[0][c] = distance;
                if(c==end)return;
                for(int i=1;i<adj.length;i++){
                    if(adj[c][i]>0){
                        queue.add(new int[]{c,i});
                    }
                }
            }
        }
        bfs(adj,queue,end);
    }

    private int distance(String s1,String s2){
        if(s1.length()!=s2.length())return 0;
        int count = 0;
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                count++;
                if(count>1)return 0;
            }
        }
        return 1;
    }*/


    Map<String, Integer> wordId = new HashMap<>();
    List<List<Integer>> edge = new ArrayList<>();
    int nodeNum = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }
        int[] dis = new int[nodeNum];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
        dis[beginId] = 0;

        Queue<Integer> que = new LinkedList<>();
        que.offer(beginId);
        while (!que.isEmpty()) {
            int x = que.poll();
            if (x == endId) {
                return dis[endId] / 2 + 1;
            }
            for (int it : edge.get(x)) {
                if (dis[it] == Integer.MAX_VALUE) {
                    dis[it] = dis[x] + 1;
                    que.offer(it);
                }
            }
        }
        return 0;
    }


    //利用*来模糊代替26个字母，降低构建邻接表的时间复杂度
    public void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }


    public void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<>());
        }
    }


    public static void main(String[] args) {
        System.out.println(new Solution127().ladderLength("hit","cog", new ArrayList<>(Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"}))));
    }

}
