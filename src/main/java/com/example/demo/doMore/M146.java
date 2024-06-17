package com.example.demo.doMore;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 146. LRU缓存机制
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */

//解法:使用LinkedHashSet和HashMap 或者直接 LinkedHashMap
public class M146 {
  public static class LRUCache {
    private Map<Integer, Integer> map;
    private Set<Integer> set;
    private int capacity;

    public LRUCache(int capacity) {
      this.capacity = capacity;
      map = new HashMap<>();
      set = new LinkedHashSet<>();
    }

    public int get(int key) {
      if (!map.containsKey(key)) {
        return -1;
      }
      set.remove(key);
      set.add(key);
      return map.get(key);
    }

    public void put(int key, int value) {
      if (map.containsKey(key)) {
        set.remove(key);
      } else if (map.size() == capacity) {
        int first = set.iterator().next();
        set.remove(first);
        map.remove(first);
      }
      map.put(key, value);
      set.add(key);
    }
  }

  public static void main(String[] args) {
    LRUCache lRUCache = new LRUCache(2);
    lRUCache.put(1, 1); // 缓存是 {1=1}
    lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
    lRUCache.get(1);    // 返回 1
    lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
    lRUCache.get(2);    // 返回 -1 (未找到)
    lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
    lRUCache.get(1);    // 返回 -1 (未找到)
    lRUCache.get(3);    // 返回 3
    lRUCache.get(4);    // 返回 4
  }
}
