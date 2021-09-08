package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 68. 文本左右对齐
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 *
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 *
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 *
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 * 说明:
 *
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 * 示例:
 *
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * 示例 2:
 *
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *      因为最后一行应为左对齐，而不是左右两端对齐。
 *      第二行同样为左对齐，这是因为这行只包含一个单词。
 * 示例 3:
 *
 * 输入:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 *          "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 * @author chenzw
 * @date 2021/6/5
 */
public class Solution68 {
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int[] space = new int[words.length];//space[i]为words[i]后面应填入的空格数量
        int len = words[0].length();
        int begin = 0;
        //计算每一行放入尽可能多的单词的情况下，每个单词后面应插入的空格数
        for(int i=1;i<words.length;i++){
            if(len+1+words[i].length()<=maxWidth){
                len = len+1+words[i].length();
                space[i-1]++;
            }else{
                int remain = maxWidth-len;
                if(begin==i-1)space[begin]=remain;
                else
                while(remain>0){
                    for(int j=begin;j<i-1;j++){
                        if(remain==0)break;
                        space[j]++;
                        remain--;
                    }
                }
                len = words[i].length();
                begin = i;
            }
        }
        if(len<maxWidth)space[words.length-1]=maxWidth-len;//最后一行多出的空格放在右侧
        //将单词和空格填入结果集
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<words.length;i++){
            sb.append(words[i]);
            for(int j =0;j<space[i];j++){
                sb.append(' ');
            }
            if(sb.length()==maxWidth){
                ans.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."},16).toArray()));
        System.out.println(Arrays.toString(fullJustify(new String[]{"What","must","be","acknowledgment","shall","be"},16).toArray()));
        System.out.println(Arrays.toString(fullJustify(new String[]{"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"},20).toArray()));
    }
}
