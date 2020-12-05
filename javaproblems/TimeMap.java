package javaproblems;

import java.util.*;

// https://leetcode.com/problems/time-based-key-value-store/
public class TimeMap {
    /**
     * Create a timebased key-value store class TimeMap, that supports two operations.

        1. set(string key, string value, int timestamp)

        Stores the key and value, along with the given timestamp.
        2. get(string key, int timestamp)

        Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
        If there are multiple such values, it returns the one with the largest timestamp_prev.
        If there are no values, it returns the empty string ("").
        

        Example 1:

        Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
        Output: [null,null,"bar","bar",null,"bar2","bar2"]
        Explanation:   
        TimeMap kv;   
        kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1   
        kv.get("foo", 1);  // output "bar"   
        kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"   
        kv.set("foo", "bar2", 4);   
        kv.get("foo", 4); // output "bar2"   
        kv.get("foo", 5); //output "bar2"   

        Example 2:

        Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
        Output: [null,null,null,"","high","high","low","low"]

        Idea: Since a key can have many values, we need to have a map with key and list of values. But we need to find value
        with t <= timestamp provided. In order to do this, it is efficient to have values sorted by time and then the problem
        reduces to finding the floor for timestamp and return its corresponding value.
     */
    class TimeValuePair implements Comparable<TimeValuePair>{
        int time;
        String value;
        
        TimeValuePair(int t, String v){
            this.time = t;
            this.value = v;
        }
        
        public int compareTo(TimeValuePair other){
            return Integer.compare(this.time, other.time); // sorted by time asc
        }
    }

    /** Initialize your data structure here. */
    Map<String, TreeSet<TimeValuePair>> data;
    public TimeMap() {
        data = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        TimeValuePair newValue = new TimeValuePair(timestamp, value);
        if(data.containsKey(key)){
            data.get(key).add(newValue);
        } else {
            TreeSet<TimeValuePair> values = new TreeSet<>();
            values.add(newValue);
            data.put(key, values);
        }
    }
    
    public String get(String key, int timestamp) {
        TreeSet<TimeValuePair> values = data.get(key);
        // find floor pair for the given t
        // floor works on objects too, as we just use t for comparison during sorting (see compareTo function in TimeValuePair)
        TimeValuePair closestValue = values.floor(new TimeValuePair(timestamp, null));
        return closestValue != null ? closestValue.value : ""; // return empty string when no floor match found.
    }
}
