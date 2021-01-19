package javaproblems;

public class AmazonWordBreakTrie {
    
}
/*
You are given a (potentially large) list of words.

Some of these are compound words - the word can be broken down into parts and all parts of the word are also in the list.

Identify all combinations where one word can be composed of two or more words of the same list, and print them.

Example 1:
    Sample input: (component words can come in any order)
        ["bar", "foobar", "foo",  "foobarbaz", "foobarbarfoobar"]

    Sample output: (only for compound words)
        "foobar": ["foo", "bar"]
*/
      
// sort list of words by char count. O(nlogn)

// use Trie - stores a dict of unique words

// word => if the prefix exists in dict - could be compound - O(len of compound word) 
//      => not exists, insert into Trie O(m)

class TrieNode{
    TrieNode[] children;
    boolean isEndOfWord;
    TrieNode() {
        children = new TrieNode[26];// english letters (lowercase)
    }
}

class Trie{
    TrieNode root;
    Trie(){
        root = new TrieNode();
    }
    boolean doesPrefixExist(String prefix){
        // check if all chars in prefix exist in Trie
        for(int i=0;i<prefix.length();i++){
            int charIndex = prefix.charAt(i) - 'a';
            if(curr.children[charIndex] == null){
                return false;
            }
            curr = curr.children[charIndex];
        }
        return curr != null && curr.isEndOfWord;
    }
    
    private boolean canConstructWordUtil(String word, int index, String constructedSequence){
        if(index >= word.length()){
            return true;
        }
        int endIndex = index+1;
        for(;endIndex<=word.length();endIndex++){
            String prefix = word.substring(index, endIndex);
            if(doesPrefixExist(prefix)){
                // can break the compound word here
                return canConstructWordUtil(word, endIndex, constructedSequence + " " + prefix);
            }
        }
        return false;
    }
    
    boolean canConstructWord(String word){
        return canConstructWordUtil(word, 0, "");
    }
    
    void insertWord(String word){
        TrieNode curr = root;
        // insertion
        for(int i=0;i<word.length();i++){
            int charIndex = word.charAt(i) - 'a';
            if(curr.children[charIndex] == null){
                curr.children[charIndex] = new TrieNode();
            }
            curr = curr.children[charIndex];
        }
        curr.isEndOfWord = true;
    }
}

