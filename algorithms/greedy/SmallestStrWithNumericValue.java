package algorithms.greedy;

// https://leetcode.com/problems/smallest-string-with-a-given-numeric-value/
public class SmallestStrWithNumericValue {
    /**
     * The numeric value of a lowercase character is defined as its position (1-indexed) in the alphabet, so the numeric value of a is 1, the numeric value of b is 2, the numeric value of c is 3, and so on.

        The numeric value of a string consisting of lowercase characters is defined as the sum of its characters' numeric values. For example, the numeric value of the string "abe" is equal to 1 + 2 + 5 = 8.

        You are given two integers n and k. Return the lexicographically smallest string with length equal to n and numeric value equal to k.

        Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is, either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.

        

        Example 1:

        Input: n = 3, k = 27
        Output: "aay"
        Explanation: The numeric value of the string is 1 + 1 + 25 = 27, and it is the smallest string with such a value and length equal to 3.
        Example 2:

        Input: n = 5, k = 73
        Output: "aaszz"
        

        Constraints:

        1 <= n <= 105
        n <= k <= 26 * n

        Idea: This is like which combination of numbers sum up to a given k, but we are given a constraint "smallest lexigographically".
        This enables us to adopt a greedy solution of always picking the smallest char such that remaining chars can be filled with max
        possible values. This is so that we can break ties for smallest lexicographically.
     * @param n
     * @param k
     * @return
     */
    public String getSmallestString(int n, int k) {
        StringBuilder result = new StringBuilder();
        int currChar = 1;
        int numChars = 0;
        int currValue = 0;
        // O(n) as max we look at n chars
        // similar to the dice greedy solution for zalando
        // instead of picking largest value, we start with smallest val a-1
        // if we can make the rest with max value z-26, then use currChar ow increment it.
        while(numChars < n){
            int charsLeft = n - numChars - 1; // consider currChar picked
            int maxPossibleVal = 26 * charsLeft;
            if(maxPossibleVal >= (k - currValue - currChar)){
                if(numChars == n-1){ // residue at the end
                    currChar = k - currValue;
                }
                result.append(Character.valueOf((char)(currChar - 1 + 'a')));
                numChars++;
                currValue += currChar;
            } else {
                currChar++;
            }
        }
        return result.toString();
    }
}
