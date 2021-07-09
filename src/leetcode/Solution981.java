package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 981. 基于时间的键值存储
 * 创建一个基于时间的键值存储类 TimeMap，它支持下面两个操作：
 *
 * 1. set(string key, string value, int timestamp)
 *
 * 存储键 key、值 value，以及给定的时间戳 timestamp。
 * 2. get(string key, int timestamp)
 *
 * 返回先前调用 set(key, value, timestamp_prev) 所存储的值，其中 timestamp_prev <= timestamp。
 * 如果有多个这样的值，则返回对应最大的  timestamp_prev 的那个值。
 * 如果没有值，则返回空字符串（""）。
 *
 *
 * 示例 1：
 *
 * 输入：inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
 * 输出：[null,null,"bar","bar",null,"bar2","bar2"]
 * 解释：
 * TimeMap kv;
 * kv.set("foo", "bar", 1); // 存储键 "foo" 和值 "bar" 以及时间戳 timestamp = 1
 * kv.get("foo", 1);  // 输出 "bar"
 * kv.get("foo", 3); // 输出 "bar" 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即 "bar"）
 * kv.set("foo", "bar2", 4);
 * kv.get("foo", 4); // 输出 "bar2"
 * kv.get("foo", 5); // 输出 "bar2"
 *
 * 示例 2：
 *
 * 输入：inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
 * 输出：[null,null,null,"","high","high","low","low"]
 * @author chenzw
 * @date 2021/7/10
 */
public class Solution981 {
    class TimeMap {

        Map<Integer, Map<String,String>> map;
        Map<String, TreeSet<Integer>> timestampMap;
        /** Initialize your data structure here. */
        public TimeMap() {
            map = new HashMap<>();
            timestampMap = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            map.putIfAbsent(timestamp,new HashMap<>());
            map.get(timestamp).put(key,value);
            timestampMap.putIfAbsent(key,new TreeSet<>());
            timestampMap.get(key).add(timestamp);
        }

        public String get(String key, int timestamp) {
            TreeSet<Integer> set = timestampMap.get(key);
            if(set==null)return "";
            Integer i = set.floor(timestamp);
            Map<String, String> m = map.get(i);
            return m==null?"":m.getOrDefault(key,"");
        }
    }

}
