package datastructures.strings;

import java.util.*;

/**
 * https://leetcode.com/problems/backspace-string-compare/
 * 
 * Uses stack = O(N) memory.
 * 
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
 */
class Solution {
    public boolean backspaceCompare(String S, String T) {
        int n = S.length();
        int m = T.length();
        Stack<Character> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>();
        for(int i=0;i<n;i++) {
            if(S.charAt(i) == '#') {
                if(!s1.empty()){
                    s1.pop();   
                }
            }else {
                s1.push(S.charAt(i));
            }
        }
        for(int i=0;i<m;i++) {
            if(T.charAt(i) == '#') {
                if(!s2.empty()){
                    s2.pop();   
                }
            }else {
                s2.push(T.charAt(i));
            }
        }
        // compare stack contents
        while(!s1.empty() && !s2.empty()) {
            if(s1.peek() != s2.peek()) {
                break;
            }
            s1.pop();
            s2.pop();
        }
        return s1.empty() && s2.empty();
    }
}


// https://leetcode.com/problems/backspace-string-compare/ - Space: O(1) solution
// doesn't work for "bxj##tw", "bxo#j##tw"
class Solution2 {
    public int getNextIndex(String str, int currIndex) {
        int nextIndex = currIndex;
        if(currIndex>=0 && currIndex<str.length()) {
            char x = str.charAt(currIndex);
            if(x == '#'){
                int hashCount =0;
                while(nextIndex>=0 && str.charAt(nextIndex) == '#') {
                    nextIndex-=1;
                    hashCount++;
                }
                nextIndex-=hashCount; //skip # many chars
            }
        }
        return nextIndex;
    }
    public boolean backspaceCompare(String S, String T) {
        int n = S.length();
        int m = T.length();
        int nextIndex1 = n-1, nextIndex2 = m-1;
        while(nextIndex1 >=0 || nextIndex2 >=0) {
            // skipped chars by #
            nextIndex1 = getNextIndex(S, nextIndex1);
            nextIndex2 = getNextIndex(T, nextIndex2);
            
            if(nextIndex1 >=0 && nextIndex2>=0) {
                char x = S.charAt(nextIndex1);
                char y = T.charAt(nextIndex2);
                if(x != '#' && y!='#') {
                    if(x!=y) 
                        break;
                    else{
                        // matched non # chars
                        nextIndex1-=1;
                        nextIndex2-=1;
                    }
                }
            }
        }
        return nextIndex1 <= 0 && nextIndex2 <= 0;
    }
}