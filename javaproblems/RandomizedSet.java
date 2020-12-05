package javaproblems;

import java.util.*;
//https://leetcode.com/problems/insert-delete-getrandom-o1/
public class RandomizedSet {
    
    Map<Integer, Integer> indexMap;
    int index =0;
    /** Initialize your data structure here. */
     /**
     * Design a data structure that supports all following operations in average O(1) time.

        insert(val): Inserts an item val to the set if not already present.
        remove(val): Removes an item val from the set if present.
        getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
     * @return
     */
    public RandomizedSet() {
        indexMap = new HashMap<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean present = indexMap.get(val) != null;
        if(!present){
            indexMap.put(val, index);
            index++;
            return true;
        }
        return false;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        boolean present = indexMap.get(val) != null;
        if(present){
            indexMap.remove(val);
        }
        return present;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int size = indexMap.size();
        int index = new Random().nextInt(size);
        List<Integer> numbers = new ArrayList<>(indexMap.keySet()); // probably this is O(1)?
        return numbers.get(index);
    }
}