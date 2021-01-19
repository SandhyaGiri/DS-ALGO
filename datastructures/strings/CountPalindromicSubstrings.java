package datastructures.strings;

// https://leetcode.com/problems/palindromic-substrings/
public class CountPalindromicSubstrings {
    /**
     * Given a string, your task is to count how many palindromic substrings in this string.

        The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

        Example 1:

        Input: "abc"
        Output: 3
        Explanation: Three palindromic strings: "a", "b", "c".
        

        Example 2:

        Input: "aaa"
        Output: 6
        Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

        Idea: similar to LongestPalindromic substring, use expand around the center technique-> Time: O(N*N), space: O(1)
     * @param l
     * @param r
     * @param s
     * @return
     */
    int getNumPalindromesCenteredAt(int l, int r, String s){
        // starting substr l..r or just l'th char when l==r
        int numPalin = 0; 
        while(l>=0 && r<s.length()){
            char x = s.charAt(l);
            char y = s.charAt(r);
            if(x == y){
                l--;
                r++;
                numPalin += 1;
            } else {
                break;
            }
        }
        return numPalin;
    }
    public int countSubstrings(String s) {
        int numPalindromes =0;
        for(int i=0;i<s.length();i++){
            numPalindromes += getNumPalindromesCenteredAt(i, i, s); // odd length palindrome
            numPalindromes += getNumPalindromesCenteredAt(i, i+1, s); // even length palindrome
        }
        return numPalindromes;
    }
}
