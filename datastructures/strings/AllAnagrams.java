package datastructures.strings;

import java.util.*;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * 
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
class AllAnagrams {
    private List<String> insert(String s, char c) {
        List<String> insertions = new ArrayList<String>();
        insertions.add(c+s);
        insertions.add(s+c);
        for(int i=1;i<=s.length()-1;i++) {
            insertions.add(s.substring(0,i) + c + s.substring(i));
        }
        return insertions;
    }
    private void getPermutations(String s,Set<String> permutations) {
        int n = s.length();
        if(n == 1) {
            permutations.add(s);
            return;
        }
        getPermutations(s.substring(0,n-1),permutations);
        Iterator<String> it = permutations.iterator();
        List<String> newPermuts = new ArrayList<String>();
        while(it.hasNext()) {
            String ps = it.next();
            newPermuts.addAll(insert(ps,s.charAt(n-1)));
        }
        permutations.clear();
        permutations.addAll(newPermuts);
    }
    public List<Integer> findAnagrams(String s, String p) {
        Set<String> permutations = new HashSet<String>();
        getPermutations(p, permutations);
        List<Integer> indices = new ArrayList<Integer>();
        for(int i=0;i<s.length();i++) {
            for(String anagram : permutations) {
                if(s.substring(i).startsWith(anagram)) {
                    indices.add(i);
                }
            }
        }
        return indices;
    }

    // All Anagrams - memory efficient solution
    // Idea: Maintain the char count of p in a int[] of length 26 (as we have only lowercase characters).
    // Have a windowsize = p.length(), as we want to find strings of length p which are anagrams of p in s.
    // for the first window s.substring(0...windowsize), find the char count and store in a separate int[].
    // while a window still fits in s, check if this charcount of the window matches the charCount of the string p.
    // If so, add this startIndex as an anagramLoc. Now, delete the first char (at startIndex), and add new char (at startIndex+windowSize) which
    // was not included so far. Adjust the count array of the window accordingly. Repeat!

    int[] getCharCount(String s){
        int[] chars = new int[26];
        for(int i=0;i<s.length();i++){
            chars[s.charAt(i)-'a']+=1;
        }
        return chars;
    }
    boolean isMatch(int[] expCounts, int[] counts){
        boolean matches = true;
        for(int i=0;i<26;i++){
            if(expCounts[i] != counts[i]){
                matches = false;
                break;
            }
        }
        return matches;
    }
    public List<Integer> findAnagramsFaster(String s, String p) {
        List<Integer> anagramsLocs = new ArrayList<Integer>();
        if(p.length()> 0 && s.length()>=p.length()){
            int windowSize = p.length();
            int[] pCount = getCharCount(p);
            int startIndex =0;
            int[] windowCount = getCharCount(s.substring(startIndex, startIndex+windowSize));
            while(startIndex+windowSize <= s.length()){
                if(isMatch(pCount, windowCount)){
                    anagramsLocs.add(startIndex);
                }
                char charToBeRemoved = s.charAt(startIndex);
                windowCount[charToBeRemoved-'a']-=1;
                // add new next char
                if(startIndex+windowSize <s.length()){
                    char charToBeAdded = s.charAt(startIndex+windowSize);
                    windowCount[charToBeAdded-'a']+=1;
                }
                startIndex++;
            }
        }
        return anagramsLocs;
    }

    /**
     * https://leetcode.com/problems/permutation-in-string/
     * 
     * Same as previous, instead of finding the location of the anagrams, find if any anagram is included in the other string.
     * 
     * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

        

        Example 1:

        Input: s1 = "ab" s2 = "eidbaooo"
        Output: True
        Explanation: s2 contains one permutation of s1 ("ba").
        Example 2:

        Input:s1= "ab" s2 = "eidboaoo"
        Output: False
        

        Note:

        The input strings only contain lower case letters.
        The length of both given strings is in range [1, 10,000].
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length()> 0 && s2.length()>=s1.length()){
            int windowSize = s1.length();
            int[] s1count = getCharCount(s1);
            int startIndex = 0;
            boolean matchFound = false;
            int[] windowCount = getCharCount(s2.substring(startIndex, startIndex+windowSize));
            while(startIndex+windowSize <= s2.length()){
                if(isMatch(s1count, windowCount)){
                    matchFound=true;
                    return true;
                }
                char charToBeRemoved = s2.charAt(startIndex);
                windowCount[charToBeRemoved-'a']-=1;
                // add new next char
                if(startIndex+windowSize <s2.length()){
                    char charToBeAdded = s2.charAt(startIndex+windowSize);
                    windowCount[charToBeAdded-'a']+=1;
                }
                startIndex++;
            }
            return matchFound;
        }
        return s2.length()>s1.length();
    }
}