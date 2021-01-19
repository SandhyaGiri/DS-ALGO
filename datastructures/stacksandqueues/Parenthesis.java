package datastructures.stacksandqueues;

import java.util.*;

class Parenthesis{
	
	public static void main(String []argh)
	{
		Scanner sc = new Scanner(System.in);
		
		while (sc.hasNext()) {
			String input=sc.next();
            Stack<Character> parenthesis = new Stack<Character>();
            boolean invalid = false;
            for(int i=0;i<input.length();i++) {
                char p = input.charAt(i);
                if(p == '{' || p == '[' || p== '(') {
                    parenthesis.push(p);
                } else {
                    if (parenthesis.size() > 0) {
                        char ps = parenthesis.peek();
                        if((p == '}' && ps == '{') || (p == ']' && ps == '[') || (p == ')' && ps == '(')) {
                            parenthesis.pop();
                        } else {
                            break;
                        }
                    } else {
                        invalid = true;
                        break;
                    }
                } 
            }
            System.out.println(parenthesis.size() == 0 && !invalid);
		}
		
    }
    
    // https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
    /**
     * Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting parentheses string is valid.

        Formally, a parentheses string is valid if and only if:

        It is the empty string, or
        It can be written as AB (A concatenated with B), where A and B are valid strings, or
        It can be written as (A), where A is a valid string.
        Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.

        Example 1:

        Input: "())"
        Output: 1
        Example 2:

        Input: "((("
        Output: 3
        Example 3:

        Input: "()"
        Output: 0
        Example 4:

        Input: "()))(("
        Output: 4

        Idea: Similar to validating if expression is right, whenever we see closing paren, but if stack is empty or stack top is not an open paren
        then this is a violation, increment min number in this case. Also at the end if stack is not empty, that many open parens need closing parens
        to be added.
     * @param S
     * @return
     */
    public int minAddToMakeValid(String S) {
        Stack<Character> parenStack = new Stack<>();
        int minParensToAdd = 0;
        for(int i=0;i<S.length();i++){
            char c = S.charAt(i);
            if(c == '('){
                parenStack.push(c);
            } else {
                if(parenStack.empty() || parenStack.peek() != '('){
                    minParensToAdd+=1;
                } else if(!parenStack.empty()){
                    parenStack.pop();
                }
            }
        }
        if(!parenStack.empty()){
            minParensToAdd += parenStack.size();
        }
        return minParensToAdd;
    }

    // https://leetcode.com/problems/score-of-parentheses/
    /**
     * Given a balanced parentheses string S, compute the score of the string based on the following rule:

        () has score 1
        AB has score A + B, where A and B are balanced parentheses strings.
        (A) has score 2 * A, where A is a balanced parentheses string.
        

        Example 1:

        Input: "()"
        Output: 1
        Example 2:

        Input: "(())"
        Output: 2
        Example 3:

        Input: "()()"
        Output: 2
        Example 4:

        Input: "(()(()))"
        Output: 6

     * @param S
     * @return
     */
    public int scoreOfParentheses(String S) {
        Stack<String> scores = new Stack<>(); // has scores + '('
        for(int i=0;i<S.length();i++){
            char currChar = S.charAt(i);
            if(currChar == '('){
                scores.push(String.valueOf(currChar));
            } else {
                int score = 0; // sum of all numbers until '(' is encountered
                while(!scores.empty() && !scores.peek().equals("(")){
                    score += Integer.valueOf(scores.pop());
                }
                // if stack not empty, then it is open paren - pop it
                if(!scores.empty()){
                    scores.pop();
                    // now value is 2*score if score > 0,
                    // otherwise this is the case: () -> hence push a score of 1
                    scores.push(score > 0 ? String.valueOf(2*score) : "1");
                } else {
                    scores.push(String.valueOf(score));
                }
            }
        }
        // finally sum all values in stack
        return scores.stream().mapToInt(Integer::valueOf).sum();
    }
}