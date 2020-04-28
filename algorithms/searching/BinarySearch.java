package algorithms.searching;

public class BinarySearch {
    /**
     * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

        You may assume no duplicates in the array.
     * @param nums
     * @param target
     * @return
     */
    public int searchInsertPosition(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        int insertPos = -1;
        while(l<=r) {
            int mid = l + (r-l)/2;
            if(nums[mid] == target) {
                insertPos = mid;
                break;
            }
            else if(target > nums[mid]){
                l=mid+1;
            } else if(target < nums[mid]) {
                r=mid-1;
            }
        }
        return insertPos > 0 ? insertPos : l;
    }

    public int getStartIndex(int[] nums, int target) {
        int startIndex = -1;
        int l = 0, r = nums.length -1;
        // find the start index first
        while(l<=r) {
            int mid = l + (r-l)/2;
            if(nums[mid] == target && (mid-1 < 0 || nums[mid-1] < target)) {
                // when we meet start of the range
                startIndex = mid;
                break;
            } else if(nums[mid] >= target) {
                r = mid -1;
            } else {
                l = mid + 1;
            }
        }
        return startIndex;
    }
    public int getEndIndex(int[] nums, int target) {
        int endIndex = -1;
        int l = 0, r = nums.length-1;
        // find the end index first
        while(l<=r) {
            int mid = l + (r-l)/2;
            if(nums[mid] == target && (mid+1 >= nums.length || nums[mid+1] > target)) {
                // when we meet end of the range
                endIndex = mid;
                break;
            } else if(nums[mid] > target) {
                r = mid -1;
            } else {
                l = mid + 1;
            }
        }
        return endIndex;
    }
    /**
     * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
        Your algorithm's runtime complexity must be in the order of O(log n).
        If the target is not found in the array, return [-1, -1].
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] range = new int[]{-1,-1};
        range[0] = getStartIndex(nums, target);
        range[1] = getEndIndex(nums, target);
        return range;
    }

    /**
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

        (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

        You are given a target value to search. If found in the array return its index, otherwise return -1.

        You may assume no duplicate exists in the array.

        Your algorithm's runtime complexity must be in the order of O(log n).

        Example 1:

        Input: nums = [4,5,6,7,0,1,2], target = 0
        Output: 4
        Example 2:

        Input: nums = [4,5,6,7,0,1,2], target = 3
        Output: -1

        Idea: at any time one half of the array is sorted (correctly), in this we can do normal bsearch if number lies in this range. ow search 
        in next half.

        Problem 2: Search in rotated sorted array with duplicates is not possible with bsearch, we have to use linear search!
     * @param nums
     * @param target
     * @return
     */
    public int searchInRotatedSortedArray(int[] nums, int target) {
        int l=0,r=nums.length-1;
        while(l<=r) {
            int mid = l+(r-l)/2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[l] <= nums[mid]) {
                // left sorted subarray
                if(target >= nums[l] && target<nums[mid]) {
                    r = mid-1;  
                } else {
                    l = mid+1;
                }
            } else if(nums[mid] <= nums[r]) {
                // right sorted subarray
                if(target> nums[mid] && target<=nums[r]) {
                    l = mid+1;
                } else {
                    r = mid-1;
                }
            }
        }
        return -1;
    }

    /**
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

        (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

        Find the minimum element.

        You may assume no duplicate exists in the array.

        Example 1:

        Input: [3,4,5,1,2] 
        Output: 1
        Example 2:

        Input: [4,5,6,7,0,1,2]
        Output: 0

        See: min elem is smaller than left and right element - same for mid comparison. If mid > last element, then min lies in right ow on left.
     * @param nums
     * @return
     */
    public int findMinInSortedRotated(int[] nums) {
        int l=0,r=nums.length-1;
        while(l<=r) {
            int mid = l+(r-l)/2;
            if((mid-1 < l || nums[mid] < nums[mid-1]) && (mid+1 > r || nums[mid] < nums[mid+1])) {
                return nums[mid];
            } else if(nums[mid] > nums[r]) { // smallest ele on right
                l = mid+1;
            } else {
                r = mid-1;
            }
        }
        return -1;
    }
}
