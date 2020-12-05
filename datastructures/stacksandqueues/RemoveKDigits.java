package datastructures.stacksandqueues;

import java.util.*;

public class RemoveKDigits {
    /**
     * https://leetcode.com/problems/remove-k-digits/
     * 
     * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

        Note:
        The length of num is less than 10002 and will be ≥ k.
        The given num does not contain any leading zero.
        Example 1:

        Input: num = "1432219", k = 3
        Output: "1219"
        Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
        Example 2:

        Input: num = "10200", k = 1
        Output: "200"
        Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
        Example 3:

        Input: num = "10", k = 2
        Output: "0"
        Explanation: Remove all the digits from the number and it is left with nothing which is 0.

        Idea: remove higher valued digits at high significant place. Traverse from left to right.
        On seeing a smallar digit than stack top, pop the stack and insert this.
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        if(k == num.length()){
            return "0";
        }
        if(k == 0){
            return num;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int limit = k;
        int index = 0;
        while(index < num.length() && (limit>0 || !stack.empty())){
            int curr = Character.getNumericValue(num.charAt(index));
            if(!stack.empty()){
                while(limit>0 && !stack.empty() && curr < stack.peek()){
                    System.out.println(curr +"," + stack.peek());
                    stack.pop();
                    limit--;
                }
                stack.push(curr);
            } else {
                stack.push(curr);
            }
            index++;
        }
        // stack has the numbers
        StringBuilder s = new StringBuilder();
        while(limit>0){ // remove additional chars
            stack.pop();
            limit--;
        }
        while(!stack.empty()){
            s.insert(0, stack.pop() + "");
        }
        while(s.length() > 0 && s.charAt(0) == '0'){ // remove initial zeros.
            s.deleteCharAt(0);
        }
        return s.length() > 0 ? s.toString() : "0";
    }
}