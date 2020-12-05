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

    // https://leetcode.com/problems/subsets-ii/
    void subsetUtil(int[] nums, int i, List<Integer> currSet, List<List<Integer>> result){
        if(i>=nums.length){
            if(!result.contains(currSet)){
                result.add(new ArrayList<>(currSet));   
            }
            return;
        }
        subsetUtil(nums, i+1, currSet, result); // not including nums[i]
        currSet.add(nums[i]);
        subsetUtil(nums, i+1, currSet, result); // including nums[i]
        // backtracking
        currSet.remove(Integer.valueOf(nums[i]));
    }
    /**
     * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

        Note: The solution set must not contain duplicate subsets.

        Example:

        Input: [1,2,2]
        Output:
        [
        [2],
        [1],
        [1,2,2],
        [2,2],
        [1,2],
        []
        ]

        Idea: maintain the curr path (or set), while backtracking remove this element. On reaching end of
        the array, add the curr set to a list only if it is not already existing.
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        subsetUtil(nums, 0, new ArrayList<>(), result);
        return result;
    }
}