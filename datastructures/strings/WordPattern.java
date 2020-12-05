package datastructures.strings;

import java.util.*;

// https://leetcode.com/problems/word-pattern/
public class WordPattern {

    /**
     * Given a pattern and a string str, find if str follows the same pattern.

        Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

        Example 1:

        Input: pattern = "abba", str = "dog cat cat dog"
        Output: true
        Example 2:

        Input:pattern = "abba", str = "dog cat cat fish"
        Output: false
        Example 3:

        Input: pattern = "aaaa", str = "dog cat cat dog"
        Output: false
        Example 4:

        Input: pattern = "abba", str = "dog dog dog dog"
        Output: false
        Notes:
        You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a single space.

        Idea: use a bidirectional mapping of words and chars.
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        Map<String, Character> strCharMapping = new HashMap<>();
        Map<Character, String> charStrMapping = new HashMap<>();
        if(words.length != pattern.length()){
            return false;
        }
        for(int i=0;i<pattern.length();i++){
            Character currChar = Character.valueOf(pattern.charAt(i));
            if(!strCharMapping.containsKey(words[i]) && !charStrMapping.containsKey(currChar)){
                strCharMapping.put(words[i], currChar);
                charStrMapping.put(currChar, words[i]);
            } else if((strCharMapping.containsKey(words[i]) && !strCharMapping.get(words[i]).equals(currChar)) || (charStrMapping.containsKey(currChar) && !charStrMapping.get(currChar).equals(words[i]))){
                return false;
            }
        }
        return true;
    }
}