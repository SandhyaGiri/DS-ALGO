package datastructures.strings;

import java.util.*;

public class ReverseWords {
    /**
     * https://leetcode.com/problems/reverse-words-in-a-string/
     * 
     * Given an input string, reverse the string word by word.
        Example 1:

        Input: "the sky is blue"
        Output: "blue is sky the"
        Example 2:

        Input: "  hello world!  "
        Output: "world! hello"
        Explanation: Your reversed string should not contain leading or trailing spaces.
        Example 3:

        Input: "a good   example"
        Output: "example good a"
        Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
        

        Note:

        A word is defined as a sequence of non-space characters.
        Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
        You need to reduce multiple spaces between two words to a single space in the reversed string.

     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if(s!=null && s.length()>0) {
            LinkedList<String> words = new LinkedList<String>(); // used as deque to store words found in reverse order
            int sIndex =-1;
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)== ' '){
                    if(sIndex == -1){
                        continue;   
                    }else {
                        // string exists, add it to word list, and reset sIndex
                        words.addFirst(s.substring(sIndex, i));
                        sIndex=-1;
                    }
                } else if(sIndex == -1){
                    sIndex = i;
                }
            }
            if(sIndex != -1){
               // last word exists, add it to word list
                words.addFirst(s.substring(sIndex, s.length())); 
            }
            Optional<String> result = words.stream().reduce((x,y) -> x+" "+y); // reduction of strings using " " in between them
            return result.isPresent() ? result.get() : "";
        }
        return s;
    }
}