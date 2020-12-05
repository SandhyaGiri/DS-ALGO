package datastructures.arrays;

// https://leetcode.com/problems/find-the-duplicate-number
public class FindDuplicate {
    // simple recursive solution (time limit exceeded)
    int getDuplicate(int[] nums, int l, int r){
        if(l>=r){
            return -1;
        }
        if(nums[l] == nums[r]){
            return nums[l];
        }
        int lDup = getDuplicate(nums, l, r-1);
        int rDup = getDuplicate(nums, l+1, r);
        return lDup != -1 ? lDup : rDup;
    }
    /**
     * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
     * prove that at least one duplicate number must exist.
     * Assume that there is only one duplicate number, find the duplicate one.

        Example 1:

        Input: [1,3,4,2,2]
        Output: 2
        Example 2:

        Input: [3,1,3,4,2]
        Output: 3
        Note:

        You must not modify the array (assume the array is read only).
        You must use only constant, O(1) extra space.
        Your runtime complexity should be less than O(n2).
        There is only one duplicate number in the array, but it could be repeated more than once.

        Note: this solution modifies the array
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int duplicate = -1;
        for(int i=0;i<n;i++){
            int index = nums[i] < 0 ? -1*nums[i] : nums[i];
            if(nums[index] < 0){
                duplicate = index;
                break;
            } else {
                nums[index] = -1*nums[index];
            }
        }
        return duplicate;
    }
}