package algorithms.dp;

import java.util.*;

public class LargestDivisibleSubset {
    /**
     * https://leetcode.com/problems/largest-divisible-subset/
     * 
     * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements 
     * in this subset satisfies:

        Si % Sj = 0 or Sj % Si = 0.

        If there are multiple solutions, return any subset is fine.

        Example 1:

        Input: [1,2,3]
        Output: [1,2] (of course, [1,3] will also be ok)
        Example 2:

        Input: [1,2,4,8]
        Output: [1,2,4,8]

        Idea: like LIS, after sorting the numbers, for each i, find an index j<i such that nums[j] completely divides the current nums[i]
        and ldsLength of i is maximum of all possible j's.
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n= nums.length;
        int[] ldsLength = new int[n];
        int maxDsLength = Integer.MIN_VALUE;
        int maxDsIndex = -1;
        int[] chosenPrevIndex = new int[n];
        Arrays.sort(nums);
        List<Integer> result = new ArrayList<>();
        if(n>0){
            for(int i=0;i<n;i++){
                int maxLen = 0;
                int maxIndex = -1;
                // extend the largest subset so far, of all divisible previous elements
                for(int j=i-1;j>=0;j--){
                    if(nums[i]%nums[j] == 0 && ldsLength[j] > maxLen){
                        maxLen = ldsLength[j];
                        maxIndex = j;
                    }
                }
                ldsLength[i] = maxLen +1;
                chosenPrevIndex[i] = maxIndex;
                if(ldsLength[i] > maxDsLength){
                    maxDsLength = ldsLength[i];
                    maxDsIndex = i;
                }
            }
            // reconstruct largest divisble subset
            int x = maxDsIndex;
            while(x >=0){
                result.add(nums[x]);
                x = chosenPrevIndex[x];
            }
        }
        return result;
    }
}