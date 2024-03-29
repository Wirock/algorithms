package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 211. 添加与搜索单词 - 数据结构设计
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 *
 * 实现词典类 WordDictionary ：
 *
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 *
 *
 * 示例：
 *
 * 输入：
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * 输出：
 * [null,null,null,null,false,true,true,true]
 *
 * 解释：
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 *
 * 提示：
 *
 * 1 <= word.length <= 500
 * addWord 中的 word 由小写英文字母组成
 * search 中的 word 由 '.' 或小写英文字母组成
 * 最多调用 50000 次 addWord 和 search
 * @author chenzw
 * @date 2021/10/19
 */
public class Solution211 {
    class WordDictionary {

        Trie root;

        public WordDictionary() {
            root = new Trie();
        }

        public void addWord(String word) {
            Trie trie = root;
            char[] chars = word.toCharArray();
            for(int i=0;i<chars.length;i++){
                Trie next = trie.next.get(chars[i]);
                if(next == null){
                    next = new Trie();
                    trie.next.put(chars[i],next);
                }
                trie = next;
            }
            trie.isEnd = true;
        }

        public boolean search(String word) {
            Trie trie = root;
            char[] chars = word.toCharArray();
            return search(trie,chars,0,chars.length-1);
        }

        private boolean search(Trie trie,char[] chars,int start,int end){
            for(int i=start;i<=end;i++){
                if(chars[i]=='.'){
                    for(Trie next:trie.next.values()){
                        if(search(next,chars,i+1,end))return true;
                    }
                    return false;
                }else{
                    Trie next = trie.next.get(chars[i]);
                    if(next == null)return false;
                    trie = next;
                }
            }
            return trie.isEnd;
        }
    }

    class Trie {
        boolean isEnd = false;
        Map<Character,Trie> next = new HashMap<>();
    }
}
