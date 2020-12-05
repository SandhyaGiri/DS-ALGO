package algorithms.backtracking;

import java.util.*;

public class WordSearch {
    boolean dfsUtil(char[][] board, boolean[][] visited, String word, int index, int x, int y){
        if(index == word.length()){
            return true;
        }
        if(x <0 || x>=board.length || y<0 || y>=board[0].length){
            return false;
        }
        if(visited[x][y] || word.charAt(index) != board[x][y]){
            return false;
        }
        // mark as visited
        visited[x][y] = true;
        // check 4 neighbors
        boolean exists = false;
        if(dfsUtil(board, visited, word, index+1, x+1, y) ||
          dfsUtil(board, visited, word, index+1, x-1, y) ||
          dfsUtil(board, visited, word, index+1, x, y+1) ||
          dfsUtil(board, visited, word, index+1, x, y-1)){
            exists = true;
        }
        if(!exists){
           visited[x][y] = false;
        }
        //System.out.println("Exists: " + exists + ", x:" + x + ",y:" + y);
        return exists;
    }
    // https://leetcode.com/problems/word-search/
    /**
     * Given a 2D board and a word, find if the word exists in the grid.

        The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

        Example:

        board =
        [
        ['A','B','C','E'],
        ['S','F','C','S'],
        ['A','D','E','E']
        ]

        Given word = "ABCCED", return true.
        Given word = "SEE", return true.
        Given word = "ABCB", return false.

        Idea: Similar to DFS, but at any time we only explore 4 neighbors, diagonal ones are unvisited.
        If the word path cannot be found through a node, mark is as NOT VISITED in backtracking stage,
        as there could paths from diagonal nodes later on.
        Visited array is needed to ensure we don't go back and use chars which were already counted!
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        // word could start at any of the cells
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j] && dfsUtil(board, visited, word, 0, i, j)){
                    return true;
                }
            }
        }
        return false;
    }
}

// https://leetcode.com/problems/word-search-ii/
class WordSearch2{
    class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord;
        TrieNode(){
            isEndOfWord = false;
            for(int i=0;i<children.length;i++){
                children[i]=null;
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
    }
    boolean dfsUtil(char[][] board, boolean[][] visited, String curr, int x, int y, TrieDT trie, List<String> result){
        if(x<0 || x>=board.length || y<0 || y>=board[0].length){
            return false;
        }
        //System.out.println("x:"+x+",y:"+y+",curr:"+curr);
        curr = curr+board[x][y];
        if(visited[x][y] || !trie.startsWith(curr)){
            //System.out.println("no prefix"+ ",x:"+x+",y:"+y);
            return false;
        }
        if(trie.search(curr) && !result.contains(curr)){
            //System.out.println("present"+ ",x:"+x+",y:"+y+",curr:"+curr);
            result.add(curr);
        }
        visited[x][y] = true;
        boolean exists = false;
        if(
        dfsUtil(board, visited, curr, x+1, y, trie, result) ||
        dfsUtil(board, visited, curr, x-1, y, trie, result) ||
        dfsUtil(board, visited, curr, x, y+1, trie, result) ||
        dfsUtil(board, visited, curr, x, y-1, trie, result)){
            exists = true;
        }
        visited[x][y] = false;
        return exists;
    }
    /**
     * Given a 2D board and a list of words from the dictionary, find all words in the board.

        Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

        

        Example:

        Input: 
        board = [
        ['o','a','a','n'],
        ['e','t','a','e'],
        ['i','h','k','r'],
        ['i','f','l','v']
        ]
        words = ["oath","pea","eat","rain"]

        Output: ["eat","oath"]

        Idea: Traverse through board for all word combinations. backtrack when current prefix doesn't exist in TRIE created
        using the words. If prefix does exist, check if it is also a complete word then add to result list. Continue checking neighbors.
        if no word was dound through a node, then mark as NOT VISITED for future searches to succeed.

        Hint: You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?
        Hint: If the current candidate does not exist in all words' prefix, you could stop backtracking immediately.
        What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not?
        How about a Trie?
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;
        // build a trie with words (dict) given
        TrieDT trie = new TrieDT();
        for(int i=0;i<words.length;i++){
            trie.insert(words[i]);
        }
        List<String> result = new ArrayList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                boolean[][] visited = new boolean[m][n];
                dfsUtil(board, visited, "", i, j, trie, result);
            }
        }
        return result;
    }
}