package datastructures.arrays;

import java.util.*;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 * 
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Note:
You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if(n>0 && k>0){
            int[] result = new int[n-k+1];
            // put first k elements in a priority queue
            Queue<Integer> maxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
            for(int i=0;i<k;i++){
                maxHeap.add(nums[i]);
            }
            // for remaining n-k elements .peek to get max, remove first element and add new element
            int windowStart =0;
            for(int i=k;i<=n;i++){
                result[i-k]=maxHeap.peek();
                if(i<n){
                    maxHeap.remove(nums[windowStart]);
                    maxHeap.add(nums[i]);
                    windowStart++;   
                }
            } 
            return result;
        }
        int[] emptyArray = {};
        return emptyArray;
    }
}