package problemsolving;

import java.util.*;

// https://leetcode.com/problems/longest-palindrome/
public class LongestPalindrome {
    /**
     * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

        This is case sensitive, for example "Aa" is not considered a palindrome here.

        Note:
        Assume the length of given string will not exceed 1,010.

        Example:

        Input:
        "abccccdd"

        Output:
        7

        Explanation:
        One longest palindrome that can be built is "dccaccd", whose length is 7.

        Idea: Even count chars - we can take them all, use half on left side, half on right side
        Odd count chars - count-1 is even and can be fully used. 1 left out char can be used as center char
        Count=1 chars - can only be used as center char.
        Choose only one center char

     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        // greedy solution
        Map<Character,Integer> charCounts = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            charCounts.put(c, charCounts.getOrDefault(c,0)+1);
        }
        int longestPalindromeLength = 0;
        boolean centerCharFilled = false;
        for(Character c: charCounts.keySet()){
            int charCount = charCounts.get(c);
            // System.out.println(c + ":" + charCount);
            if(charCount%2 == 0){
                longestPalindromeLength += charCount;
            } else if((charCount-1)/2 > 0){
                longestPalindromeLength += charCount-1;
                if(!centerCharFilled){
                    longestPalindromeLength += 1;
                    centerCharFilled=true;
                }
            } else if(!centerCharFilled){
                longestPalindromeLength += 1;
                centerCharFilled=true;
            }
        }
        return longestPalindromeLength;
    }
}