package datastructures.strings;

import java.util.regex.*;

public class Palindrome {
    public boolean isPalindrome(String s) {
        int start=0, end=s.length()-1;
        boolean result = true;
        Pattern alphanumeric = Pattern.compile("[a-zA-Z0-9]");
        while(start < end){
            char c1 = s.charAt(start);
            char c2 = s.charAt(end);
            //System.out.println(c1 +"," +c2);
            Matcher char1Matcher = alphanumeric.matcher(c1+"");
            Matcher char2Matcher = alphanumeric.matcher(c2+"");
            if(!char1Matcher.matches()){
                start++;
                continue;
            }
            if(!char2Matcher.matches()){
                end--;
                continue;
            }
            if(Character.toLowerCase(c1) != Character.toLowerCase(c2)){
                result=false;
                break;
            }
            //System.out.println("here");
            start++;
            end--;
        }
        return result;
    }

    // https://leetcode.com/problems/remove-palindromic-subsequences/
    boolean isPalindrome2(String s){
        int l=0,r=s.length()-1;
        while(l<=r){
            if(s.charAt(l) != s.charAt(r)){
                return false;
            } else {
                l++;
                r--;
            }
        }
        return true;
    }
    /**
     * Given a string s consisting only of letters 'a' and 'b'. In a single step you can remove one palindromic subsequence from s.

        Return the minimum number of steps to make the given string empty.

        A string is a subsequence of a given string, if it is generated by deleting some characters of a given string without changing its order.

        A string is called palindrome if is one that reads the same backward as well as forward.

        

        Example 1:

        Input: s = "ababa"
        Output: 1
        Explanation: String is already palindrome
        Example 2:

        Input: s = "abb"
        Output: 2
        Explanation: "abb" -> "bb" -> "". 
        Remove palindromic subsequence "a" then "bb".
        Example 3:

        Input: s = "baabb"
        Output: 2
        Explanation: "baabb" -> "b" -> "". 
        Remove palindromic subsequence "baab" then "b".
        Example 4:

        Input: s = ""
        Output: 0
 
        Idea: Since str has only 2 chars and we can remove subsequences NOT substrings, we can remove all a's as a single
        palindromic subsequence and then b's. that is 2 steps, we can avoid this if entire string itself were a palindrome.
     * @param s
     * @return
     */
    public int removePalindromeSub(String s) {
        if(s.length() == 0){
            return 0;
        }
        // max 2 steps! - if s is not a palin, then remove all a's first, then b's - 2 steps
        // if s is a palin, remove the entire string once - 1 step
        return isPalindrome2(s) ? 1 : 2;
    }
}