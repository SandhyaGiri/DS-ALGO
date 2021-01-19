package algorithms.dp;

public class LongestPalindromicSubsequence {
    // https://leetcode.com/problems/longest-palindromic-subsequence/
    /**
     * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

        Example 1:
        Input:

        "bbbab"
        Output:
        4
        One possible longest palindromic subsequence is "bbbb".
        

        Example 2:
        Input:

        "cbbd"
        Output:
        2
        One possible longest palindromic subsequence is "bb".
        

        Constraints:

        1 <= s.length <= 1000
        s consists only of lowercase English letters.

        Idea: same as LCS, with second string being the reversed version of s.
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] lpsLength = new int[n+1][n+1];
    
        String reversed = new StringBuilder(s).reverse().toString();
        for(int i=1;i<=n;i++){ // original dir
            for(int j=1;j<=n;j++){ // reverse string
                char x = s.charAt(i-1);
                char y = reversed.charAt(j-1);
                if(x == y){
                    lpsLength[i][j] = 1+lpsLength[i-1][j-1];
                } else {
                    lpsLength[i][j] = Math.max(lpsLength[i-1][j], lpsLength[i][j-1]);
                }
            }
        }
        //System.out.println(Arrays.toString(lpsLength[3]));
        return lpsLength[n][n];
    }
}
