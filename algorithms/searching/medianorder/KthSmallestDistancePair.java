package algorithms.searching.medianorder;

import java.util.*;

// https://leetcode.com/problems/find-k-th-smallest-pair-distance
public class KthSmallestDistancePair {
    public int smallestDistancePairMemHeavy(int[] nums, int k) {
        // leads to memory limit exceeded for a very large k
        //only has k pairs 
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        int pairCount=0;
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                int dis = Math.abs(nums[i] - nums[j]);
                if(pairCount < k){
                    maxHeap.add(dis);
                    pairCount += 1;
                } else {
                    // compare if curr pair's distance is smaller than top of heap
                    if(dis < maxHeap.peek()){
                        maxHeap.poll();
                        maxHeap.add(dis);
                    }
                }
            }
        }
        System.out.println(maxHeap.isEmpty());
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }
    /**
     * Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.

        Example 1:
        Input:
        nums = [1,3,1]
        k = 1
        Output: 0 
        Explanation:
        Here are all the pairs:
        (1,3) -> 2
        (1,1) -> 0
        (3,1) -> 2
        Then the 1st smallest distance pair is (1,1), and its distance is 0.
        Note:
        2 <= len(nums) <= 10000.
        0 <= nums[i] < 1000000.
        1 <= k <= len(nums) * (len(nums) - 1) / 2.

        Idea: Similar to finding ith smallest element ( by neglecting one half od the elements), we should be able to
        find the distance i.e (kth smallest distance) without generating the pairs or sorting them or storing them in a heap.
        1. sort the array
        2. Binary search: between smallest dis and max dist (0.. last-first) (as we are searching for the distance)
        3. At each mid, we need to calculate number of pairs whose distance is <= mid, then see if k is lesser than this count
        then k lies on the left. otherwise on the right.

        Time: O( W + N logW + N logN), W = nums[-1] - nums[0] (max distance)
        we do O(W) work to calculate prefix initially. 
        The logW factor comes from our binary search, and we do O(N) work inside our call to calculate count. 
        The final O(NlogN) factor comes from sorting.

        Space: O(N+W), the space used to store multiplicity and prefix.
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int width = nums[nums.length -1] * 2; // twice the maximum number
        // calc multiplicty (in sorted array)
        int[] multiplicity = new int[nums.length];
        for(int i=1;i<nums.length-1;i++){
            if(nums[i] == nums[i-1]){
                multiplicity[i] = multiplicity[i-1]+1;
            }
        }
        // calc prefix[v]
        int[] prefix = new int[width];
        int left = 0;
        for(int i=0;i<width;i++){
            // left already has number of elements less than i
            // only add number of elements = i
            while(left < nums.length && nums[left] == i){
                left +=1;
            }
            prefix[i]=left;
        }
        // binary search on the range of 0..max pair distance possible
        int l=0;
        int r = nums[nums.length-1] - nums[0]; // largest diff in sorted arr
        while(l < r){
            int mid = (l+r)/2; // mid is distance between any pair
            int countLeftPairs = 0; // number of pairs to left of mid
            for(int i=0;i<nums.length;i++){
                // second part gives count of numbers greater than nums[i] whose distance to nums[i] is atmost mid
                // this combined with number of times nums[i] occurs, we get total pairs
                countLeftPairs +=  multiplicity[i] + (prefix[nums[i] + mid] - prefix[nums[i]]);
            }
            if(k <= countLeftPairs){
                // go left
                r = mid; // k could be mid too
            } else {
                l = mid+1;
            }
        }
        return l;
    }
}
