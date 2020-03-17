package datastructures.strings;

import java.util.*;

/**
 * https://leetcode.com/problems/decode-string/
 * 
 * Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
s = "2[abc]13[cd]ef" return "abcabccdcdcdcdcdcdcdcdcdcdcdcdcdef" (repeat number can be more than a single digit)
 */
public class DecodeString {
    public String decodeString(String s) {
        Stack<String> stack = new Stack<String>();
        int i=0;
        while(i<s.length()){
            if(s.charAt(i) == ']'){
                // pop the stack until [ encontered build the string meanwhile
                StringBuilder str = new StringBuilder();
                while(!stack.empty() && !"[".equals(stack.peek())){
                    str.insert(0, stack.pop());
                }
                stack.pop(); // remove the '[' char
                // pop once more to get repeat number
                int repeatNum = Integer.valueOf(stack.pop());
                String envelopedString = str.toString();
                while(repeatNum>1){
                    str.append(envelopedString);
                    repeatNum--;
                }
                stack.push(str.toString());
                i++;
            } else {
                // push the char in the stack
                if(Character.isDigit(s.charAt(i))){
                    int endIndex =i;
                    while(Character.isDigit(s.charAt(endIndex))){
                        endIndex++;
                    }
                    stack.push(s.substring(i,endIndex));
                    i=endIndex;
                }else {
                    stack.push(String.valueOf(s.charAt(i)));   
                    i++;
                    // accumulate the string that follows until ] or [ is encountered?
                }
            }
        }
        if(stack.size()>1){
            StringBuilder str = new StringBuilder();
            while(!stack.empty()){
                str.insert(0,stack.pop());
            }
            return str.toString();
        }
        return stack.empty() ? "" : stack.pop();
    }
}