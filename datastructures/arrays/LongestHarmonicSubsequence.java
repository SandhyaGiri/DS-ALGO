package datastructures.arrays;

import java.util.*;

// https://leetcode.com/problems/longest-harmonious-subsequence/
public class LongestHarmonicSubsequence {
    
    /**
     * We define a harmonious array as an array where the difference between its maximum value and its minimum value is exactly 1.

        Given an integer array nums, return the length of its longest harmonious subsequence among all its possible subsequences.

        A subsequence of array is a sequence that can be derived from the array by deleting some or no elements without changing the order of the remaining elements.

        

        Example 1:

        Input: nums = [1,3,2,2,5,2,3,7]
        Output: 5
        Explanation: The longest harmonious subsequence is [3,2,2,2,3].
        Example 2:

        Input: nums = [1,2,3,4]
        Output: 2
        Example 3:

        Input: nums = [1,1,1,1]
        Output: 0

        Idea: Since max-min==1, only numbers in the subsequence are a and a+1, so after sorting we can easily finding subarrays
        which have only two different elements and they differ by 1.
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        Integer first =null, second=null;
        int maxHarmonicSeqLen = 0;
        int firstStart = 0, secondStart = 0;
        int i=0;
        for(;i<nums.length;i++){
            if(first == null){
                first = nums[i];
                firstStart = i;
            } else if(second == null && nums[i] != first){
                if(nums[i] == first +1){
                    // can extend
                    second = nums[i];
                    secondStart = i;
                } else {
                    first = nums[i];
                    firstStart = i;
                }
            } else if(nums[i] != first && nums[i] != second){
                // new number, shift second to first;s position
                maxHarmonicSeqLen = Math.max(maxHarmonicSeqLen, i-firstStart);
                if(nums[i] == second+1){ // valid new extension
                    first = second;
                    firstStart = secondStart;
                    second = nums[i];
                    secondStart = i;   
                } else {
                    first = nums[i];
                    firstStart = i;
                    second = null;
                }
            }
        }
        // for sequences that end at the end
        return first != null && second != null ? Math.max(maxHarmonicSeqLen, i-firstStart): maxHarmonicSeqLen;
    }
}
