package algorithms.backtracking;

import java.util.*;

// https://leetcode.com/problems/palindrome-partitioning/
public class PalindromePartition {
    boolean isPalindrome(String s){
        int l=0,r=s.length()-1;
        while(l<=r){
            char x = s.charAt(l);
            char y = s.charAt(r);
            if(x == y){
                l++;
                r--;
            } else {
                break;
            }
        }
        return l>=r;
    }

    void partitionDfs(String s, int index, List<String> currPalins, List<List<String>> result){
        if(index >= s.length()){
            result.add(currPalins);
            return;
        }
        int endIndex=index+1;
        for(;endIndex<=s.length();endIndex++){
            String substr = s.substring(index, endIndex);
            if(isPalindrome(substr)){
                List<String> currPalinClone = new ArrayList<>();
                currPalinClone.addAll(currPalins);
                currPalinClone.add(substr);
                partitionDfs(s, endIndex, currPalinClone, result);
            }
        }
    }
    
    /**
     * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

        A palindrome string is a string that reads the same backward as forward.

        Example 1:

        Input: s = "aab"
        Output: [["a","a","b"],["aa","b"]]
        Example 2:

        Input: s = "a"
        Output: [["a"]]
        

        Constraints:

        1 <= s.length <= 16
        s contains only lowercase English letters.

        Idea: Similar to workbreak trie (break whenever you see a palindrome) and build the remaining later.
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        partitionDfs(s, 0, new ArrayList<>(), result);
        return result;
    }
}
