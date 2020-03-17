package algorithms.backtracking;

import java.util.*;

class CombinationSum3 {
    /**
     * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
     * @param k
     * @param n
     * @param currSum
     * @param start
     * @param candidate
     * @param result
     */
    public void findCandidates(int k, int n, int currSum, int start, List<Integer> candidate, List<List<Integer>> result) {
        if(candidate.size() > k || currSum > n) {
            return;
        }
        if(currSum == n) {
            if(candidate.size() == k) {
                result.add(new ArrayList<>(candidate));  
            }
            return;
        }
        for(int i=start;i<=9;i++) {
            candidate.add(i);
            findCandidates(k, n, currSum + i, i+1, candidate, result);
            candidate.remove(candidate.size()-1);
        }
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> candidate = new ArrayList<Integer>();
        findCandidates(k, n, 0, 1, candidate, result);
        return result;
    }
}