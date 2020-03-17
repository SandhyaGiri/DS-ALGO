package datastructures.stacksandqueues;

import java.util.*;

/**
 * 
 * https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 * 
 * You are given a string s that consists of lower case English letters and brackets. 

Reverse the strings in each pair of matching parentheses, starting from the innermost one.

Your result should not contain any brackets.

 

Example 1:

Input: s = "(abcd)"
Output: "dcba"
Example 2:

Input: s = "(u(love)i)"
Output: "iloveu"
Explanation: The substring "love" is reversed first, then the whole string is reversed.
Example 3:

Input: s = "(ed(et(oc))el)"
Output: "leetcode"
Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
Example 4:

Input: s = "a(bcdefghijkl(mno)p)q"
Output: "apmnolkjihgfedcbq"
 */
public class ReverseParenthesis {
    
    public String reverseParentheses(String s) {
        Stack<String> stack = new Stack<String>();
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i) == ')') {
                // pop till ( is reached, insert reversed string back
                StringBuilder str = new StringBuilder();
                while(!"(".equals(stack.peek())) {
                    StringBuilder temp = new StringBuilder(stack.pop());
                    str.append(temp.reverse().toString());
                }
                stack.pop();
                stack.push(str.toString());
            } else {
                // push into stack
                stack.push(String.valueOf(s.charAt(i)));
            }
        }
        if(stack.size() == 1) {
            return stack.pop();
        } else {
            // example 4 - when all brackets have been exhausted, combine the strings from bottom of stack one by one (a, pmnolkjihgfedcb , q)
            StringBuilder str = new StringBuilder();
            while(!stack.isEmpty()) {
                StringBuilder temp = new StringBuilder(stack.pop());
                str.append(temp.reverse().toString());
            }
            return str.reverse().toString();
        }
    }
}