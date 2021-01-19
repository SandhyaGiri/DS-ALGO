package datastructures.strings;

// https://leetcode.com/problems/longest-palindromic-substring/
public class LongestPalindromicSubstr {
    int findLargestPalindromicSubstr(String s, int L, int R){
        while(L>=0 && R<s.length()){
            char x = s.charAt(L);
            char y = s.charAt(R);
            if(x == y){
                L--;
                R++; // expand around the given substring from L..R
            } else {
                break;
            }
        }
        return R-L-1; // L and R pointers are already one ahead
    }
    /**
     * Given a string s, return the longest palindromic substring in s.

        Example 1:

        Input: s = "babad"
        Output: "bab"
        Note: "aba" is also a valid answer.
        Example 2:

        Input: s = "cbbd"
        Output: "bb"
        Example 3:

        Input: s = "a"
        Output: "a"
        Example 4:

        Input: s = "ac"
        Output: "a"
        

        Constraints:

        1 <= s.length <= 1000
        s consist of only digits and English letters (lower-case and/or upper-case),

        Time: O(n*n), Space: O(1)

        Idea: Expand Around Center: In fact, we could solve it in O(n^2) time using only constant space.
        We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center,
        and there are only 2n - 1 such centers.
        You might be asking why there are 2n - 1 but not n centers? The reason is the center of a palindrome
        can be in between two letters. Such palindromes have even number of letters (such as "abba") and its center are between the two 'b's.
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int start=0, end=0;
        for(int i=0;i<s.length();i++){
            int oddLen = findLargestPalindromicSubstr(s, i, i); // odd len palin centered at i
            int evenLen = findLargestPalindromicSubstr(s, i, i+1); // even len palin centered around i and i+1
            int len = Math.max(oddLen, evenLen);
            if(len > end-start){
                start = i - (len-1)/2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end+1);
    }
}
