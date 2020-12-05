package algorithms.sorting;

import java.util.*;

// https://leetcode.com/problems/sort-array-by-increasing-frequency
public class FrequencySort {
    class EntryComparator implements Comparator<Map.Entry<Integer, Integer>>{
        public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2){
            if(entry1.getValue() == entry2.getValue()){
                return -1 * Integer.compare(entry1.getKey(), entry2.getKey()); // decreasing key in case of tie
            } else {
                return Integer.compare(entry1.getValue(), entry2.getValue()); // increasing frequency
            }
        }
    }
    /**
     * Uses same concept as sort a map by value.
     * 
     * @param nums
     * @return
     */
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(countMap.get(nums[i]) == null){
                countMap.put(nums[i], 1);
            } else{
                countMap.put(nums[i], countMap.get(nums[i])+1);
            }
        }
        // sort this map by value
        Set<Map.Entry<Integer, Integer>> entries = countMap.entrySet();
        TreeSet<Map.Entry<Integer, Integer>> sortedEntries = new TreeSet<>(new EntryComparator());
        sortedEntries.addAll(entries);
        // accumulate the result
        int[] result = new int[nums.length];
        int i=0;
        for(Map.Entry<Integer, Integer> entry: sortedEntries){
            int key = entry.getKey();
            int value = entry.getValue();
            while(value > 0){
                result[i++] = key;
                value -=1;
            }
        }
        return result;
    }
}
