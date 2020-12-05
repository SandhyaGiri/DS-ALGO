package datastructures.arrays;

import java.util.*;

// https://leetcode.com/problems/subarray-sum-equals-k/
public class SubarraySumK {
    /**
     * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.
    Example 1:

    Input: nums = [1,1,1], k = 2
    Output: 2
    Example 2:

    Input: nums = [1,2,3], k = 3
    Output: 2

    Idea: store sum of all subarrays starting from 0 and ending at i in a new array. (pre-calculation)
    Any subarray sum which stretches from i..j can be calculated as sum[j]-sum[i]. We need to count number of
    subarrays which eitehr start from 0, or internally which have sum K. for first case, its already available from
    sum array. for internal case, O(n) loop on sum array to check if sum[i]-k exists aleady? then use the count of such subarrays
    to add to the overall count. (sum[i]-sum[j] == k <==> sum[i]-k exists)
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int[] sumArr = new int[nums.length];
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            sum+= nums[i];
            sumArr[i] = sum;
        }
        // maintain number of subarrays with same sum
        Map<Integer, Integer> sumsSeen = new HashMap<Integer, Integer>();
        int count = 0;
        for(int i=0;i<nums.length;i++){
            if(sumArr[i] == k){
                count++;
            }
            if(sumsSeen.get(sumArr[i]-k) != null){
                count+=sumsSeen.get(sumArr[i]-k);
            }
            sumsSeen.put(sumArr[i], sumsSeen.getOrDefault(sumArr[i], 0) + 1);
        }
        return count;
    }

    /**
     * https://leetcode.com/problems/continuous-subarray-sum/
     * 
     * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous
     * subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.
        Example 1:

        Input: [23, 2, 4, 6, 7],  k=6
        Output: True
        Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
        Example 2:

        Input: [23, 2, 6, 4, 7],  k=6
        Output: True
        Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.

        Idea: Here though we can use cumulative sum array, we cannot do hashmap lookup, as sum[j]-sum[i] = n*k
        could be true for any n. So we can directly search for all subarrays i..j and check if their sum is divisble
        by k without any reminder.

        Time: O(n*n), Space: O(n) for sum array
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] sum = new int[nums.length];
        boolean found = false;
        int currSum =0;
        for(int i=0;i<nums.length;i++){
            currSum += nums[i];
            // k can negative too, k!= 0 is to avoid divisionByZero error
            if((currSum == k || (k != 0 && (currSum % k) == 0)) && i>=1){
                found = true;
                // System.out.println("Directly found: " + currSum);
                return true;
            }
            sum[i] = currSum;
        }
        if(!found){
            // check all possible subarrays.
            for(int i=0;i<nums.length;i++){
                for(int j=i+1;j<nums.length;j++){ // already subarray size = 2
                    currSum = sum[j]-sum[i]+nums[i];
                    if((k!=0 && (currSum % k) == 0) || currSum == k){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * https://leetcode.com/problems/minimum-size-subarray-sum/
     * 
     * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

        Example: 

        Input: s = 7, nums = [2,3,1,2,4,3]
        Output: 2
        Explanation: the subarray [4,3] has the minimal length under the problem constraint.
        Follow up:
        If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n). 

        Idea: Like subarray sum=k, construct a sum array with sum of elements up to i. If any sum>=s, maintain the smallest length.
        for subarrays not starting at 0, though we cannot directly lookup the remaining sum, we can find the nearest sum to sum[i]-s
        (floor) and check if their difference is atleast s. (nearest neighbor search) for this we need to maintain the sums in a 
        sorted order and also have the latest index where they occur. SO use treeMap with sum as key and index as value. Find the floor of
        (sum[i]-s) and check if floor is close enough. If so update min subarray size.
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int[] sum = new int[nums.length];
        int currSum =0;
        int subArrayMinSize = Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            currSum += nums[i];
            sum[i] = currSum;
            if(currSum >= s){
                subArrayMinSize = Math.min(subArrayMinSize, i+1);
            }
        }
        // maintain the latest index where a particular sum was seen in a TreeMap
        // latest index -> as we need small size
        // if we stored only first index -> then we would get max size subarray
        TreeMap<Integer, Integer> sumOccurences = new TreeMap<>();
        for(int i=0;i<sum.length;i++){
            Integer floor = sumOccurences.floorKey(sum[i]-s);
            System.out.println(floor + "," + (sum[i]-s));
            if(floor != null && sum[i]-floor >= s){
                subArrayMinSize = Math.min(subArrayMinSize, i-sumOccurences.get(floor));
            }
            sumOccurences.put(sum[i], i);
        }
        return subArrayMinSize == Integer.MAX_VALUE ? 0 : subArrayMinSize;
    }
}
