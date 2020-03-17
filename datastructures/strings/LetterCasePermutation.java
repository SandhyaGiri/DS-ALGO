package datastructures.strings;

import java.util.*;

/**
 * https://leetcode.com/problems/letter-case-permutation/
 * 
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.

Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Input: S = "3z4"
Output: ["3z4", "3Z4"]

Input: S = "12345"
Output: ["12345"]
Note:

S will be a string with length between 1 and 12.
S will consist only of letters or digits.
 */
public class LetterCasePermutation {
    void casePermutUtil(String S, int i, String currStr, List<String> permutations){
        if(i==S.length()){
            permutations.add(currStr);
            return;
        }
        char c = S.charAt(i);
        String upperCase = (""+c).toUpperCase();
        String lowerCase = (""+c).toLowerCase();
        if(upperCase.equals(lowerCase)){
            casePermutUtil(S, i+1, currStr+c, permutations);
        }else {
            // 2 case possibilities
            casePermutUtil(S, i+1, currStr+upperCase, permutations);
            casePermutUtil(S, i+1, currStr+lowerCase, permutations);
        }
    }
    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<String>();
        casePermutUtil(S, 0, "", result);
        return result;
    }
}