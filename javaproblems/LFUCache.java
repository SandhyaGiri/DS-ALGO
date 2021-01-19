package javaproblems;

import java.util.*;

// https://leetcode.com/problems/lfu-cache/
public class LFUCache {
    /**
     * Design and implement a data structure for Least Frequently Used (LFU) cache.

        Implement the LFUCache class:

        LFUCache(int capacity) Initializes the object with the capacity of the data structure.
        int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
        void put(int key, int value) Sets or inserts the value if the key is not already present. When the cache
        reaches its capacity, it should invalidate the least frequently used item before inserting a new item.
        For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be evicted.
        Notice that the number of times an item is used is the number of calls to the get and put
        functions for that item since it was inserted. This number is set to zero when the item is removed.

        

        Example 1:

        Input
        ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
        [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
        Output
        [null, null, null, 1, null, -1, 3, null, -1, 3, 4]

        Explanation
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);
        lfu.put(2, 2);
        lfu.get(1);      // return 1
        lfu.put(3, 3);   // evicts key 2
        lfu.get(2);      // return -1 (not found)
        lfu.get(3);      // return 3
        lfu.put(4, 4);   // evicts key 1.
        lfu.get(1);      // return -1 (not found)
        lfu.get(3);      // return 3
        lfu.get(4);      // return 4
        

        Constraints:

        0 <= capacity, key, value <= 104
        At most 10^5 calls will be made to get and put.
     */

    class KeyCountPair implements Comparable<KeyCountPair>{
        int key;
        int accessCount;
        int time;
        KeyCountPair(int key, int accessCount){
            this.key = key;
            this.accessCount = accessCount;
        }
        void setTime(int time){
            this.time = time;
        }
        public int compareTo(KeyCountPair other){
            // ascending order of counts
            if(this.accessCount == other.accessCount){
                // least recently used appears first (tie)
                return this.time - other.time;
            }
            // least frequently used appears first (no tie)
            return this.accessCount - other.accessCount;
        }
        // argument has to be Object for it to be overriden
        public boolean equals(Object other){
            if(other instanceof KeyCountPair){
                return this.key == ((KeyCountPair)other).key;
            }
            return false;
        }
    }
    
    PriorityQueue<KeyCountPair> lruKeys;
    Map<Integer, Integer> kvMap;
    Map<Integer, Integer> kcMap;
    int capacity;
    int size;
    int time;
    public LFUCache(int capacity) {
        lruKeys = new PriorityQueue<>();
        kvMap = new HashMap<>();
        kcMap = new HashMap<>();
        size = 0;
        time = 0;
        this.capacity = capacity;
    }
    
    
    public int get(int key) {
        int value = -1;
        if(kvMap.containsKey(key)){
            value = kvMap.get(key);
            System.out.println("GET:" + key +"," + value);
            // update access count
            incrementAccessCount(key);
        }
        return value;
    }
    
    // Complexity: O(log k) , k is number of keys
    void incrementAccessCount(int key){
        // update minHeap
        if(kcMap.containsKey(key)){
            KeyCountPair kcPair = new KeyCountPair(key, kcMap.get(key));
            lruKeys.remove(kcPair); // O(log |k|)
        }
        
        kcMap.put(key, kcMap.getOrDefault(key, 0) + 1);
        System.out.println("New count:" + key + "," + kcMap.get(key));
        
        KeyCountPair kcPair = new KeyCountPair(key, kcMap.get(key));
        kcPair.setTime(++time);
        lruKeys.add(kcPair); // O(log |k|)
        System.out.println("TOP:" + lruKeys.peek().key + "-" + lruKeys.peek().accessCount);
    }
    
    public void put(int key, int value) {
        if(kvMap.containsKey(key)){
            kvMap.put(key, value);
            incrementAccessCount(key);
        } else {
            if(size == capacity){
                // eviction
                KeyCountPair kcPair = lruKeys.poll(); // removed from heap
                if(kcPair != null){
                    System.out.println("EVICTED:" + kcPair.key + " " + kcPair.accessCount);
                    kcMap.remove(kcPair.key); // remove key from count map
                    kvMap.remove(kcPair.key); // remove key from k,v map
                    kvMap.put(key, value);
                    incrementAccessCount(key);
                }
            } else {
                kvMap.put(key, value);
                incrementAccessCount(key);
                size+=1;
            }
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
