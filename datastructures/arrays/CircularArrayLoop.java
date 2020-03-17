package datastructures.arrays;

import java.util.*;

/**
 * https://leetcode.com/problems/circular-array-loop/
 */
public class CircularArrayLoop {
    // detects any cycle starting from the current vertex v, direction of movement is specified using isForward
    // Time: O(N) , Space: O(N) - for holding the path nodes in a set
    public boolean checkCycle(int v, int[] nums, boolean isForward) {
        int i = v;
        boolean cycleFound = false;
        Set<Integer> pathNodes = new HashSet<Integer>();
        while((nums[i] > 0 && isForward) || (nums[i] < 0 && !isForward)) {
            int nextIndex = (i+nums[i])%nums.length;
            if(nextIndex < 0) {
                nextIndex = nums.length + nextIndex;
            }
            pathNodes.add(i);
            nums[i] = 0; // mark node as visited
            if(nextIndex != i && pathNodes.contains(nextIndex)) {
                // found a back edge to one of the nodes in current path
                cycleFound = true;
                break;
            }
            i = nextIndex;
        }
        return cycleFound;
    }

    /**
     * You are given a circular array nums of positive and negative integers. If a number k at an index is positive, then move forward k steps. Conversely, if it's negative (-k), move backward k steps. Since the array is circular, you may assume that the last element's next element is the first element, and the first element's previous element is the last element.

        Determine if there is a loop (or a cycle) in nums. A cycle must start and end at the same index and the cycle's length > 1. Furthermore, movements in a cycle must all follow a single direction. In other words, a cycle must not consist of both forward and backward movements.

        

        Example 1:

        Input: [2,-1,1,2,2]
        Output: true
        Explanation: There is a cycle, from index 0 -> 2 -> 3 -> 0. The cycle's length is 3.
        Example 2:

        Input: [-1,2]
        Output: false
        Explanation: The movement from index 1 -> 1 -> 1 ... is not a cycle, because the cycle's length is 1. By definition the cycle's length must be greater than 1.
        Example 3:

        Input: [-2,1,-1,-2,-2]
        Output: false
        Explanation: The movement from index 1 -> 2 -> 1 -> ... is not a cycle, because movement from index 1 -> 2 is a forward movement, but movement from index 2 -> 1 is a backward movement. All movements in a cycle must follow a single direction.
        

        Note:

        -1000 ≤ nums[i] ≤ 1000
        nums[i] ≠ 0
        1 ≤ nums.length ≤ 5000
        

        Follow up:

        Could you solve it in O(n) time complexity and O(1) extra space complexity?
     * @param nums
     * @return
     */
    public boolean circularArrayLoop(int[] nums) {
        boolean cycleFound = false;
        for(int i=0;i<nums.length;i++) {
            if(nums[i] != 0 && checkCycle(i, nums, nums[i] > 0)) {
                cycleFound = true;
                break;
            }
        }
        return cycleFound;
    }
}