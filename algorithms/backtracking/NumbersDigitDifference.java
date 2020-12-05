package algorithms.backtracking;

import java.util.*;

// https://leetcode.com/problems/numbers-with-same-consecutive-differences
public class NumbersDigitDifference {

    /**
     * IDEA: do a DFS search for all valid digit combinations
     * starting digit can be 0 only for totalDigits=1, ow we have from 1..9
     * For each of these start digits, next possible digits(0..9) should be at abs diff of K (search tree is narrowed down).
     * After all digits are considered, add the final combination to result list.
     * 
     * @param currDigit
     * @param totalDigits
     * @param diff
     * @param currCombination
     * @param result
     */
    void dfsUtil(int currDigit, int totalDigits, int diff, String currCombination, List<Integer> result){
        if(currDigit == 0){
            result.add(Integer.valueOf(currCombination.toString()));
            return;
        }
        if(currDigit < totalDigits){
            // include zero all digits at difference diff from lastDigit
            int digit = currCombination.charAt(currCombination.length()-1) - '0';
            for(int i=0;i<=9;i++){
                if(Math.abs(digit- i) == diff){
                    dfsUtil(currDigit-1, totalDigits, diff, currCombination + Integer.valueOf(i).toString(), result);
                }
            }
        } else {
            // first digit
            int startDigit = totalDigits == 1 ? 0 : 1;
            for(int i=startDigit;i<=9;i++){
                dfsUtil(currDigit-1, totalDigits, diff, Integer.valueOf(i).toString(), result);
            }
        }
    }

    /**
     * Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.

        Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.

        You may return the answer in any order.

        

        Example 1:

        Input: N = 3, K = 7
        Output: [181,292,707,818,929]
        Explanation: Note that 070 is not a valid number, because it has leading zeroes.
        Example 2:

        Input: N = 2, K = 1
        Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
        

        Note:

        1 <= N <= 9
        0 <= K <= 9
     * @param N
     * @param K
     * @return
     */
    public int[] numsSameConsecDiff(int N, int K) {
        List<Integer> result = new LinkedList<>();
        dfsUtil(N, N, K, "", result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}