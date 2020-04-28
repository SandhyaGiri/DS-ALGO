package javaproblems;

import java.util.*;

/**
 * https://leetcode.com/problems/lru-cache/
 */
public class LRUCache {
    LinkedList<Integer> recentKeyQueue;
    int currSize, capacity;
    Map<Integer,Integer> kvMap;
    public LRUCache(int capacity) {
        recentKeyQueue = new LinkedList<Integer>();
        kvMap = new HashMap<Integer,Integer>();
        this.capacity = capacity;
        currSize = 0;
    }
    
    public int get(int key) {
        Integer val = kvMap.get(key);
        if(val != null){
            moveToLatest(key);
            return val;
        }
        return -1;
    }
    void moveToLatest(int key){
        recentKeyQueue.remove(new Integer(key));
        recentKeyQueue.addLast(key);
    }
    public void put(int key, int value) {
        if(kvMap.get(key) != null){
            kvMap.put(key, value);
            moveToLatest(key);
        } else {
            if(currSize == capacity){
                // must evict
                int keyToBeEvicted = recentKeyQueue.pollFirst();
                kvMap.remove(keyToBeEvicted);
                kvMap.put(key, value);
                recentKeyQueue.addLast(key);
            } else {
                kvMap.put(key, value);
                currSize += 1;
                recentKeyQueue.addLast(key);
            }
        }
    }
}