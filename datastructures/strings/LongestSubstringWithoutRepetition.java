package datastructures.strings;

import java.util.*;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class LongestSubstringWithoutRepetition {
    /**
     * 
     * Given a string s, find the length of the longest substring without repeating characters.

    Example 1:

    Input: s = "abcabcbb"
    Output: 3
    Explanation: The answer is "abc", with the length of 3.
    Example 2:

    Input: s = "bbbbb"
    Output: 1
    Explanation: The answer is "b", with the length of 1.
    Example 3:

    Input: s = "pwwkew"
    Output: 3
    Explanation: The answer is "wke", with the length of 3.
    Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
    Example 4:

    Input: s = ""
    Output: 0

    Idea: Keep enlargening the window size until a duplicate is encountered. Also maintain a hashmap with char as key and its last occurence as
    the value. When a duplicate char is encountered, we can get its last index from the map, and adjust our window to start from
    next index. We can update the char's curr index in the hashmap. Since hashmap has all chars (even those outside the window),
    when a duplciate occurs we need to check if its index >= start of window. Otherwise this char was outside our window and 
    we can continue widening the window size. Also keep track of the largest window size ? longest substring.
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // char-index map (all chars seen so far, may be outside the window too)
        Map<Character, Integer> windowChars = new HashMap<>();
        int maxSubstrLen = Integer.MIN_VALUE;
        int currWindowLength = 0;
        int start = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(windowChars.containsKey(c)){ 
                int oldIndex = windowChars.get(c);
                // System.out.println(oldIndex +","+start);
                if(oldIndex >= start){ // start new substr as duplicate char found
                    currWindowLength = (i-oldIndex);
                    start = oldIndex +1;
                } else {
                    currWindowLength+= 1; // otherwise it occurred outside the window.   
                }
                // always update the occurence to the new index
                windowChars.put(Character.valueOf(c), i);
            } else {
                windowChars.put(Character.valueOf(c), i);
                currWindowLength+=1;
            }
            if(currWindowLength > maxSubstrLen){
                maxSubstrLen = currWindowLength;
            }
        }
        return maxSubstrLen == Integer.MIN_VALUE ? 0 : maxSubstrLen;
    }
}
