package problemsolving;

import java.util.*;

public class MinMovesToMakeArrayElementsEqual {
    // https://leetcode.com/problems/minimum-moves-to-equal-array-elements
    /**
     * Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.

        In one move, you can increment n - 1 elements of the array by 1.

        

        Example 1:

        Input: nums = [1,2,3]
        Output: 3
        Explanation: Only three moves are needed (remember each move increments two elements):
        [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
        Example 2:

        Input: nums = [1,1,1]
        Output: 0

        Idea: 
        To make elements equal, it means we need to make the difference between min element and max element in array equal to 0.
        In each move, we can increase all n-1 elements by one. We should never choose to increase our max element, so we choose to increase other elements except our current max element. Instead of that, we can choose to decrease max element by one, so the difference between elements still the same.
        So in each move, we need to decrease the current max element by one to util every elements become min element.
        The problem becomes: count difference between other elements with our min element in the array.
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int minNum = Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            if(nums[i] < minNum){
                minNum = nums[i];
            }
        }
        // calc num moves
        // in each move instead of increaseing n-1 eles (except the max) by 1, we decrese max ele by 1 each time until all numbers are same as min ele
        // problem becomes sum of diff of each ele to the min ele
        int moves = 0;
        for(int i=0;i<nums.length;i++){
            moves += nums[i] - minNum;
        }
        return moves;
    }

    /**
     * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii
     * 
     * Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.

        In one move, you can increment or decrement an element of the array by 1.

        Example 1:

        Input: nums = [1,2,3]
        Output: 2
        Explanation:
        Only two moves are needed (remember each move increments or decrements one element):
        [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
        Example 2:

        Input: nums = [1,10,2,9]
        Output: 16

        Idea: A median ele will balance the moves and lead to a min number of moves.
        Sort the array and find the median of the array. Sum of abs(median - difference) of all elements in the array is the min moves.
     * @param nums
     * @return
     */
    public int minMoves2(int[] nums) {
        // find median element - middle ele in a sorted array
        // so that increments and decrements are balanced as we pick middle element
        Arrays.sort(nums);
        int targetValue = nums[nums.length/2];
        if(nums.length % 2 ==0){
            targetValue += nums[nums.length/2 -1];
            targetValue /= 2;
        } 
        int moves =0;
        for(int i=0;i<nums.length;i++){
            moves += Math.abs(nums[i] - targetValue);
        }
        return moves;
    }
}
