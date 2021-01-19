package datastructures.trees;

import java.util.*;

/**
 * https://leetcode.com/problems/prefix-and-suffix-search/
 * 
 * Modify the trie structure to include # character. Trie facilitates only a prefix search, for doing a suffix search also, we need to do 
 * some preprocesing.
 * 
 * For a word like "test", insert -- "#test", "t#test", "st#test", "est#test", "test#test" into the Trie. 
 * Then if we have a query like prefix = "te", suffix = "t", we can find it by searching for something we've inserted starting with "t#te"
 * i.e sort of like autocomplete from prefix "t#te", then from all full words filter the one with largest weight.
 */
public class WordFilterTrie {

    class TrieNode{
        TrieNode[] children = new TrieNode[27];
        boolean isEndOfWord = false;
        int weight;
        TrieNode() {
            for(int i=0;i<children.length;i++) {
                children[i] = null;
            }
        }
    }
    TrieNode root;
    
    void insert(String word, int weight) {
        TrieNode curr=root;
        for(int i=0;i<word.length();i++) {
            char letter = word.charAt(i);
            int charIndex = 26;
            if(letter != '#'){
                charIndex = letter-'a';
            }
            if(curr.children[charIndex] == null) {
                curr.children[charIndex] = new TrieNode();
            }
            curr = curr.children[charIndex];
        }
        curr.isEndOfWord = true;
        curr.weight = weight;
    }
    
    public WordFilterTrie(String[] words) {
        root = new TrieNode();
        for(int i=0;i<words.length;i++) {
            // generate suffix prepended variations
            StringBuffer word = new StringBuffer(words[i] + "#" + words[i]);
            int j=0;
            insert(word.toString(), i);
            while(j<words[i].length()) {
                word.deleteCharAt(0);
                j++;
                insert(word.toString(), i);
            }
        }
    }
    
    void filter(TrieNode curr, String prefix, Map<String, Integer> candidates){
        if(curr == null){
            return;
        }
        for(int i=0;i<curr.children.length;i++) {
            if(curr.children[i] != null) {
                String extendedPrefix = new StringBuffer(prefix).append((char) (i+'a')).toString();
                if(curr.children[i].isEndOfWord) {
                    candidates.put(extendedPrefix, curr.children[i].weight);
                }
                filter(curr.children[i], extendedPrefix, candidates);
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        Map<String,Integer> candidates = new HashMap<>();
        int maxWeight = Integer.MIN_VALUE;
        
        // check if the prefix exits in the trie
        TrieNode curr = root;
        String finalPrefix = suffix + "#" + prefix;
        int i=0;
        for(;i<finalPrefix.length();i++) {
            char letter = finalPrefix.charAt(i);
            int charIndex = 26;
            if(letter != '#'){
                charIndex = letter-'a';
            }
            if(curr.children[charIndex] == null) {
                break;
            }
            curr = curr.children[charIndex];
        }
        if(i==finalPrefix.length()) {
            filter(curr, suffix + "#" + prefix, candidates);
            if(candidates.isEmpty() && curr.isEndOfWord) {
                maxWeight = curr.weight;
            } else {
                for(Map.Entry<String,Integer> entry: candidates.entrySet()){
                    if(entry.getValue() > maxWeight){
                        maxWeight = entry.getValue();
                    }
                }
            }
        }
        return maxWeight == Integer.MIN_VALUE ? -1 : maxWeight;
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */
