package datastructures.stacksandqueues;

import java.util.*;

// https://leetcode.com/problems/longest-valid-parentheses/
public class LongestValidParenthesis {
    
    class IndexPair{
        int start;
        int end;
        IndexPair(int start, int end){
            this.start = start;
            this.end = end;
        }
        boolean endsImmediateBefore(IndexPair other){
            return this.end+1 == other.start;
        }
        boolean contains(IndexPair other){
            return this.start < other.start && this.end > other.end;
        }
    }

    /**
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
        Example 1:

        Input: s = "(()"
        Output: 2
        Explanation: The longest valid parentheses substring is "()".
        Example 2:

        Input: s = ")()())"
        Output: 4
        Explanation: The longest valid parentheses substring is "()()".
        Example 3:

        Input: s = ""
        Output: 0

        Idea: Maintain two stacks. In one stack we track all indices where ( char occured. In other stack we track (start,end) of
        all valid substrings we found so far .. sort of like intervals. On seeing a open char, push its index into stack1. On seeing
        a close char, get last open char index by poping stack1. currIndex-pastIndex is one such valid substr. But there could be a larger
        substring which contains this, which can be easily found using only stack1.. like (()()) -> 0..5 will be found while poping 0.
        For cases when 2 valid substrs are not composed but occur side by side (()())(), we won't find 8 length substr from 0..7 using stack1.
        Hence we main stack2 with prev valid interval and merge current one if it occurs immediately after prev one.

        Time: O(n)
        Space: O(n) - 2 stacks - max all ( chars in input
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        // stores the indices of all ( chars
        Stack<Integer> openParenIndices = new Stack<>();
        // maintain all valid intervals and merge which occur adjacent to each other like ()()
        // or replace with newly found substr if it contains stack top. (()) the inner () is found first, later we discover the whole which contains earlier one.
        Stack<IndexPair> validSubstrs = new Stack<>();
        int maxLengthValidSubstr = 0;
        
        for(int i=0;i<s.length();i++){
            char currChar = s.charAt(i);
            if(currChar == '('){
                openParenIndices.push(i);
            } else {
                if(!openParenIndices.empty()){
                    int lastOpenIndex = openParenIndices.pop();
                    IndexPair currSubstr = new IndexPair(lastOpenIndex, i);
                    maxLengthValidSubstr = Math.max(maxLengthValidSubstr, currSubstr.end-currSubstr.start+1);
                    if(validSubstrs.empty()){
                        validSubstrs.push(currSubstr);
                    } else {
                        while(!validSubstrs.empty()){
                            IndexPair prevIndex = validSubstrs.peek();
                            // top contained within curr substr - replace
                            if(currSubstr.contains(prevIndex)){
                                validSubstrs.pop();
                            } else {
                                break;
                            }
                        }  
                        // push currsubstr or a new interval
                        if(validSubstrs.empty()){
                            validSubstrs.push(currSubstr);  
                        }
                        else if(validSubstrs.peek().endsImmediateBefore(currSubstr)){
                            IndexPair prevIndex = validSubstrs.pop();
                            validSubstrs.push(new IndexPair(prevIndex.start, currSubstr.end));
                        } else{
                            validSubstrs.push(currSubstr);   
                        }
                    }
                }
                // else: extra close paren, ignore
            }
        }
        // openParenIndices - stack need not be empty now
        // compute chained valid substr lengths from validSubstrs
        OptionalInt chainedMax = validSubstrs.stream()
            .peek((indexPair) -> {System.out.println(indexPair.start +"-" + indexPair.end);})
            .map((indexPair)-> (indexPair.end-indexPair.start+1))
            .mapToInt(Integer::intValue)
            .max();
        System.out.println(maxLengthValidSubstr + "," + chainedMax);
        return Math.max(maxLengthValidSubstr, chainedMax.isPresent() ? chainedMax.getAsInt() : 0);
    }
}
