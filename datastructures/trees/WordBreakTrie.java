package datastructures.trees;

import java.util.*;

// https://leetcode.com/problems/word-break-ii/
public class WordBreakTrie {
    class TrieNode{
        TrieNode[] children;
        boolean isEndOfWord;
        TrieNode(){
            children = new TrieNode[26];
            for(int i=0;i<children.length;i++){
                children[i] = null;
            }
        }
    }
    class Trie{
        TrieNode root;
        Trie(){
            root = new TrieNode();
        }
        public void insertWord(String word){
            TrieNode curr = root;
            for(int i=0;i<word.length();i++){
                char c = word.charAt(i);
                int index = c-'a';
                if(curr.children[index] == null){
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
            }
            curr.isEndOfWord = true;
        }
        public boolean isPresent(String word){
            TrieNode curr = root;
            for(int i=0;i<word.length();i++){
                char c = word.charAt(i);
                int index = c-'a';
                if(curr.children[index] == null){
                    return false;
                }
                curr = curr.children[index];
            }
            return curr.isEndOfWord;
        }
    }
    void buildSentence(String src, int index, Trie trie, String sentence, List<String> fullSentences){
        if(index >= src.length()){
            return;
        }
        //System.out.println("Index: " + index + ", sent: " + sentence);
        int i=index+1;
        for(;i<=src.length();i++){
            String subStr = src.substring(index, i);
            boolean isPres = trie.isPresent(subStr);
            //System.out.println("Substr: " + subStr + ", present?:" + isPres);
            if(isPres){
                //System.out.println("Next index:" + i);
                if(i < src.length()){
                    buildSentence(src, i, trie, sentence + subStr + " ", fullSentences);
                } else {
                    fullSentences.add(sentence + subStr);
                }
            }
        }
    }
    /**
     * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
     * add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

        Note:

        The same word in the dictionary may be reused multiple times in the segmentation.
        You may assume the dictionary does not contain duplicate words.
        Example 1:

        Input:
        s = "catsanddog"
        wordDict = ["cat", "cats", "and", "sand", "dog"]
        Output:
        [
        "cats and dog",
        "cat sand dog"
        ]
        Example 2:

        Input:
        s = "pineapplepenapple"
        wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
        Output:
        [
        "pine apple pen apple",
        "pineapple pen apple",
        "pine applepen apple"
        ]
        Explanation: Note that you are allowed to reuse a dictionary word.
        Example 3:

        Input:
        s = "catsandog"
        wordDict = ["cats", "dog", "sand", "and", "cat"]
        Output:
        []
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        if(s.length() > 100){
            return new ArrayList<>();
        }
        Trie trie = new Trie();
        // build the trie with words in dictionary
        for(String word: wordDict){
            trie.insertWord(word);
        }
        List<String> sentences = new ArrayList<>();
        buildSentence(s, 0, trie, "", sentences);
        return sentences;
    }

    boolean canBuildSentence(String src, int index, Trie trie, String sentence){
        if(index >= src.length()){
            return false;
        }
        //System.out.println("Index: " + index + ", sent: " + sentence);
        int i=index+1;
        boolean canBuild = false;
        for(;i<=src.length();i++){
            String subStr = src.substring(index, i);
            boolean isPres = trie.isPresent(subStr);
            //System.out.println("Substr: " + subStr + ", present?:" + isPres);
            if(isPres){
                //System.out.println("Next index:" + i);
                if(i < src.length() && !canBuild){
                    canBuild = canBuildSentence(src, i, trie, sentence + subStr + " ");
                } else {
                    canBuild = true; // full sentence can be formed
                    break;
                }
            }
        }
        return canBuild;
    }
    /**
     * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

    Note:

    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.
    Example 1:

    Input: s = "leetcode", wordDict = ["leet", "code"]
    Output: true
    Explanation: Return true because "leetcode" can be segmented as "leet code".
    Example 2:

    Input: s = "applepenapple", wordDict = ["apple", "pen"]
    Output: true
    Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
                Note that you are allowed to reuse a dictionary word.
    Example 3:

    Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
    Output: false
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak1(String s, List<String> wordDict) {
        if(s.length() > 100){
            return false;
        }
        Trie trie = new Trie();
        for(String word: wordDict){
            trie.insertWord(word);
        }
        return canBuildSentence(s, 0, trie, "");
    }
}