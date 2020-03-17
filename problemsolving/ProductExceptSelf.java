package problemsolving;

/**
 * https://leetcode.com/problems/product-of-array-except-self/
 * 
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)

Idea: In L array, store for each j the product of elements not including j (from left), 0..j-1. For the first element since no ele exist before it
the value is 1. Similarly in R array store for each j the product of elements on right not including j j+1...n-1. For the last element this value
is 1. Then we need to multiply these arrays elemntwise, to get product of numbers without self.
 */
public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        // construct l
        output[0]=1;
        for(int i=1;i<nums.length;i++){
            output[i]= output[i-1]*nums[i-1];
        }
        // update r
        int product =1;
        for(int i=nums.length-2;i>=0;i--){
            product*= nums[i+1];
            output[i]*=product;
        }
        return output;
    }
}