package datastructures.trees;

import java.util.*;

/**
 * 
 * 1. Check if a string of words, is formed exactly from the dictionary
 * 
 * We can modify trie to act like a dictionary of duplicate words - isEndOfWord can be made int to keep track of number of times that word occurs in 
 * the dictionary. So while searching, we can decrement this count for each new occurence of the word. That way we can check if a string can be formed
 * from the dictionary of words.
 * 
 * 2. Finding unique rows in a boolean matrix
 * 
 * Modify trie with alphabet_size =2 i.e each node will have only 2 children 0 or 1. Insert each row, and if it was inserted then it is unique,
 * print the row. Otherwise don't print the row. Enhance insert to return boolean indicating if insertion was done.
 * Time: O(row*col)
 * Space: O(row*col)
 * Less space is HashSet O(row) -- convert each row to a string, and insert in a set, if its already present in set then its not unique.
 */
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord;
    TrieNode() {
        this.isEndOfWord = false;
        for(int i=0;i<children.length;i++) {
            children[i] = null;
        }
    }
}

class TrieDT {
    TrieNode root;
    TrieDT() {
        root = new TrieNode();
    }
    public TrieNode getRoot() {
        return root;
    }

    public void insert(String word) {
        TrieNode curr = root;
        for(int level=0;level<word.length();level++) {
            int index = word.charAt(level) - 'a';
            if(curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode curr = root;
        for(int level=0;level<word.length();level++) {
            int index = word.charAt(level) - 'a';
            if(curr.children[index] == null) {
                return false;
            }
            curr = curr.children[index];
        }
        return curr != null && curr.isEndOfWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for(int level=0;level<prefix.length();level++){
            int index = prefix.charAt(level) - 'a';
            if(curr.children[index] == null) {
                return false;
            }
            curr = curr.children[index];
        }
        return curr != null;
    }

    /**
     * Ex words in dict: ["bad"],["dad"],["mad"],["pad"],["bad"]
     * Search words: [".ad"],["b.."],["..."],["m..ui"]
     * Result: false,true,true,true,true,false
     */
    public boolean dfsSearch(String word, int index, TrieNode curr) {
        if(index == word.length()){
            return curr != null && curr.isEndOfWord;
        }
        char currChar = word.charAt(index);
        if(currChar != '.') {
            int charIndex = currChar -'a';
            return curr.children[charIndex] != null && dfsSearch(word, index+1, curr.children[charIndex]);
        } else {
            // search for any children
            boolean anyChildPresent = false;
            for(int i=0;i<curr.children.length;i++) {
                if(curr.children[i] != null) {
                    anyChildPresent = anyChildPresent || dfsSearch(word, index+1, curr.children[i]);
                }
            }
            return anyChildPresent;
        }
    }
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean regexSearch(String word) {
        TrieNode curr = root;
        return dfsSearch(word, 0, curr);
    }

    /**
     * By default pre-order traversal of the trie, results in a sorted list of strings stored in it.
     */
    public void printPreorder(TrieNode node, String commonPrefix) {
        if(node == null) {
            return;
        }
        for(int i=0;i<node.children.length;i++) {
            if(node.children[i] != null) {
                String prefix = new StringBuilder(commonPrefix).append((char)(i+'a')).toString();
                if(node.children[i].isEndOfWord) {
                    System.out.println(prefix);
                }
                printPreorder(node.children[i], prefix);
            }
        }
    }

    public void autocomplete(String prefix) {
        TrieNode curr = root;
        int level = 0;
        for(;level<prefix.length();level++) {
            int index = prefix.charAt(level) - 'a';
            if(curr.children[index] == null) {
                break;
            }
            curr = curr.children[index];
        }
        if(level == prefix.length()) {
            // Prefix found in trie
            boolean anyChildPresent = false;
            for(int i=0;i<curr.children.length;i++) {
                if(curr.children[i] != null) {
                    anyChildPresent = true;
                }
            }
            if(!anyChildPresent) {
                System.out.println("No autocomplete strings found for " + prefix);
            } else {
                printPreorder(curr, prefix); // Will print suggestions only when suggestions exist i.e curr node has any children
            }
        }
    }

    public void delete(String word) {
        if(search(word)) {
            TrieNode curr = root;
            int level =0;
            int index = word.charAt(level) - 'a';
            curr = curr.children[index];
            for(level=1;level<word.length();level++) {
                index = word.charAt(level) - 'a';
                if(!isOnlyChild(curr, index)) {
                    break;
                }
                curr = curr.children[index];
            }
            if(curr != null && curr.isEndOfWord && level == word.length()-1) {
                // Unique word, can be deleted.
                int startIndex = word.charAt(0) - 'a';
                root.children[startIndex] = null;
            } else {
                // word shares a prefix with some other word in trie
                // 1. word could have a another child word branching off. or 2. no sub child
                int sharingLevel = level;
                index = word.charAt(sharingLevel) - 'a';
                TrieNode prev = curr;
                TrieNode sharingCurr = curr.children[index];
                sharingLevel++;
                for(;sharingLevel<word.length();sharingLevel++) {
                    index = word.charAt(sharingLevel) - 'a';
                    if(!isOnlyChild(sharingCurr, index)) {
                        break;
                    }
                    prev = sharingCurr;
                    sharingCurr = sharingCurr.children[index];
                }
                if(sharingLevel == word.length()) {
                    // case 2, delete the substring starting from level.
                    index = word.charAt(level) - 'a';
                    curr.children[index] = null;
                } else {
                    // case 1, delete the substring starting from sharingLevel.
                    index = word.charAt(sharingLevel) - 'a';
                    prev.children[index] = null;
                }
            }
        }
    }

    private boolean isOnlyChild(TrieNode trieNode, int index) {
        if(trieNode != null) {
            for(int i=0;i<trieNode.children.length;i++) {
                if(trieNode.children[i] != null && i != index) {
                    return false;
                }
            }
            return trieNode.children[index] != null; 
        }
        return false;
    }
}

public class Trie {
    public static void main(String args[]) {
        TrieDT trieDT = new TrieDT();
        String keys[] = {"the", "a", "there", "answer", "any", 
                         "by", "bye", "their"};
        for(int i=0;i<keys.length;i++) {
            trieDT.insert(keys[i]);
        }
        trieDT.printPreorder(trieDT.getRoot(), "");
        String word = keys[3];
        System.out.println(word + " is present in Trie? " + trieDT.search(word));
        System.out.println("Suggestions for prefix: an");
        trieDT.autocomplete("an");
        System.out.println("deleting the word: " + word);
        trieDT.delete(word);
        System.out.println(word + " is present in Trie? " + trieDT.search(word));
        word = keys[1];
        System.out.println(word + " is present in Trie? " + trieDT.search(word));
        trieDT.printPreorder(trieDT.getRoot(), "");
    }
}