package datastructures.arrays;

import java.util.*;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 * 
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Return the max sliding window.

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
    // Time: O(N Log K) -> will fail for a very large K
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
                    // priorityQueue remove -> removes only one instance of the given value even if multiple values are present.
                    // This is important because there could be duplicates within the k elements inside the heap and
                    // remove shouldn't delete all of them.
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

    /**
     * Instead of maintaining all elements within the sliding window, only maintain
     * elements which are larger than the current element.
     * 
     * Use deque, with queue size lesser than the window size. Remove redundant elements and the queue should
     * store only elements that need to be considered.

        Time: O(N)
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindowFast(int[] nums, int k) {
        int n = nums.length;
        LinkedList<Integer> deque = new LinkedList<>(); // has indices only
        int[] result = new int[n-k+1];
        for(int i=0;i<n;i++){
            // remove elements outside the window
            while(deque.size()> 0 && deque.peekFirst() <= i-k){
                deque.pollFirst();
            }
            // remove elements < nums[i], i.e those numbers who can never be a max result
            while(deque.size()> 0 && nums[deque.peekLast()] < nums[i] ){
                deque.pollLast();
            }
            // insert curr ele
            deque.addLast(i);
            // add a new result (greatest ele index is at head of the queue)
            if(i >= k-1){
                result[i-k+1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
}