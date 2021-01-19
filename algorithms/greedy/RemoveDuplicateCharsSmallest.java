package algorithms.greedy;

import java.util.*;

// https://leetcode.com/problems/remove-duplicate-letters/
public class RemoveDuplicateCharsSmallest {
    /**
     * Given a string s, remove duplicate letters so that every letter appears once and only once.
     * You must make sure your result is the smallest in lexicographical order among all possible results.

        Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/

        Example 1:

        Input: s = "bcabc"
        Output: "abc"
        Example 2:

        Input: s = "cbacdcbc"
        Output: "acdb"
        

        Constraints:

        1 <= s.length <= 104
        s consists of lowercase English letters.

        Idea: Greedy using stack (as we are not checking all possible deletions of duplicate chars)
        Keep all unique chars in the stack. If current char is lexicographically smaller than stack top
        and the stack top also occurs after the current index, then safely pop that char and mark it as not
        used. Repeat this until a correct spot for insertion and push the current char and mark it as taken.
        So that even if it is visited later on, it won't added to the stack again.

        Time: O(N)
        Space: O(N) - stack
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        int n = s.length();
        Stack<Character> charStack = new Stack<>();
        Map<Character, Integer> lastIndexMap = new HashMap<>();
        Set<Character> usedChars = new HashSet<>();
        for(int i=0;i<n;i++){
            lastIndexMap.put(s.charAt(i), i);
        }
        for(int i=0;i<n;i++){
            char c = s.charAt(i);
            if(usedChars.contains(c)){ // already picked in final string
                continue;
            } else {
                while(!charStack.empty() && c<= charStack.peek()){
                    // pop a char from stack if it is higher than curr, and 
                    // if it also occurs later on (this is not the last occurence of it)
                    int lastIndex = lastIndexMap.get(charStack.peek());
                    if(lastIndex > i){
                        char charToBeRemoved = charStack.pop(); // can be safely removed
                        usedChars.remove(charToBeRemoved); // so that it can be picked later on
                    } else {
                        break;
                    }
                }
                // add curr char to stack
                charStack.push(c);
                // add to list of used chars
                usedChars.add(c);
            }
        }
        // stack has all final chars
        StringBuilder uniqueChars = new StringBuilder();
        while(!charStack.empty()){
            char c = charStack.pop();
            uniqueChars.append(c);
        }
        return uniqueChars.reverse().toString();
    }
}
