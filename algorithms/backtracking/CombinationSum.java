package algorithms.backtracking;

import java.util.*;

class CombinationSum {
    /**
     * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

        The same repeated number may be chosen from candidates unlimited number of times.

        Note:

        All numbers (including target) will be positive integers.
        The solution set must not contain duplicate combinations.
        Example 1:

        Input: candidates = [2,3,6,7], target = 7,
        A solution set is:
        [
        [7],
        [2,2,3]
        ]
        Example 2:

        Input: candidates = [2,3,5], target = 8,
        A solution set is:
        [
        [2,2,2,2],
        [2,3,3],
        [3,5]
        ]
     * @param candidates
     * @param start
     * @param target
     * @param currSum
     * @param result
     * @param candidate
     */
    public void findCandidates(int[] candidates, int start, int target, int currSum, List<List<Integer>> result, List<Integer> candidate) {
        if(currSum > target) {
            return;
        }
        if(currSum == target) {
            result.add(new ArrayList<>(candidate));
            return;
        }
        for(int i=start;i<candidates.length;i++) {
            candidate.add(candidates[i]);
            findCandidates(candidates, i, target, currSum + candidates[i], result, candidate);
            // backtrack
            candidate.remove(candidate.size()-1);
        }
    }
    /**
     * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

        Each number in candidates may only be used once in the combination.

        Note:

        All numbers (including target) will be positive integers.
        The solution set must not contain duplicate combinations.
        Example 1:

        Input: candidates = [10,1,2,7,6,1,5], target = 8,
        A solution set is:
        [
        [1, 7],
        [1, 2, 5],
        [2, 6],
        [1, 1, 6]
        ]
     * @param candidates
     * @param start
     * @param target
     * @param currSum
     * @param result
     * @param candidate
     */
    public void findCandidates2(int[] candidates, int start, int target, int currSum, List<List<Integer>> result, List<Integer> candidate) {
        if(currSum > target) {
            return;
        }
        if(currSum == target) {
            result.add(new ArrayList<>(candidate));
            return;
        }
        int prev = -1;
        for(int i=start;i<candidates.length;i++) {
            if(candidates[i] != prev) {
                candidate.add(candidates[i]);
                findCandidates2(candidates, i+1, target, currSum + candidates[i], result, candidate);
                // backtrack
                candidate.remove(candidate.size()-1);
                prev = candidates[i];   
            }
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> candidate = new ArrayList<Integer>();
        findCandidates(candidates, 0, target, 0, result, candidate);
        return result;
    }
}