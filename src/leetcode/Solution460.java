package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 460. LFU 缓存
 * 困难
 * 相关标签
 * 相关企业
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 *
 * 实现 LFUCache 类：
 *
 * LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
 * int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
 * void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最久未使用 的键。
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 *
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 *
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 *
 *
 * 示例：
 *
 * 输入：
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * 输出：
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 *
 * 解释：
 * // cnt(x) = 键 x 的使用计数
 * // cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // 返回 1
 *                  // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
 *                  // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // 返回 -1（未找到）
 * lfu.get(3);      // 返回 3
 *                  // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
 *                  // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // 返回 -1（未找到）
 * lfu.get(3);      // 返回 3
 *                  // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // 返回 4
 *                  // cache=[3,4], cnt(4)=2, cnt(3)=3
 *
 *
 * 提示：
 *
 * 1 <= capacity <= 104
 * 0 <= key <= 105
 * 0 <= value <= 109
 * 最多调用 2 * 105 次 get 和 put 方法
 * Created by Chenzw on 2023/9/25 23:04
 */
public class Solution460 {
    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        lfuCache.get(1);
    }
}
class LFUCache {
    Map<Integer, DLinkedNode> keyMap;
    Map<Integer, DLinkedNode> freqMap;
    int capacity;
    int minFreq;

    public LFUCache(int capacity) {
        keyMap = new HashMap<>();
        freqMap = new HashMap<>();
        this.capacity = capacity;
        minFreq = Integer.MAX_VALUE;
    }

    public int get(int key) {
        System.out.println("get "+ key);
        //从keyMap获取节点
        DLinkedNode node = keyMap.get(key);
        if(node == null){
            return -1;
        }
        //更新频率到freqMap，同一频率的节点按时间排序
        removeNode(node);
        node.freq++;
        addNode(node);
        refreshMinFreq();
        return node.val;
    }

    public void put(int key, int value) {
        System.out.println("put "+ key + ":"+value);
        //若key已存在，继承原来的频率并更新频率到freqMap
        DLinkedNode node = keyMap.get(key);
        if(node != null){
            node.val = value;
            removeNode(node);
            node.freq++;
        }else{
            node = new DLinkedNode(key, value, 1);
            keyMap.put(key, node);
        }
        addNode(node);
        //若超过容量，删除使用频率最低最久未使用的节点
        if(keyMap.size()>capacity){
            removeMinFreqNode();
        }
        refreshMinFreq();
    }


    private void removeNode(DLinkedNode node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = null;
        node.next = null;
    }

    private void addNode(DLinkedNode node){
        DLinkedNode head = freqMap.get(node.freq);
        if(head == null){
            head = new DLinkedNode(-1, -1, -1);
            freqMap.put(node.freq, head);
        }
        DLinkedNode pre = head.pre;
        node.next = head;
        head.pre = node;
        if(pre != null) {
            node.pre = pre;
            pre.next = node;
        }else{
            node.pre = head;
            head.next = node;
        }
    }

    private void removeMinFreqNode(){
        DLinkedNode head = freqMap.get(minFreq);
        DLinkedNode node = head.next;
        keyMap.remove(node.key);
        removeNode(node);
    }

    private void refreshMinFreq(){
        DLinkedNode head = freqMap.get(1);
        if(head.next != null && head.next != head){
            minFreq = 1;
            return;
        }
        head = freqMap.get(minFreq);
        if(head.next == null || head.next == head){
            minFreq++;
        }
    }

}
class DLinkedNode{
    int key;
    int val;
    int freq;
    DLinkedNode pre;
    DLinkedNode next;

    public DLinkedNode(int key, int val, int freq){
        this.key = key;
        this.val = val;
        this.freq = freq;
    }
}
