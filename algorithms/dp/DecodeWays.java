package algorithms.dp;

import java.util.*;

// https://leetcode.com/problems/decode-ways/
public class DecodeWays {
    int numWays = 0;
    // exponential complexity, in order of 2^s.length()
    void dfsParse(String s, int currIndex){
        if(currIndex >= s.length()){
            numWays += 1;
            return;
        }
        // take curr char alone
        int charValue = Character.getNumericValue(s.charAt(currIndex));
        if(charValue >=1 && charValue <= 9){
            dfsParse(s, currIndex+1);
        }
        // take next char too
        if(currIndex+1 < s.length()){ // only two chars allowed
            int nextCharValue = Character.getNumericValue(s.charAt(currIndex+1));
            int number = charValue * 10 + nextCharValue;
            if(charValue != 0 && number >=1 && number <= 26){
                dfsParse(s, currIndex+2);
            }
        }
    }
    /**
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:

        'A' -> 1
        'B' -> 2
        ...
        'Z' -> 26
        Given a non-empty string containing only digits, determine the total number of ways to decode it.

        The answer is guaranteed to fit in a 32-bit integer.

        Example 1:

        Input: s = "12"
        Output: 2
        Explanation: It could be decoded as "AB" (1 2) or "L" (12).
        Example 2:

        Input: s = "226"
        Output: 3
        Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
        Example 3:

        Input: s = "0"
        Output: 0
        Explanation: There is no character that is mapped to a number starting with '0'. We cannot ignore a zero when we face it while decoding. So, each '0' should be part of "10" --> 'J' or "20" --> 'T'.
        Example 4:

        Input: s = "1"
        Output: 1
        

        Constraints:

        1 <= s.length <= 100
        s contains only digits and may contain leading zero(s).

        Idea: Number of ways of reaching a char at i, is sum of number of ways of reaching previous char -1 (if char is also valid, i.e lies within 1 and 9)
        and number of ways of reaching -2 char (if the current two digit value is valid, i.e lies in range of 10..26).
        If currchar is not valid, then path breaks here, and we need to set numWays[i]= -1 to indicate absence of a path.
        If any of previous chars are themselves not valid, we cannot extend them even if curr char is valid. here again use -1 to indicate no path.
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int n = s.length();
        int[] numWays = new int[n+2];
        numWays[0]=0;
        numWays[1]=0;
        for(int i=0;i<s.length();i++){
            int charValue = Character.getNumericValue(s.charAt(i));
            int previousWays = 0;
            boolean currValidChar = false; // maintains if current char is a valid extension to any of previous paths.
            // previous char should be reachable from origin
            if(numWays[i+1] >= 0 && charValue >=1 && charValue <= 9){
                // single char
                // start a new path if not found so far
                previousWays += numWays[i+1] == 0 ? 1 : numWays[i+1];
                currValidChar = true;
            }
            // The one before previous char should also be reachble from origin
            if(i>0 && numWays[i] >= 0){
                int prevCharValue = Character.getNumericValue(s.charAt(i-1));
                int twoDigitNumber = prevCharValue*10 + charValue; // ending here at i
                if(prevCharValue != 0 && twoDigitNumber >= 1 && twoDigitNumber <= 26){
                    // two char
                    // start a new path if one has not been found so far
                    previousWays += numWays[i] == 0 ? 1 : numWays[i];
                    currValidChar = true;
                }
            }
            numWays[i+2] = currValidChar ? previousWays : -1; // -1 denotes invalid char 
            // to stop extending ways through this (an obstacle)
        }
        System.out.println(Arrays.toString(numWays));
        return numWays[n+1] == -1 ? 0 : numWays[n+1]; // no path found, return 0
    }
}
