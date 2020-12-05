package datastructures.arrays;

import java.util.*;

/**
 * https://leetcode.com/problems/contiguous-array/
 * 
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

 */
public class MaxLengthBinarySubarray {
    public int findMaxLength(int[] nums) {
        int arrSum[] = new int[nums.length];
        int maxIndexSoFar = -1;
        int sum = 0;
        for(int i=0;i<nums.length;i++) {
            sum+= (nums[i] == 1 ? 1 : -1);
            if(sum == 0){
               maxIndexSoFar = Math.max(maxIndexSoFar, i);
            }
            arrSum[i] = sum;
        }
        int maxSubarrLen = maxIndexSoFar+1;
        // for subarrays that don't start at 0
        
        // store a map of index at which the sum occured, in order to calculate the length of the subarray.
        HashMap<Integer,Integer> sums = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            if(sums.get(arrSum[i]) == null){
                sums.put(arrSum[i], i);
            }else{
                maxSubarrLen = Math.max(maxSubarrLen, i-sums.get(arrSum[i]));
            }
        }
        
        // this is O(n*n)
        
        //for(int i=1;i<nums.length;i++){
        //    for(int j=i+1;j<nums.length;j++){
        //        if(arrSum[j] - arrSum[i] == 0){
        //            maxSubarrLen = Math.max(maxSubarrLen, j-i);
        //        }
        //    }
        //}
        
        return maxSubarrLen;
    }
}