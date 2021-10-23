package datastructures.strings;

import java.util.*;

// https://leetcode.com/problems/find-and-replace-pattern/
public class FindAndReplacePattern {
    /**
     * Given a list of strings words and a string pattern, return a list of words[i] that match pattern. You may return the answer in any order.

        A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.

        Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.

        

        Example 1:

        Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
        Output: ["mee","aqq"]
        Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}. 
        "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation, since a and b map to the same letter.
        Example 2:

        Input: words = ["a","b","c"], pattern = "a"
        Output: ["a","b","c"]
        

        Constraints:

        1 <= pattern.length <= 20
        1 <= words.length <= 50
        words[i].length == pattern.length
        pattern and words[i] are lowercase English letters.

        Idea: A mapping from pattern to any of the words, needs to be bijective -> a maps only to b, b maps only to a.. 
        Just verify this by maintaining two maps, one for forwardMapping and another for reverse mapping, and 
        for every char in pattern and word, check if the char mapping is bijective or not. Break at any point where the
        mapping is not bijective.
     * @param word
     * @param pattern
     * @return
     */
    public boolean isPatternMatch(String word, String pattern){
        Map<Character, Character> originalToReplacementMap = new HashMap<>();
        Map<Character, Character> replacementToOriginalMap = new HashMap<>();
        for(int i=0;i<pattern.length();i++){
            char original = pattern.charAt(i);
            char replacement = word.charAt(i);
            boolean forwardMappingMultipleValues = originalToReplacementMap.containsKey(original) && originalToReplacementMap.get(original) != replacement;
            boolean reverseMappingMultipleValues = replacementToOriginalMap.containsKey(replacement) && replacementToOriginalMap.get(replacement) != original;
            if(forwardMappingMultipleValues || reverseMappingMultipleValues){
                return false;
            } else{
                originalToReplacementMap.put(original, replacement);
                replacementToOriginalMap.put(replacement, original);
            }
        }
        return true;
    }
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> possibleWords = new ArrayList<>();
        for(String word: words){
            if(isPatternMatch(word, pattern)){
                possibleWords.add(word);
            }
        }
        return possibleWords;
    }
}
