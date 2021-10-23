package datastructures.trees;

// https://leetcode.com/problems/short-encoding-of-words/
public class WordsEncodingTrie {
    class TrieNode{
        TrieNode[] children;
        boolean isEndOfWord;
        int wordLength;
        TrieNode(){
            children = new TrieNode[26];
            isEndOfWord = false;
            wordLength = 0;
        }
    }
    class Trie{
        TrieNode root;
        Trie(){
            root = new TrieNode();
        }
        void insertWord(String word){
            // inserts words in a reverse manner
            TrieNode curr = root;
            for(int i=word.length()-1;i>=0;i--){
                char c = word.charAt(i);
                int index = c-'a';
                if(curr.children[index] == null){
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
            }
            curr.isEndOfWord = true;
            curr.wordLength = word.length();
        }
        // time and me will be counted as same word, as we had inserted in reverse
        // rime will be a separate word.
        void computeEncompassingWordLengths(TrieNode curr, List<Integer> wordLengths){
            boolean hasSubsequentWords = false;
            for(int i=0;i<curr.children.length;i++){
                if(curr.children[i] != null){
                    hasSubsequentWords = true;
                    computeEncompassingWordLengths(curr.children[i], wordLengths);
                }
            }
            if(curr.isEndOfWord && !hasSubsequentWords){
                wordLengths.add(curr.wordLength);
            }
        }
        // Ex: time#tim# -> words: time, me, tim (only suffix matters)
        // Ex: time#rime# -> words: time, me, rime
        int computeShortestEncodingLen(){
            List<Integer> wordLengths = new ArrayList<>();
            computeEncompassingWordLengths(root, wordLengths);
            int numHashes = wordLengths.size(); // # inserted after each word
            int charCount = wordLengths.stream().mapToInt(Integer::intValue).sum();
            return numHashes + charCount;
        }
    }
    /**
     * A valid encoding of an array of words is any reference string s and array of indices indices such that:

        words.length == indices.length
        The reference string s ends with the '#' character.
        For each index indices[i], the substring of s starting from indices[i] and up to (but not including) the next '#' character is equal to words[i].
        Given an array of words, return the length of the shortest reference string s possible of any valid encoding of words.

        

        Example 1:

        Input: words = ["time", "me", "bell"]
        Output: 10
        Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
        words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
        words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
        words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
        Example 2:

        Input: words = ["t"]
        Output: 2
        Explanation: A valid encoding would be s = "t#" and indices = [0].

        Idea: since all suffixes of a word can be represented by the word itself (shortest encoding), we can insert words
        in reverse order to group words with common suffix. In such a trie, if we know a char is endofword and it has no more
        children, then it has no larger words which contain this as a suffix. Now count this length + 1 (for the hash).
        Do this for all unique words.

        Ex: words- time, me, rime
        e
        e->m (EOW with some children)
        e->m->i
        e->m->i->t (EOW with no children) = 4
        e->m->i->r (EOW with no children) = 4
        shortest enc len = 4+4+1+1 =10

     * @param words
     * @return
     */
    public int minimumLengthEncoding(String[] words) {
        Trie trie = new Trie();
        for(String word: words){
            trie.insertWord(word);
        }
        return trie.computeShortestEncodingLen();
    }
}
