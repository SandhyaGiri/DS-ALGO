package datastructures.arrays;

/**

Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.

Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.

 */
class TwoSumSorted {
    private int binSearch(int[] arr, int l, int r, int target) {
        int found = -1;
        while(l<=r) {
            int mid = (int)Math.floor((l+r)/2);
            if(arr[mid] == target) {
                found = mid;
                break;
            } else if(arr[mid] < target) {
                l = mid+1;
            } else if(arr[mid] >target) {
                r = mid-1;
            }
        }
        return found;
    }
    
    public int[] twoSum(int[] numbers, int target) {
        for(int i=0;i<numbers.length;i++) {
            int pair_index = binSearch(numbers, i+1, numbers.length-1, target-numbers[i]);
            if(pair_index != -1) {
                return new int[]{i+1,pair_index+1};
            }
        }
        return new int[]{};
    }
}