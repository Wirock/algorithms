package leetcode;

import java.util.*;

/**
 * 726. 原子的数量
 * 给定一个化学式formula（作为字符串），返回每种原子的数量。
 *
 * 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
 *
 * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
 *
 * 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。
 *
 * 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
 *
 * 给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
 *
 * 示例 1:
 *
 * 输入:
 * formula = "H2O"
 * 输出: "H2O"
 * 解释:
 * 原子的数量是 {'H': 2, 'O': 1}。
 * 示例 2:
 *
 * 输入:
 * formula = "Mg(OH)2"
 * 输出: "H2MgO2"
 * 解释:
 * 原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
 * 示例 3:
 *
 * 输入:
 * formula = "K4(ON(SO3)2)2"
 * 输出: "K4N2O14S4"
 * 解释:
 * 原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
 * 注意:
 *
 * 所有原子的第一个字母为大写，剩余字母都是小写。
 * formula的长度在[1, 1000]之间。
 * formula只包含字母、数字和圆括号，并且题目中给定的是合法的化学式。
 * @author chenzw
 * @date 2021/7/5
 */
public class Solution726 {
    public String countOfAtoms(String formula) {
        Deque<String> elem = new LinkedList<>();
        Deque<Integer> count = new LinkedList<>();
        Deque<Integer> temp = new LinkedList<>();
        Map<String,Integer> map = new HashMap<>();
        char[] c = formula.toCharArray();
        int i=0;
        while(i<c.length){
            if(c[i]=='('){
                if(!count.isEmpty()&&count.peek()==-1){
                    int p = count.pop();
                    while((p = count.pop())>0){
                        temp.push(p);
                    }
                    while(!temp.isEmpty())count.push(temp.pop());
                }
                count.push(0);
            }else if(c[i]==')'){
                count.push(-1);
            }else if(c[i]>'0'&&c[i]<='9'){
                int num = c[i]-'0';
                while(i+1<c.length&&c[i+1]>='0'&&c[i+1]<='9'){
                    num = num*10+c[++i]-'0';
                }
                int p = count.pop();
                if(p==-1){
                    while((p = count.pop())>0){
                        temp.push(p*num);
                    }
                    while(!temp.isEmpty())count.push(temp.pop());
                }else{
                    count.push(p*num);
                }
            }else{
                if(!count.isEmpty()&&count.peek()==-1){
                    int p = count.pop();
                    while((p = count.pop())>0){
                        temp.push(p);
                    }
                    while(!temp.isEmpty())count.push(temp.pop());
                }
                StringBuilder sb = new StringBuilder(c[i]+"");
                while(i+1<c.length&&c[i+1]>='a'&&c[i+1]<='z'){
                    sb.append(c[++i]);
                }
                elem.push(sb.toString());
                count.push(1);
            }
            i++;
        }
        while(!elem.isEmpty()){
            String k = elem.pop();
            map.put(k,map.getOrDefault(k,0)+count.pop());
        }
        StringBuilder ans = new StringBuilder();
        List<Map.Entry<String,Integer>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList,(x, y)->x.getKey().compareTo(y.getKey()));
        for(Map.Entry<String,Integer> entry:entryList){
            ans.append(entry.getKey());
            if(entry.getValue()>1)ans.append(entry.getValue());
        }
        return ans.toString();
    }
}
