package javaproblems;

import java.util.*;

// https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed

/**
 * Implement the RandomizedCollection class:

RandomizedCollection() Initializes the RandomizedCollection object.
bool insert(int val) Inserts an item val into the multiset if not present. Returns true if the item was not present, false otherwise.
bool remove(int val) Removes an item val from the multiset if present. Returns true if the item was present, false otherwise. Note that if val has multiple occurrences in the multiset, we only remove one of them.
int getRandom() Returns a random element from the current multiset of elements (it's guaranteed that at least one element exists when this method is called). The probability of each element being returned is linearly related to the number of same values the multiset contains.
You must implement the functions of the class such that each function works in average O(1) time complexity.

 

Example 1:

Input
["RandomizedCollection", "insert", "insert", "insert", "getRandom", "remove", "getRandom"]
[[], [1], [1], [2], [], [1], []]
Output
[null, true, false, true, 2, true, 1]

Explanation
RandomizedCollection randomizedCollection = new RandomizedCollection();
randomizedCollection.insert(1);   // return True. Inserts 1 to the collection. Returns true as the collection did not contain 1.
randomizedCollection.insert(1);   // return False. Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
randomizedCollection.insert(2);   // return True. Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
randomizedCollection.getRandom(); // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
randomizedCollection.remove(1);   // return True. Removes 1 from the collection, returns true. Collection now contains [1,2].
randomizedCollection.getRandom(); // getRandom should return 1 and 2 both equally likely.
 

Constraints:

-231 <= val <= 231 - 1
At most 2 * 105  calls will be made to insert, remove, and getRandom.
There will be at least one element in the data structure when getRandom is called.
 */
public class RandomizedCollection {

    Map<Integer, Integer> occurences;
    ArrayList<Integer> numbers;
    int count=0;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        occurences = new HashMap<>();
        numbers = new ArrayList<>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean existed = false;
        if(occurences.get(val) == null){
            existed = true;
            occurences.put(val, 1);
        } else {
            occurences.put(val, occurences.get(val) +1);
        }
        numbers.add(val);
        count++;
        return existed;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        boolean existed = false;
        if(occurences.get(val) != null){
            existed = true;
            int currOccurences = occurences.get(val);
            if(currOccurences > 1){
                occurences.put(val, occurences.get(val) -1);
            } else {
                occurences.remove(val);
            }
            numbers.remove(Integer.valueOf(val)); // removes the value not the val at this index
            count--;
        }
        return existed;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        int randomIndex = new Random().nextInt(count);
        return numbers.get(randomIndex);
        // tried this too - without maintaining the arraylist, only with the hashmap
        // find the bucket in which the randomIndex lies and return its key -> somehow some number is less probable and was rejected.

        //int sumOccurenceCounts = 0;
        //for(int key: occurences.keySet()){
        //    int val = occurences.get(key);
        //    if(sumOccurenceCounts + val >= randomIndex){
        //        return key;
        //    }
        //    sumOccurenceCounts += val;
        //}
        //return -1;
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */