package datastructures.arrays;

// https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/
public class SumAbsDiffSortedArray {
    /**
     * You are given an integer array nums sorted in non-decreasing order.

        Build and return an integer array result with the same length as nums such that result[i] is equal to the summation of absolute differences between nums[i] and all the other elements in the array.

        In other words, result[i] is equal to sum(|nums[i]-nums[j]|) where 0 <= j < nums.length and j != i (0-indexed).

        Example 1:

        Input: nums = [2,3,5]
        Output: [4,3,5]
        Explanation: Assuming the arrays are 0-indexed, then
        result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4,
        result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3,
        result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5.
        Example 2:

        Input: nums = [1,4,6,8,10]
        Output: [24,15,13,15,21]

        Idea: Since array is sorted, for any i, abs diff to all elements to its left is arrived by subtracting them from nums[i]. Similarly abs diff to all
        elements to its right is obtained by subtracting nums[i] from them.

        For nums[i], the answer is (nums[i] - nums[0]) + (nums[i] - nums[1]) + ... + (nums[i] - nums[i-1]) + (nums[i+1] - nums[i]) + (nums[i+2] - nums[i]) + ... + (nums[n-1] - nums[i]).

        It can be simplified to (nums[i] * i - (nums[0] + nums[1] + ... + nums[i-1])) + ((nums[i+1] + nums[i+2] + ... + nums[n-1]) - nums[i] * (n-i-1)).
        One can build prefix and suffix sums to compute this quickly.
     * @param nums
     * @return
     */
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int leftSum[] = new int[n];
        int rightSum[] = new int[n];
        // sum of nums[0].. nums[i-1] for any i
        for(int i=1;i<n;i++){
            leftSum[i] = leftSum[i-1] + nums[i-1];
        }
        // sum of nums[i+1].. nums[n] for any i
        for(int i=n-2;i>=0;i--){
            rightSum[i] = rightSum[i+1] + nums[i+1];
        }
        int[] result = new int[n];
        for(int i=0;i<n;i++){
            result[i] = nums[i] * i - leftSum[i] + rightSum[i] - nums[i] * (n-i-1);
        }
        return result;
    }
}
