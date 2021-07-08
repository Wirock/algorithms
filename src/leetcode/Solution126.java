package leetcode;

import java.util.*;

/**
 * 126. 单词接龙 II
 * 按字典 wordList 完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的 转换序列 是形式上像 beginWord -> s1 -> s2 -> ... -> sk 这样的单词序列，并满足：
 *
 * 每对相邻的单词之间仅有单个字母不同。
 * 转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词。注意，beginWord 不必是字典 wordList 中的单词。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。请你找出并返回所有从 beginWord 到 endWord 的 最短转换序列 ，如果不存在这样的转换序列，返回一个空列表。每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回。
 *
 *
 *
 * 示例 1：
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * 解释：存在 2 种最短的转换序列：
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 * 示例 2：
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：[]
 * 解释：endWord "cog" 不在字典 wordList 中，所以不存在符合要求的转换序列。
 *
 *
 * 提示：
 *
 * 1 <= beginWord.length <= 7
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有单词 互不相同
 * @author chenzw
 * @date 2021/6/23
 */
public class Solution126 {
    //bfs求出最短距离，dfs搜索最短路径
    //关键点是在bfs过程中建立反向的邻接表，记录每个点的前继节点。
    //然后从endWord开始通过邻接表进行dfs，这样从endWord开始的路径终点都会是beginWord,没有多余的分支
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        Map<String,List<String>> edges = new HashMap<>();
        for(int i=0;i<wordList.size();i++){
            edges.put(wordList.get(i),new ArrayList<>());
        }
        if(!edges.containsKey(endWord))return ans;
        if(!edges.containsKey(beginWord)){
            edges.put(beginWord,new ArrayList<>());
            wordList.add(0,beginWord);
        }
        //bfs找出最短距离,反向建立邻接表
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int step = 1;
        boolean done =false;
        while(!done&&!queue.isEmpty()){
            int size = queue.size();
            step++;
            Set<String> levelSet = new HashSet<>();
            while(size-->0){
                String w = queue.poll();
                char[] arr = w.toCharArray();
                for(int j=0;j<arr.length;j++){
                    for(char c='a';c<='z';c++){
                        char temp = arr[j];
                        arr[j] = c;
                        String k = new String(arr);
                        //寻找最短距离应该忽略掉已经访问过的元素
                        //但是，要使最后找到的最短路径方案完整，前继表中不能忽略层级中的相同元素，在这一步中加上
                        if(levelSet.contains(k))edges.get(k).add(w);
                        if(edges.containsKey(k)&&!visited.contains(k)) {
                            edges.get(k).add(w);
                            visited.add(k);
                            levelSet.add(k);
                            if(k.equals(endWord)) {
                                done = true;
                                arr[j] = temp;
                                break;
                            }
                            queue.offer(k);
                        }
                        arr[j] = temp;
                    }
                }
            }
        }
       if(!done)return ans;
        Deque<String> path = new LinkedList<>();
        path.push(endWord);
        //dfs找出所有最短路线
       dfs(ans,path,beginWord,step,edges);
       return ans;
    }

    private void dfs(List<List<String>> ans, Deque<String> path,String beginWord,int size,Map<String,List<String>> edges){
        if(path.size()==size) {
            if(beginWord.equals(path.peek()))ans.add(new ArrayList<>(path));
            return;
        }
        for(String s:edges.get(path.peek())){
            path.push(s);
            dfs(ans,path,beginWord,size,edges);
            path.pop();
        }
    }

    public static void main(String[] args) {
        List<List<String>> ladders;
        ladders = new Solution126().findLadders("hit", "cog", new ArrayList<>(Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log", "cog"})));
        for(List<String> l:ladders){
            System.out.println(Arrays.toString(l.toArray()));
        }
        ladders = new Solution126().findLadders("a", "c", new ArrayList<>(Arrays.asList(new String[]{"a", "b", "c"})));
        for(List<String> l:ladders){
            System.out.println(Arrays.toString(l.toArray()));
        }
        ladders = new Solution126().findLadders("cet", "ism", new ArrayList<>(Arrays.asList(new String[]{"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"})));
        for(List<String> l:ladders){
            System.out.println(Arrays.toString(l.toArray()));
        }
        ladders = new Solution126().findLadders("red", "tax", new ArrayList<>(Arrays.asList(new String[]{"ted","tex","red","tax","tad","den","rex","pee"})));
        for(List<String> l:ladders){
            System.out.println(Arrays.toString(l.toArray()));
        }
    }
}
