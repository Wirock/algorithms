package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 341. 扁平化嵌套列表迭代器
 * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 *
 * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
 * @author chenzw
 * @date 2021/3/23
 */
public class Solution341 {
    public class NestedIterator implements Iterator<Integer> {
        private List<Integer> list = null;
        private int index = 0;
        public NestedIterator(List<NestedInteger> nestedList) {
            list = new ArrayList<>();
            addInteger(nestedList);
        }
        private void addInteger(List<NestedInteger> nestedList){
            for(int i=0;i<nestedList.size();i++){
                NestedInteger nestedInteger = nestedList.get(i);
                if(nestedInteger.isInteger()){
                    list.add(nestedInteger.getInteger());
                }else{
                    addInteger(nestedInteger.getList());
                }
            }
        }

        @Override
        public Integer next() {
            if(index<list.size()){
                return list.get(index++);
            }else{
                return null;
            }
        }

        @Override
        public boolean hasNext() {
            return index<list.size();
        }
    }


      // This is the interface that allows for creating nested lists.
      // You should not implement it, or speculate about its implementation
     public interface NestedInteger {

          // @return true if this NestedInteger holds a single integer, rather than a nested list.
          public boolean isInteger();

          // @return the single integer that this NestedInteger holds, if it holds a single integer
          // Return null if this NestedInteger holds a nested list
          public Integer getInteger();

          // @return the nested list that this NestedInteger holds, if it holds a nested list
          // Return null if this NestedInteger holds a single integer
          public List<NestedInteger> getList();
      }
}
