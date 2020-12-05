package datastructures.trees;

// https://leetcode.com/problems/stream-of-characters/
public class StreamCheckerTrie {
    class TrieNode{
        TrieNode[] children;
        boolean isEndOfWord;
        TrieNode(){
            this.children = new TrieNode[26];
            for(int i=0;i<26;i++){
                this.children[i] = null;
            }
        }
    }
    class Trie{
        TrieNode root;
        Trie(){
            root = new TrieNode();
        }
        void insert(String word){
            TrieNode curr = root;
            // store in reverse, so we can do O(n) search for each query
            for(int i=word.length()-1;i>=0;i--){
                int index = word.charAt(i) - 'a';
                if(curr.children[index] == null){
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
            }
            curr.isEndOfWord = true;
        }
        boolean hasASubWord(String word){
            TrieNode curr = root;
            for(int i=word.length()-1;i>=0;i--){
                int index = word.charAt(i) - 'a';
                if(curr.children[index] == null){
                    return false;
                }
                curr = curr.children[index];
                if(curr.isEndOfWord){
                    return true; // returns when the first subword found!
                }
            }
            return curr.isEndOfWord;   
        }
    }
    Trie trie;
    String str;
    public StreamCheckerTrie(String[] words) {
        trie = new Trie();
        str = "";
        for(int i=0;i<words.length;i++){
            trie.insert(words[i]);
        }
    }
    
    /**
     * Implement the StreamChecker class as follows:

        StreamChecker(words): Constructor, init the data structure with the given words.
        query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest, including this letter just queried) spell one of the words in the given list.
        

        Example:

        StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
        streamChecker.query('a');          // return false
        streamChecker.query('b');          // return false
        streamChecker.query('c');          // return false
        streamChecker.query('d');          // return true, because 'cd' is in the wordlist
        streamChecker.query('e');          // return false
        streamChecker.query('f');          // return true, because 'f' is in the wordlist
        streamChecker.query('g');          // return false
        streamChecker.query('h');          // return false
        streamChecker.query('i');          // return false
        streamChecker.query('j');          // return false
        streamChecker.query('k');          // return false
        streamChecker.query('l');          // return true, because 'kl' is in the wordlist
        

        Note:

        1 <= words.length <= 2000
        1 <= words[i].length <= 2000
        Words will only consist of lowercase English letters.
        Queries will only consist of lowercase English letters.
        The number of queries is at most 40000.

        IDEA: if we store words in a TRIE and then during query(), we need to check each substring
        starting at k>=0 and ending at current char. This would mean O(K*n) complexity if K is number
        of chars gathered so far.

        We can reduce the complecity of query() to O(K) if we store the initial words in reverse order
        inside the trie. Now for the current string of chars, do a single search going thru chars in
        reverse and return true on finding the first endofword! => only O(K) complexity.
     * @param letter
     * @return
     */
    public boolean query(char letter) {
        str = str + letter;
        //System.out.println(str.toString());
        // check for entire string
        if(trie.hasASubWord(str.toString())){
            return true;
        }
        return false;
    }
}