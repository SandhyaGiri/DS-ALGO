package algorithms.dp;

import java.util.*;

// https://leetcode.com/problems/numbers-at-most-n-given-digit-set/
public class AtmostNDigitSet {
    int[] getDigits(int n){
        LinkedList<Integer> digits = new LinkedList<>();
        while(n>0){
            digits.addFirst(n % 10);
            n = n/10;
        }
        return digits.stream().mapToInt(Integer::intValue).toArray();
    }
    // fails for {1,3,5,7} and n=150
    int getMaxDigitCombinationsNaive(String[] digits, int[] maxDigits){
        TreeSet<Integer> availableDigits = new TreeSet<>();
        for(String digit: digits){
            availableDigits.add(Integer.valueOf(digit));
        }
        int maxDigitCombinations = 1;
        for(int i=0;i<maxDigits.length;i++){
            // validDigits are all digits which lie to the left of the maxDigit in sorted set
            Set<Integer> validDigits = availableDigits.headSet(maxDigits[i], true);
            System.out.println(validDigits.size());
            if(validDigits.size() == 0){
                maxDigitCombinations = 0;
                break;
            } else {
                // find number of digits to left of this key
                maxDigitCombinations *= validDigits.size();
            }
        }
        return maxDigitCombinations;
    }
    /**
     * Given an array of digits, you can write numbers using each digits[i] as many times as we want.  For example, if digits = ['1','3','5'], we may write numbers such as '13', '551', and '1351315'.

        Return the number of positive integers that can be generated that are less than or equal to a given integer n.

        

        Example 1:

        Input: digits = ["1","3","5","7"], n = 100
        Output: 20
        Explanation: 
        The 20 numbers that can be written are:
        1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
        Example 2:

        Input: digits = ["1","4","9"], n = 1000000000
        Output: 29523
        Explanation: 
        We can write 3 one digit numbers, 9 two digit numbers, 27 three digit numbers,
        81 four digit numbers, 243 five digit numbers, 729 six digit numbers,
        2187 seven digit numbers, 6561 eight digit numbers, and 19683 nine digit numbers.
        In total, this is 29523 integers that can be written using the digits array.
        Example 3:

        Input: digits = ["7"], n = 8
        Output: 1
        

        Constraints:

        1 <= digits.length <= 9
        digits[i].length == 1
        digits[i] is a digit from '1' to '9'.
        All the values in digits are unique.
        1 <= n <= 109

        Idea: for constructing < number of digits in n combination, we can use all digits in all the places -> this we can
        find easily by counting the combinations. Only for the last case when constructing num of digit == digits in n combination,
        we need to take into consideration digit values in n and find number of valid ways. -> use DP here.

        Let dp[i] be the number of ways to write a valid number if N became N[i], N[i+1], .... For example, if N = 2345,
        then dp[0] would be the number of valid numbers at most 2345, dp[1] would be the ones at most 345, dp[2] would be
        the ones at most 45, and dp[3] would be the ones at most 5.

        Then, by our reasoning above, dp[i] = (number of d in D with d < S[i]) * ((D.length) ** (K-i-1)), plus dp[i+1] if S[i] is in D.
     * @param digits
     * @param n
     * @return
     */
    public int atMostNGivenDigitSet(String[] digits, int n) {
        // 1. find number of digits in n
        int[] maxDigits = getDigits(n);
        int numDigits = maxDigits.length;
        int numCombinations =0;
        // 2. for 1.. numDigits-1 digit combinations, find the possible ways
        for(int i=1;i<numDigits;i++){
            numCombinations += Math.pow(digits.length, i);
        }
        // 3. for numDigits digit combination, we need to find number of digits <= currDigit (each place)
        int[] dp = new int[numDigits+1];
        dp[numDigits]=1;
        for(int i=numDigits-1;i>=0;i--){
            int maxDigit = maxDigits[i];
            for(String digit: digits){
                if(Integer.valueOf(digit) < maxDigit){
                    dp[i] += Math.pow(digits.length, numDigits-i-1); // num_possible_digits ^ #digit combination
                } else if(Integer.valueOf(digit) == maxDigit){
                    dp[i] += dp[i+1];
                }
            }
        }
        return numCombinations + dp[0];
    }
}
