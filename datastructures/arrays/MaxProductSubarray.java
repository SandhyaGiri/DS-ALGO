package datastructures.arrays;

import java.util.*;

public class MaxProductSubarray {
    /**
     * https://www.geeksforgeeks.org/maximum-product-subarray-added-negative-product-case/?ref=rp
     * 
     * https://leetcode.com/problems/maximum-product-subarray/
     * 
     * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int maxSoFar = Integer.MIN_VALUE;
        int maxEndingHere = 1; // max positive product
        int minEndingHere = 1; // min negative product
        for(int i=0;i<nums.length;i++){
            if(nums[i] > 0){ // pos number
                maxEndingHere = maxEndingHere * nums[i];
                minEndingHere = Math.min(minEndingHere * nums[i], 1);
            } else if(nums[i] == 0){
                maxEndingHere = 0;
                minEndingHere = 1;
            } else { // neg number
                int tmp = maxEndingHere;
                maxEndingHere = minEndingHere * nums[i]; // update directly though negative or zero. we can reset it to 1 later on!
                minEndingHere = tmp*nums[i]; // certainly negative
            }
            
            if(maxEndingHere > maxSoFar){
                maxSoFar = maxEndingHere;
            }
            if(maxEndingHere <= 0){
                maxEndingHere = 1;
            }
        }
        return maxSoFar;
    }

    /**
     * https://leetcode.com/problems/maximum-product-of-three-numbers/
     * 
     * Given an integer array, find three numbers whose product is maximum and output the maximum product.

        Example 1:

        Input: [1,2,3]
        Output: 6
        

        Example 2:

        Input: [1,2,3,4]
        Output: 24
        

        Note:

        The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
        Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.

        Idea: sort array, O(nlgn) - either last 3 numbers or if initial values are negative (we need atleast 2 negative values + last value)
        Linear time: O(n) - just maintain max 3 values, min 2 values (for negative values)
        then result is product of all max values or (2 min + top max) value
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        if(nums.length >= 3){
            int prod1 = 1;
            for(int i=1;i<=3;i++){
                prod1 *= nums[nums.length -i];
            }
            if(nums[0] <0 && nums[1] < 0){ // 2 smallest negative numbers
                return Math.max(prod1, nums[nums.length-1] * nums[0] * nums[1]);
            }
            return prod1;
        }
        return 0;
    }
}