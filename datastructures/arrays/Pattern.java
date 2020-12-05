package datastructures.arrays;

// https://leetcode.com/problems/132-pattern/
public class Pattern{
    /**
     * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].

        Return true if there is a 132 pattern in nums, otherwise, return false.

        Follow up: The O(n^2) is trivial, could you come up with the O(n logn) or the O(n) solution?

        

        Example 1:

        Input: nums = [1,2,3,4]
        Output: false
        Explanation: There is no 132 pattern in the sequence.
        Example 2:

        Input: nums = [3,1,4,2]
        Output: true
        Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
        Example 3:

        Input: nums = [-1,3,2,0]
        Output: true
        Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].

     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        if(nums.length>=3){
            // for each j find a i corresponding to smallest nums[i]
            int[] minI = new int[nums.length];
            minI[0]=-1; // first element cannot be a j
            int minElement = nums[0];
            int minIndex =0;
            for(int j=1;j<nums.length;j++){
                if(nums[j]>minElement){
                    minI[j]= minIndex;
                } else {
                    minI[j] =-1;
                    // update min
                    if(nums[j]<minElement){
                        minElement = nums[j];
                        minIndex =j;
                    }
                }
            }
            // find a k which can be inserted between arr[j] and minI[j]
            for(int k=nums.length-1;k>0;k--){
                for(int j=k-1;j>0;j--){
                    if(nums[k]<nums[j] && minI[j] != -1 && nums[k]>nums[minI[j]]){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}