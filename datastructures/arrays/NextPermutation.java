package datastructures.arrays;

import java.util.*;

public class NextPermutation {
    // on a descendingly sorted array
    int bsearchCeiling(int[] nums, int l, int r, int target){
        int left =l, right=r;
        while(left<=right){
            int mid = left+ (right-left)/2;
            if((mid+1 >r || target >= nums[mid+1]) && target < nums[mid]){
                return mid;
            } else if(target >= nums[mid]){ // even if equal we have to go right to find the ceil
                right--;
            } else {
                left++;
            }
        }
        return left;
    }
    /**
     * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

        If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).

        The replacement must be in place and use only constant extra memory.

        

        Example 1:

        Input: nums = [1,2,3]
        Output: [1,3,2]
        Example 2:

        Input: nums = [3,2,1]
        Output: [1,2,3]
        Example 3:

        Input: nums = [1,1,5]
        Output: [1,5,1]
        Example 4:

        Input: nums = [1]
        Output: [1]

        Idea: First, we observe that for any given sequence that is in descending order, no next larger permutation is possible. For example, no next permutation is possible for the following array:

        [9, 5, 4, 3, 1]
        We need to find the first pair of two successive numbers a[i] and a[i-1], from the right, which satisfy a[i] > a[i-1].
        Now, no rearrangements to the right of a[i-1] can create a larger permutation since that subarray consists of numbers in descending order.
        Thus, we need to rearrange the numbers to the right of a[i-1] including itself.

        Now, what kind of rearrangement will produce the next larger number? We want to create the permutation just larger than the current one. Therefore, we need to replace the number a[i-1]
        with the number which is just larger than itself (ceiling) among the numbers lying to its right section, say a[j].

        We swap the numbers a[i-1] and a[j]. We now have the correct number at index i-1i−1. But still the current permutation isn't the
        permutation that we are looking for. We need the smallest permutation that can be formed by using the numbers only to the right of a[i-1]. 
        Therefore, we need to place those numbers in ascending order to get their smallest permutation.
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int i=nums.length-1;
        for(;i>0;i--){
            if(nums[i] > nums[i-1]){
                break;
            }
        }
        if(i>0){
            int firstSmallNumFromRight = nums[i-1];
            // find the ceil for this number on the right! nums[i].. nums[n-1]
            int ceilingIndex = bsearchCeiling(nums, i, nums.length-1, firstSmallNumFromRight);
            nums[i-1] = nums[ceilingIndex];
            nums[ceilingIndex] = firstSmallNumFromRight;   
        }
        // now sort the rest of the array
        List<Integer> remainingElements = new ArrayList<>();
        for(int j=i;j<nums.length;j++){
            remainingElements.add(nums[j]);
        }
        Collections.sort(remainingElements);
        for(int j=i;j<nums.length;j++){
            nums[j] = remainingElements.get(j-i);
        }
    }
}
