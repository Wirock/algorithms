package leetcode;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。

 实现 MyHashMap 类：

 MyHashMap() 用空映射初始化对象
 void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。
 int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
 void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
 * @author chenzw
 * @date 2021/3/14
 */
public class Solution706 {
    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
        myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
        System.out.println(myHashMap.get(1));    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
        System.out.println(myHashMap.get(3));    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
        myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
        System.out.println(myHashMap.get(2));    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
        myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
        System.out.println(myHashMap.get(2));    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
    }
}
class MyHashMap {

    private class Entry{
        int key;
        int value;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    int baseSize = 1024;
    LinkedList<Entry>[] data;
    /** Initialize your data structure here. */
    public MyHashMap() {
        data = new LinkedList[baseSize];
        for(int i=0;i<data.length;i++){
            data[i] = new LinkedList();
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        LinkedList<Entry> dataList = data[hash(key)];
        Iterator iterator = dataList.iterator();
        boolean keyExists = false;
        while(iterator.hasNext()){
            Entry e = (Entry) iterator.next();
            if(e.getKey()==key){
                e.setValue(value);
                keyExists = true;
                break;
            }
        }
        if(!keyExists){
            dataList.addLast(new Entry(key,value));
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        LinkedList<Entry> dataList = data[hash(key)];
        Iterator iterator = dataList.iterator();
        while(iterator.hasNext()){
            Entry e = (Entry) iterator.next();
            if(e.getKey()==key){
                return e.getValue();
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        LinkedList<Entry> dataList = data[hash(key)];
        Iterator iterator = dataList.iterator();
        while(iterator.hasNext()){
            Entry e = (Entry) iterator.next();
            if(e.getKey()==key){
                iterator.remove();
            }
        }
    }

    private int hash(int key){
        return Math.abs(key%baseSize);
    }
}