package algorithms.sorting;

import java.util.*;

// https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
public class ShortestUnsortedSubarray {
    int bsearchIndex(int[] arr, int target){
        int l=0,r=arr.length-1;
        while(l<=r){
            int mid = l+(r-l)/2;
            if(arr[mid] == target && (mid-1 <0 || arr[mid-1] < target)){
                return mid;
            } else if(arr[mid] < target){
                l++;
            } else {
                r--;
            }
        }
        return l;
    }
    /**
     * Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order.

        Return the shortest such subarray and output its length.

        

        Example 1:

        Input: nums = [2,6,4,8,10,9,15]
        Output: 5
        Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
        Example 2:

        Input: nums = [1,2,3,4]
        Output: 0
        Example 3:

        Input: nums = [1]
        Output: 0


     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        // clone the array and sort it to get the actual sorted array? - O(nlogn)
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        // for each i, find nums[i]'s index in the sorted array using bsearch
        // if its not i, maintain min and max index values to which the elements belong!
        int min= Integer.MAX_VALUE;
        int max= Integer.MIN_VALUE;
        // for duplicate nums, we need to check if their pos is right (as bsearch always returns index of the first occurence)
        Map<Integer, Integer> lastIndexMap = new HashMap<>(); // has index of last occurence of a num in sorted array
        for(int i=0;i<nums.length;i++){
            int newIndex;
            if(lastIndexMap.containsKey(nums[i])){
                newIndex = lastIndexMap.get(nums[i])+1;
            } else {
                newIndex = bsearchIndex(sortedNums, nums[i]);
            }
            lastIndexMap.put(nums[i], newIndex);
            //System.out.println(newIndex + "-" + i);
            if(newIndex != i){
                if(newIndex > i){ // i..newIndex is the window
                    min = Math.min(min, i);
                    max = Math.max(max, newIndex);
                } else { // newIndex..i is the window
                    min = Math.min(min, newIndex);
                    max = Math.max(max, i);
                }
            }
        }
        return max != Integer.MIN_VALUE ? max-min+1 : 0;
    }
}
