package algorithms.backtracking;

import java.util.*;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * 
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

 */
public class LetterCombinations{
    void genCombinations(String digits, int index, Map<Integer, List<Character>> letterEquivalents, String letters, List<String> combinations){
        if(index==digits.length()) {
            if(!"".equals(letters)) {
                combinations.add(letters);   
            }
            return;
        }
        int digit = Integer.valueOf(digits.charAt(index)+"");
        List<Character> equivs = letterEquivalents.get(digit);
        if(equivs != null){
            for(Character c: equivs){
                genCombinations(digits, index+1, letterEquivalents, letters+c, combinations);
            } 
        }  
    }
    public List<String> letterCombinations(String digits) {
        Map<Integer, List<Character>> letterEquivalents = new HashMap<>();
        letterEquivalents.put(2, List.of('a','b','c'));
        letterEquivalents.put(3, List.of('d','e','f'));
        letterEquivalents.put(4, List.of('g','h','i'));
        letterEquivalents.put(5, List.of('j','k','l'));
        letterEquivalents.put(6, List.of('m','n','o'));
        letterEquivalents.put(7, List.of('p','q','r','s'));
        letterEquivalents.put(8, List.of('t','u','v'));
        letterEquivalents.put(9, List.of('w','x','y','z'));
        List<String> combinations = new ArrayList<>();
        genCombinations(digits, 0, letterEquivalents, "", combinations);
        return combinations;
    }
}