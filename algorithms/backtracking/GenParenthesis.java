package algorithms.backtracking;

import java.util.*;

/**
 * https://leetcode.com/problems/generate-parentheses/
 * 
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 */
public class GenParenthesis {
    public void genParenUtil(int n, int l,int r, String s, List<String> combinations){
        if(l==0 && r== 0) {
            combinations.add(s);
            return;
        }
        if(l > 0) {
            genParenUtil(n, l-1,r,s+"(", combinations); 
        }
        if(r>0 && r>l) {
            genParenUtil(n, l,r-1,s+")", combinations); 
        }
    }
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<>();
        if(n>0) {
            genParenUtil(n, n-1,n,"(", combinations);
        }
        else {
           combinations.add(""); 
        }
        return combinations;
    }
}