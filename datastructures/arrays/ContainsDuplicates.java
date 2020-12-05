package datastructures.arrays;

import java.util.*;

public class ContainsDuplicates {
    
    // https://leetcode.com/problems/contains-duplicate/
    /**
     * Given an array of integers, find if the array contains any duplicates.

        Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

        Example 1:

        Input: [1,2,3,1]
        Output: true
        Example 2:

        Input: [1,2,3,4]
        Output: false
        Example 3:

        Input: [1,1,1,3,3,4,3,2,4,2]
        Output: true
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        boolean hasDuplicate = false;
        for(int i=1;i<nums.length;i++){
            if(nums[i] == nums[i-1]){
                hasDuplicate = true;
                break;
            }
        }
        return hasDuplicate;
    }

    // https://leetcode.com/problems/contains-duplicate-ii
    /**
     * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

        Example 1:

        Input: nums = [1,2,3,1], k = 3
        Output: true
        Example 2:

        Input: nums = [1,0,1,1], k = 1
        Output: true
        Example 3:

        Input: nums = [1,2,3,1,2,3], k = 2
        Output: false
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> window = new HashSet<>(); // hashset over list to have better time complexity
        boolean dupFound = false;
        for(int i=0;i<nums.length;i++){
            if(window.contains(nums[i])){
                dupFound = true;
                break;
            }
            window.add(nums[i]);
            if(i>=k){
                window.remove(nums[i-k]); // O(1) for hashset and O(n) for LL
            }
        }
        return dupFound;
    }

    // https://leetcode.com/problems/contains-duplicate-iii/
    /**
     * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

        Example 1:

        Input: nums = [1,2,3,1], k = 3, t = 0
        Output: true
        Example 2:

        Input: nums = [1,0,1,1], k = 1, t = 2
        Output: true
        Example 3:

        Input: nums = [1,5,9,1,5,9], k = 2, t = 3
        Output: false

        Idea: If we have k elements (takes care of index) in a sorted list, then for a given ele, we need to check if ele+t
        can be inserted into this sorted list if so then all elements below it satisfy that some ele with a diff of t exists.
        we use sorted set to store k elements (as duplicates are not needed), and we can easily find the insert_position using
        floor and ceil operations (instead of using bsearch on list).

     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> sortedSet = new TreeSet<>();
        boolean elementFound = false;
        for(int i=0;i<n;i++){
            // with k elements on sortedset, find a,b with atmost diff t
            long ele = nums[i];
            Long floor = sortedSet.floor(Long.valueOf(ele+t));
            Long ceil = sortedSet.ceiling(Long.valueOf(ele-t));
            if((floor != null && Math.abs(floor-ele) <=t) || (ceil != null && Math.abs(ceil-ele) <=t)){
                elementFound = true;
                break;
            }
            
            sortedSet.add(ele);
            if(i>=k){
                sortedSet.remove(1L * nums[i - k]);
            }
        }
        return elementFound;
    }
}