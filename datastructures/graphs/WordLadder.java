package datastructures.graphs;

import java.util.*;

public class WordLadder {

    class WordPair{
        String word;
        WordPair parent;
        WordPair(String word, WordPair parent){
            this.word = word;
            this.parent = parent;
        }
    }

    List<String> getIntermediateWords(String word){
        List<String> result = new ArrayList<>();
        for(int i=0;i<word.length();i++){
            StringBuilder trans = new StringBuilder();
            if(i>0){
                trans.append(word.substring(0, i));
            }
            trans.append("*");
            if(i+1 < word.length()){
                trans.append(word.substring(i+1));
            }
            result.add(trans.toString());
        }
        return result;
    }

    void addIntermediateWords(List<List<String>> result, WordPair finalWordPair){
        LinkedList<String> intermediateWords = new LinkedList<>();
        while(finalWordPair !=null){
                intermediateWords.addFirst(finalWordPair.word);
                finalWordPair = finalWordPair.parent;
        }
        result.add(intermediateWords);
    }

    // https://leetcode.com/problems/word-ladder-ii/
    /**
     * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

        Only one letter can be changed at a time
        Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
        Note:

        Return an empty list if there is no such transformation sequence.
        All words have the same length.
        All words contain only lowercase alphabetic characters.
        You may assume no duplicates in the word list.
        You may assume beginWord and endWord are non-empty and are not the same.
        Example 1:

        Input:
        beginWord = "hit",
        endWord = "cog",
        wordList = ["hot","dot","dog","lot","log","cog"]

        Output:
        [
        ["hit","hot","dot","dog","cog"],
        ["hit","hot","lot","log","cog"]
        ]
        Example 2:

        Input:
        beginWord = "hit"
        endWord = "cog"
        wordList = ["hot","dot","dog","lot","log"]

        Output: []

        Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

        Idea: illustrates how path from start to goal node can be tracked using BFS (with help of parent pointers).
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // maps intermediate string to list of words
        Map<String, List<String>> transformationMap = new HashMap<>();
        for(String word: wordList){
            List<String> intermediateStates = getIntermediateWords(word);
            for(String state: intermediateStates){
                if(transformationMap.containsKey(state)){
                    transformationMap.get(state).add(word);
                }else{
                    List<String> words = new ArrayList<>();
                    words.add(word);
                    transformationMap.put(state, words);
                }
            }
        }
        Queue<WordPair> nodes = new LinkedList<>();
        Set<String> visitedWords = new HashSet<>();
        nodes.add(new WordPair(beginWord, null));
        boolean endWordFound = false;
        List<List<String>> result = new ArrayList<>();
        while(!nodes.isEmpty()){
            int levelSize = nodes.size();
            for(int i=0;i<levelSize;i++){
                // find words at distance 1 and add them to queue
                WordPair currWordPair = nodes.poll();
                visitedWords.add(currWordPair.word);

                if(currWordPair.word.equals(endWord)){

                    endWordFound = true;
                    addIntermediateWords(result, currWordPair);
                    continue;
                }

                List<String> intermediateStates = getIntermediateWords(currWordPair.word);
                for(String state: intermediateStates){
                    if(transformationMap.containsKey(state)){
                        for(String nextWord: transformationMap.get(state)){
                            if(!visitedWords.contains(nextWord))
                            {
                                nodes.add(new WordPair(nextWord, currWordPair));
                            }
                        }
                    }
                }
            }
            if(endWordFound){ // stop at the shallowest goal node level
                break;
            }
        }
        return result;
    }

    // https://leetcode.com/problems/word-ladder/
    /**
     * Given two words beginWord and endWord, and a dictionary wordList, return the length of the shortest transformation sequence from beginWord to endWord, such that:

    Only one letter can be changed at a time.
    Each transformed word must exist in the word list.
    Return 0 if there is no such transformation sequence.
    
    Idea: number of levels in BFS until a goal node is found.
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // maps intermediate string to list of words
        Map<String, List<String>> transformationMap = new HashMap<>();
        for(String word: wordList){
            List<String> intermediateStates = getIntermediateWords(word);
            for(String state: intermediateStates){
                if(transformationMap.containsKey(state)){
                    transformationMap.get(state).add(word);
                }else{
                    List<String> words = new ArrayList<>();
                    words.add(word);
                    transformationMap.put(state, words);
                }
            }
        }
        Queue<String> nodes = new LinkedList<>();
        nodes.add(beginWord);
        Set<String> visitedWords = new HashSet<>();
        int level = 0;
        boolean goalNodeFound = false;
        while(!nodes.isEmpty()){
            int levelSize = nodes.size();
            for(int i=0;i<levelSize;i++){
                String word = nodes.poll();
                visitedWords.add(word);
                if(endWord.equals(word)){
                    goalNodeFound = true;
                    break;
                }
                List<String> intermediateStates = getIntermediateWords(word);
                // System.out.println(intermediateStates);
                for(String interState: intermediateStates){
                    if(transformationMap.containsKey(interState)){
                        // System.out.println(transformationMap.get(interState));
                        for(String neighbor: transformationMap.get(interState)){
                            if(!visitedWords.contains(neighbor)){
                                nodes.add(neighbor);
                            }
                        }
                    }
                }
            }
            level += 1;
            if(goalNodeFound){
                return level;
            }
        }
        return 0;
    }
}
