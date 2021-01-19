package algorithms.searching;

import java.util.*;

public class BinarySearch {
    /**
     * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it
     * would be if it were inserted in order.

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

        OR - Finding the valley - opposite of finding the peak in a mountain array.
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

    /**
     * Valid Perfect Square
     * 
     * Given a positive integer num, write a function which returns True if num is a perfect square else False.

        Note: Do not use any built-in library function such as sqrt.

        Idea: Instead of finding the sqrt, we can check if a number's square is the target number given.
        Now it boils down to a search problem for numbers in range 1...target/2. Since the numbers are
        sorted, we can use bsearch to find the sqrt number. But we need to be careful of overflows while
        computing num*num == target check.
        O(log n/2)
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        long l=1,r=num/2;
        System.out.println("l:"+l+",r:"+r);
        while(l<=r){
            long mid = l+(r-l)/2;
            System.out.println(mid);
            System.out.println("l:"+l+",r:"+r);
            if((mid*mid) == num){
                return true;
            }else if(mid*mid > num){
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        return num == l*l;
    }

    /**
     * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once. Find this single element that appears only once.
        Example 1:

        Input: [1,1,2,3,3,4,4,8,8]
        Output: 2
        Example 2:

        Input: [3,3,7,7,10,11,11]
        Output: 10
        

        Note: Your solution should run in O(log n) time and O(1) space.

        Idea: use the indices, before this single element all pairs occur at even, odd indices.
        After this single element, the pairs occur at odd, even indices.
     * @param nums
     * @return
     */
    public int singleNonDuplicateSorted(int[] nums) {
        int l=0, r=nums.length-1;
        while(l<r){
            int mid = l+(r-l)/2;
            if(mid%2 == 0){ // mid is even
                if(nums[mid] == nums[mid+1]) { // even, odd pair, so single element on right
                    l = mid+2;
                } else{
                    r = mid;
                }
            }else { // mid is odd
                if(nums[mid-1] == nums[mid]){ // even, odd pair, so single element on right
                    l = mid+1;
                } else {
                    r = mid;
                }
            }
        }
        return nums[l];
    }

    /**
     * https://leetcode.com/problems/h-index-ii/
     * @param citations
     * @return
     * 
     * Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

        According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

        Example:

        Input: citations = [0,1,3,5,6]
        Output: 3 
        Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had 
                    received 0, 1, 3, 5, 6 citations respectively. 
                    Since the researcher has 3 papers with at least 3 citations each and the remaining 
                    two with no more than 3 citations each, her h-index is 3.
        Note:

        If there are several possible values for h, the maximum one is taken as the h-index.

        Idea: search in logn time.
     */
    public int hIndex(int[] citations) {
        int l=0,r=citations.length-1;
        int N = citations.length;
        int hIndex =0;
        while(l<=r){
            int mid = l+(r-l)/2;
            int h = N-mid; // number of papers on right, all values >= citations[mid]
            if(h == citations[mid] || (mid-1 <0 && h<=citations[mid]) || (mid-1>=0 && h>=citations[mid-1] && h<=citations[mid])){
                hIndex = h;
                break;
            } else if(h> citations[mid]){
                // go right
                // as even on going left since more elements on right (h), we will only encounter smaller values
                // and hIndex cannot be found.. so we have to reduce h by going right
                l=mid+1;
            } else{
                r = mid-1;
            }
        }
        return hIndex;
    }

    // https://leetcode.com/problems/find-k-closest-elements/
    int bsearchIndex(int target, int[] arr){
        int l=0,r=arr.length-1;
        while(l<=r){
            int mid=l+(r-l)/2;
            if(arr[mid] == target){
                return mid;
            } else if(target > arr[mid]){
                l = mid+1;
            } else{
                r=mid-1;
            }
        }
        return l;
    }
    /**
     * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

        An integer a is closer to x than an integer b if:

        |a - x| < |b - x|, or
        |a - x| == |b - x| and a < b
        

        Example 1:

        Input: arr = [1,2,3,4,5], k = 4, x = 3
        Output: [1,2,3,4]
        Example 2:

        Input: arr = [1,2,3,4,5], k = 4, x = -1
        Output: [1,2,3,4]

        Idea: locate the target x in the array or its insertpos using bsearch. Then we need to find k elements either on left or right side
        of this insert pos. keep expanding left (and add those elements to result) as long as their abs diff to target is <= right pointer.
        Otheriwse take the right element and increment its pointer. 

        Two pointer solution expanding from target's insertPos until k elements are used.
        Time: O(log n + k + klogk) // last part for sorting resulting k elements.
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int index = bsearchIndex(x, arr);
        int leftIndex = Math.max(0, index-1);
        if(leftIndex == index){
            index +=1;
        }
        System.out.println(index + "-" + leftIndex);
        List<Integer> result = new ArrayList<>();
        while(k>0){
            // prefer left element even if abs diff is same as that of right ele
            if(leftIndex>=0 && (index >= arr.length || x - arr[leftIndex] <= arr[index] - x)){
                result.add(arr[leftIndex]);
                leftIndex-=1;
            } else if(index < arr.length){
                result.add(arr[index]);
                index+=1;
            }
            k-=1;
        }
        Collections.sort(result);
        return result;
    }
}

class MountainPeaks {
    /**
     * https://leetcode.com/problems/find-peak-element
     * https://leetcode.com/problems/peak-index-in-a-mountain-array 
     * 
     * Given a mountain/mountain range array, which 1 peak or multiple peaks
     * find the index of one of the peaks.
     * 
     * Idea: 
     * We start off by finding the middle element, mid from the given nums array.
     * If this element happens to be lying in a descending sequence of numbers. 
     * or a local falling slope(found by comparing nums[i] to its right neighbour), 
     * it means that the peak will always lie towards the left of this element. 
     * Thus, we reduce the search space to the left of mid(including itself) and perform the same process on left subarray. 
     * If the middle element, mid lies in an ascending sequence of numbers, 
     * or a rising slope(found by comparing nums[i]nums[i] to its right neighbour), 
     * it obviously implies that the peak lies towards the right of this element. 
     * Thus, we reduce the search space to the right of midmid and perform the same process on the right subarray.
     * 
     * Works for 1 peak, multiple peaks.
     * 
     * Using Bsearch even on unsorted array, but where we can reduce search space by half every time.
     * 
     * Time: O(logN)
     * Space: O(1)
     */
    public int findPeakElement(int[] nums) {
        int l=0,r=nums.length-1;
        int peak = -1;
        while(l<=r){
            int mid = l+(r-l)/2;
            // peak is either taller than its neighbors (if they exist)
            if((mid-1 < 0 || nums[mid] > nums[mid-1] ) && (mid+1 > r || nums[mid] > nums[mid+1])){
                peak = mid;
                break;
            }
            else if(mid+1 <=r && nums[mid] < nums[mid+1]) { // increasing sequence, peak to the right
                l = mid+1;           
            } else { // dreasing sequence peak to the left
                r = mid-1;
            }
        }
        return peak; // index of the peak element.
    }

    interface MountainArray {
        public int get(int index);
        public int length();
    }
    public int findPeakElementIndex(MountainArray mountainArr) {
        int l=0,r=mountainArr.length()-1;
        int peak = -1;
        while(l<=r){
            int mid = l+(r-l)/2;
            int curr = mountainArr.get(mid);
            int next = mid+1 <= r ? mountainArr.get(mid+1) : -1;
            int prev = mid-1 >= l ? mountainArr.get(mid-1) : -1;
            // peak is either taller than its neighbors (if they exist)
            if(curr > next && curr > prev){
                peak = mid;
                break;
            }
            else if(mid+1 <=r && curr < next) { // increasing sequence, peak to the right
                l = mid+1;           
            } else { // dreasing sequence peak to the left
                r = mid-1;
            }
        }
        return peak; // index of the peak element.
    }
    int bsearch(MountainArray mountainArr, int target, int l, int r, boolean ascending){
        int index = -1;
        while(l<=r){
            int mid = l +(r-l)/2;
            int curr = mountainArr.get(mid);
            if(curr == target){
                index = mid;
                break;
            } else if((ascending && curr > target) || (!ascending && curr < target)){
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        return index;
    }
    /**
     * https://leetcode.com/problems/find-in-mountain-array 
     * 
     * Search for a target in a certain mountain array.
     * 
     * Idea: multiple bsearch trials.
     * 1. Use Bsearch to find the peak index
     * 2. target could be the peak or may lie on increasingly sorted array on left of peak
     * or on decreasingly sorted array on right of the peak.
     * 
     * Trying to do both searching while looking for peak, will result in many calls to API.
     * @param target
     * @param mountainArr
     * @return
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peakIndex = findPeakElementIndex(mountainArr);
        if(mountainArr.get(peakIndex) == target){
            return peakIndex;
        }
        int leftIndex = bsearch(mountainArr, target, 0, peakIndex, true); // asc sorting
        if(leftIndex == -1){
            return bsearch(mountainArr, target, peakIndex, mountainArr.length()-1, false); // desc sorting
        }
        return leftIndex;
    }
}

// https://leetcode.com/problems/find-right-interval/
class FindRightIntervals{
    /**
     * Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point
     * is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.

        For any interval i, you need to store the minimum interval j's index, which means that the interval j
        has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist,
        store -1 for the interval i. Finally, you need output the stored value of each interval as an array.

        Note:

        You may assume the interval's end point is always bigger than its start point.
        You may assume none of these intervals have the same start point.
 
        Example 2:

        Input: [ [3,4], [2,3], [1,2] ]

        Output: [-1, 0, 1]

        Explanation: There is no satisfied "right" interval for [3,4].
        For [2,3], the interval [3,4] has minimum-"right" start point;
        For [1,2], the interval [2,3] has minimum-"right" start point.

        Idea: If we have a sorted list of starting points, then we just have to use
        binary search to find the ceil for the endpoint. This would give us the closest
        starting point that is greater than or equal to endpoint. But we need index of the 
        interval, so we store the starting point-index in a treeMap and use its ceilingEntry method
        to get both starting point and the index.

        ceilingEntry basically does bsearch inside, as entries are stored in a balanced binary search tree.
     * @param intervals
     * @return
     */
    public int[] findRightInterval(int[][] intervals) {
        // map between start pt and index
        // using map, as we are given starting points are UNIQUE
        TreeMap<Integer, Integer> startPoints = new TreeMap<>();
        int numIntervals = intervals.length;
        for(int i=0;i<numIntervals;i++){
            startPoints.put(intervals[i][0], i);
        }
        int[] rightIntervals = new int[numIntervals];
        for(int i=0;i<numIntervals;i++){
            int endPoint = intervals[i][1];
            Map.Entry<Integer, Integer> rightInterval = startPoints.ceilingEntry(endPoint);
            // store the index of the matching interval
            rightIntervals[i] = rightInterval == null ? -1 : rightInterval.getValue();
        }
        return rightIntervals;
    }
}
