package algorithms.dp;

public class IsSubsequence {
    boolean isSubRecursive(String s, String t, int i, int j){
        if(i>= s.length() || j>=t.length()){
            return false;
        }
        if(s.charAt(i) == t.charAt(j)){
            return isSubRecursive(s, t, i+1, j+1);
        }
        return isSubRecursive(s, t, i, j+1); // only one option
    }
    /**
     * Given a string s and a string t, check if s is subsequence of t.

        A subsequence of a string is a new string which is formed from the original string
        by deleting some (can be none) of the characters without disturbing the relative positions
        of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

        Follow up:
        If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to
        check one by one to see if T has its subsequence. In this scenario, how would you change your code?

        Example 1:

        Input: s = "abc", t = "ahbgdc"
        Output: true
        Example 2:

        Input: s = "axc", t = "ahbgdc"
        Output: false

        Idea: same as lcs, but when chars don't match we can't exclude the char in s (as we do in lcs where lcs(x_m-1, y_n) or lcs(x_m, y_n-1)).
        We can only exclude the char on the string "t".
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();
        boolean dp[][] = new boolean[m+1][n+1];
        for(int j=0;j<=n;j++){
            dp[0][j] = true; // empty s is a subsequence of t
        }
        for(int i=1;i<=m;i++){
            dp[i][0] = false; // non empty s cannot be a subsequence of empty t
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]; // only if so far we have a subsequence                    
                } else {
                    dp[i][j] = dp[i][j-1]; // can't exclude char i on s, only move back on str t
                }
            }
        }
        return dp[m][n];
    }
}