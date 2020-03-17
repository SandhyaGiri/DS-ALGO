package datastructures.strings;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 * 
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

Idea: do a sliding window, but increase or decrease the window size dynamically. Time: O(n), Space: O(n+m)
First find the count profile of pattern, then the first window (starting windowsize=t.length). Then while char count in the window
don't match with expected counts, increase the window, and add new char counts to windowcount. Once a match is found, then compare the window
size to min value gathered so far and update it. and decrement the window size to next index where a char matched with target (skipping all chars
between).
 */
public class MinWindowSubstring {
    Map<Character,Integer> getCharCount(String s){
        Map<Character,Integer> chars = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            chars.put(c, chars.getOrDefault(c, 0)+1);
        }
        return chars;
    }
    boolean isMatch(Map<Character,Integer> expCounts, Map<Character,Integer> counts){
        boolean matches = true;
        //System.out.println("inside match");
        //System.out.println(Arrays.toString(expCounts));
        //System.out.println(Arrays.toString(counts));
        for(Character c:expCounts.keySet()){
            if(counts.get(c) == null || counts.get(c)-expCounts.get(c) < 0) {
                matches=false;
                break;
            }
        }
        return matches;
    }
    public String minWindow(String s, String t) {
        if(t.length()>0 && s.length()>=t.length()){

            Set<Character> tchars = new HashSet<>();
            for(int i=0;i<t.length();i++){
                tchars.add(t.charAt(i));
            }
            Map<Character,Integer> expCount = getCharCount(t);
            
            int minWindowSize = Integer.MAX_VALUE;
            String minWindow = "";
            
            int startIndex = 0;
            int endIndex = t.length();
            Map<Character,Integer> windowCount = getCharCount(s.substring(startIndex, startIndex+endIndex));
            
            // put char matches indices in queue
            Queue<Integer> nextStartIndices = new LinkedList<Integer>();
            for(int i=startIndex;i<startIndex+endIndex;i++){
                if(tchars.contains(s.charAt(i))){
                    nextStartIndices.add(i);
                }
            }
            
            while(startIndex != -1 && endIndex <= s.length()){
                if(isMatch(expCount, windowCount)){
                    //System.out.println("matched");
                    if(endIndex-startIndex < minWindowSize){
                        minWindowSize=endIndex-startIndex;
                        minWindow = s.substring(startIndex, endIndex);
                        //System.out.println(minWindow);
                    }
                    // reduce the windowSize, by moving to start of next possible window
                    int nextStart = nextStartIndices.isEmpty() ? -1 : nextStartIndices.poll();
                    // update count array for removed chars
                    for(int i=startIndex;i<nextStart;i++){
                        char c=s.charAt(i);
                        windowCount.put(c, windowCount.getOrDefault(c, 0)-1);
                    }
                    startIndex = nextStart;
                    //System.out.println(Arrays.toString(windowCount));
                    //System.out.println("start:"+startIndex);
                } else {
                    // expand the window
                    if(endIndex < s.length()){
                        if(tchars.contains(s.charAt(endIndex))){
                            nextStartIndices.add(endIndex);
                            //System.out.println(endIndex);
                        }
                        char c=s.charAt(endIndex);
                        windowCount.put(c, windowCount.getOrDefault(c, 0)+1);
                        //System.out.println(Arrays.toString(windowCount));
                    }
                    endIndex+=1;
                }
            }
            return minWindow;
        }
        return "";
    }
}