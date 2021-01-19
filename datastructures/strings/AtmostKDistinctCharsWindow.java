package datastructures.strings;

import java.util.*;

public class AtmostKDistinctCharsWindow {
    //https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
    /**
     * Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.

        Example 1:

        Input: s = "eceba", k = 2
        Output: 3
        Explanation: The substring is "ece" with length 3.
        Example 2:

        Input: s = "aa", k = 1
        Output: 2
        Explanation: The substring is "aa" with length 2.


     * @param s
     * @param k
     * @return
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> lastIndexMap = new HashMap<>();
        int maxSubstrLen =0;
        int startIndex =0, endIndex=0;
        while(endIndex < s.length()){
            char currchar = s.charAt(endIndex);
            lastIndexMap.put(currchar, endIndex);
            endIndex+=1;
            if(lastIndexMap.size() > k){
                int delIndex = Collections.min(lastIndexMap.values());
                lastIndexMap.remove(s.charAt(delIndex));
                startIndex = delIndex+1;
            }
            maxSubstrLen = Math.max(maxSubstrLen, endIndex - startIndex);
        }
        return maxSubstrLen;
    }

    // https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
    /**
     * 
     * 
     * To solve the problem in one pass let's use here sliding window approach with two set pointers left and right serving as the window boundaries.

The idea is to set both pointers in the position 0 and then move right pointer to the right while the window contains not more than two distinct characters.
If at some point we've got 3 distinct characters, let's move left pointer to keep not more than 2 distinct characters in the window. Let's use for this purpose hashmap containing all
characters in the sliding window as keys and their rightmost positions as values. At each moment, this hashmap could contain not more than 3 elements.
If hashmap contains 3 distinct characters, remove the leftmost character from the hashmap and move the left pointer so that sliding window contains again 2 distinct characters only.

     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s.length() < 3){
            return s.length();
        }
        Map<Character, Integer> lastIndexMap = new HashMap<>();
        int maxSubstrLen = 0;
        int startIndex = 0, endIndex=0;
        while(endIndex < s.length()){
            char currchar = s.charAt(endIndex);
            lastIndexMap.put(currchar, endIndex);
            endIndex++;
            if(lastIndexMap.size() == 3){ //> 2 distinct chars
                // remove leftmost char, and set start after all its duplicates
                // i.e to its latest index.
                int delIndex = Collections.min(lastIndexMap.values());
                lastIndexMap.remove(s.charAt(delIndex));
                startIndex = delIndex +1;
            }
            maxSubstrLen = Math.max(maxSubstrLen, endIndex - startIndex);
        }
        return maxSubstrLen;
    }
}
