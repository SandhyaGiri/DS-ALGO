package datastructures.arrays;

import java.util.*;

public class Permutation {
    /**
     * https://leetcode.com/problems/permutations/
     * 
     * Given a collection of distinct integers, return all possible permutations.

        Example:

        Input: [1,2,3]
        Output:
        [
        [1,2,3],
        [1,3,2],
        [2,1,3],
        [2,3,1],
        [3,1,2],
        [3,2,1]
        ]
     */
    List<List<Integer>> permuteUtil(int[] nums, int index){
        if(index < 0){
            return List.of(List.of());
        }
        List<List<Integer>> oldPermuts = permuteUtil(nums, index-1);
        List<List<Integer>> newPermuts = new ArrayList<>();
        for(List<Integer> permutation: oldPermuts){
            for(int i=0;i<=permutation.size();i++){
                List<Integer> newPermut = new ArrayList<>();
                newPermut.addAll(permutation);
                newPermut.add(i, nums[index]);
                newPermuts.add(newPermut);
            }
        }
        return newPermuts;
    }
    public List<List<Integer>> permute(int[] nums) {
        return permuteUtil(nums, nums.length-1);
    }
}

// https://leetcode.com/problems/permutations-ii/
class UniquePermutation {
    class TrieNode{
        TrieNode[] children;
        boolean isEndOfWord;
        TrieNode(){
            this.children = new TrieNode[21];
            this.isEndOfWord = false;
        }
    }
    class Trie{
        TrieNode root;
        Trie(){
            root = new TrieNode();
        }
        boolean insertPermutation(List<Integer> permute){
            TrieNode curr = root;
            boolean alreadyPresent = true;
            for(int i=0;i<permute.size();i++){
                int index = permute.get(i) + 10; // in order to convert -10 to index 0
                if(curr.children[index] == null){
                    alreadyPresent = false;
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
            }
            if(!alreadyPresent){
                alreadyPresent = curr.isEndOfWord;
            }
            curr.isEndOfWord = true; // new bigger length string inserted.
            return alreadyPresent;
        }
    }
    
    List<List<Integer>> genPermutations(int[] nums, int index){
        if(index < 0){
            return List.of(List.of());
        }
        List<List<Integer>> prevPermutations = genPermutations(nums, index-1);
        List<List<Integer>> newPermutations = new ArrayList<>();
        for(List<Integer> permute : prevPermutations){
            // insert nums[index] at every location in permute
            for(int i=0;i<=permute.size();i++){
                List<Integer> extendedPermute = new ArrayList<>();
                extendedPermute.addAll(permute);
                extendedPermute.add(i, nums[index]);
                newPermutations.add(extendedPermute);
            }
        }
        return newPermutations;
    }
    /**
     * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.

        Example 1:

        Input: nums = [1,1,2]
        Output:
        [[1,1,2],
        [1,2,1],
        [2,1,1]]
        Example 2:

        Input: nums = [1,2,3]
        Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

        Idea: generate all permutations, and then check if duplicates exist by inserting them into an trie datastructure.
        If the permutation already exists, then don't add to result.
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // generate all perms
        List<List<Integer>> permutations = genPermutations(nums, nums.length-1);
        // insert them into Trie (filters duplicates)
        List<List<Integer>> result = new ArrayList<>();
        Trie trie = new Trie();
        for(List<Integer> permute : permutations){
            boolean alreadyPresent = trie.insertPermutation(permute);
            if(!alreadyPresent){
                result.add(permute);
            }
        }
        return result;
    }
}