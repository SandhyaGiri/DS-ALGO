package algorithms.backtracking;

import java.util.*;

public class Subsets {
    /**
     * https://leetcode.com/problems/subsets/
     * 
     * Given a set of distinct integers, nums, return all possible subsets (the power set).

        Note: The solution set must not contain duplicate subsets.

        Example:

        Input: nums = [1,2,3]
        Output:
        [
        [3],
        [1],
        [2],
        [1,2,3],
        [1,3],
        [2,3],
        [1,2],
        []
        ]
     * @param nums
     * @param i
     * @return
     */
    List<List<Integer>> subsetUtil(int[] nums, int i){
        if(i<0){
            return List.of(List.of()); // creates an empty set {}
        }
        List<List<Integer>> prevSubsets = subsetUtil(nums, i-1);
        List<List<Integer>> newSubsets = new ArrayList<List<Integer>>();
        // inject nums[i] into all prevsubsets
        for(List<Integer> subset: prevSubsets){
            List<Integer> newsubset = new ArrayList<>(subset); // clone is needed as {} was an immutable subset created with List.of
            newsubset.add(nums[i]);
            newSubsets.add(newsubset);
        }
        newSubsets.addAll(prevSubsets);
        return newSubsets;
    }
    public List<List<Integer>> subsets(int[] nums) {
        return subsetUtil(nums, nums.length-1);
    }
}