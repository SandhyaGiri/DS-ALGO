package datastructures.strings;

import java.util.*;

public class ValidParenthesis {
    /**
     * Naive solution: generate all combinations, then check if atleast one of them is valid
     * 
     * Time Limit Exceeded
     * 
     * Time Complexity: O(N * 3^{N}), where N is the length of the string. For each asterisk we try 3 different values. Thus, 
     * we could be checking the validity of up to 3^N strings. Then, each check of validity is O(N).

     */

    void generateVariations(String s, int index, String currStr, Set<String> variations){
        if(index == s.length()){
            variations.add(currStr);
            return;
        }
        if(s.charAt(index) == '*'){ // 3 possibilities
            generateVariations(s, index+1, currStr+"(", variations);
            generateVariations(s, index+1, currStr+")", variations);
            generateVariations(s, index+1, currStr, variations);
        }
        generateVariations(s, index+1, currStr+s.charAt(index), variations);
    }
    boolean isValidParen(String s){
        Stack<Character> stack = new Stack<Character>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c == ')'){
                if(stack.empty() || stack.peek() != '('){
                    return false;
                }else {
                    stack.pop();
                }
            } else if(c == '('){
                stack.push(c);
            }
        }
        return stack.empty();
    }
    public boolean checkValidString(String s) {
        Set<String> variations = new HashSet<String>();
        // generate all combinations with 3 possibilities for *
        generateVariations(s, 0, "", variations);
        // validate if one of these is a valid parenthesis
        for(String var: variations){
            System.out.println(var);
            if(isValidParen(var)){
                return true;
            }
        }
        return false;
    }

    /**
     * Slightly better solution - in one pass check validity and combinations.
     * 
     * But still exponential in time - time limit exceeded.
     * 
     * @param s
     * @param index
     * @param stack
     * @return
     */
    boolean isValid(String s, int index, Deque<Character> stack){
        if(index == s.length()){
            boolean isValid = stack.isEmpty();
            return isValid;
        }
        if(s.charAt(index) == '('){
            stack.push(s.charAt(index));
            return isValid(s, index+1, stack);
        }
        else if(s.charAt(index) == ')'){
            if(stack.isEmpty() || stack.peekLast() != '('){
                return false;
            }
            stack.pollLast();
            return isValid(s, index+1, stack);
        }
        else if(s.charAt(index) == '*'){ // 3 possibilities
            StringBuilder s1 = new StringBuilder(s);
            StringBuilder s2 = new StringBuilder(s);
            s1.setCharAt(index, '(');
            s2.setCharAt(index, ')');
            return isValid(s1.toString(), index, new LinkedList<>(stack)) ||
                    isValid(s2.toString(), index, new LinkedList<>(stack)) ||
                    isValid(s, index+1, stack);
        }
        return false;
    }
    public boolean checkValidString2(String s) {
        return isValid(s, 0, new LinkedList<Character>());
    }


    /**
     * O(N) solution based on counts of number of open left brackets more than right brackets.
     * 
     * When checking whether the string is valid, we only cared about the "balance": the number of extra, open left brackets as we parsed through the string. For example, when checking whether '(()())' is valid, we had a balance of 1, 2, 1, 2, 1, 0 as we parse through the string: '(' has 1 left bracket, '((' has 2, '(()' has 1, and so on. This means that after parsing the first i symbols, (which may include asterisks,) we only need to keep track of what the balance could be.

For example, if we have string '(***)', then as we parse each symbol, the set of possible values for the balance is [1] for '('; [0, 1, 2] for '(*'; [0, 1, 2, 3] for '(**'; [0, 1, 2, 3, 4] for '(***', and [0, 1, 2, 3] for '(***)'.

Furthermore, we can prove these states always form a contiguous interval. Thus, we only need to know the left and right bounds of this interval. That is, we would keep those intermediate states described above as [lo, hi] = [1, 1], [0, 2], [0, 3], [0, 4], [0, 3].

Let lo, hi respectively be the smallest and largest possible number of open left brackets after processing the current character in the string.

If we encounter a left bracket (c == '('), then lo++, otherwise we could write a right bracket, so lo--. If we encounter what can be a left bracket (c != ')'), then hi++, otherwise we must write a right bracket, so hi--. If hi < 0, then the current prefix can't be made valid no matter what our choices are. Also, we can never have less than 0 open left brackets. At the end, we should check that we can have exactly 0 open left brackets.

     * @param s
     * @return
     */
    public boolean checkValidString3(String s) {
        int lo=0; // min number of open brackets (that are more than closing brackets)
        int hi=0; // max number ...
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            lo+= c == '(' ? 1 : -1; // -1 -> assumes right brackets(even *s) to reduce the min value
            hi+= c != ')' ? 1 : -1; // 1 -> assumes left brackets(even *s) to increase the max value
            if (hi < 0) break; // if max itself is neg, then it can never be valid
            lo = Math.max(lo, 0); // low cannot be less than 0
        }
        return lo == 0;
    }
}