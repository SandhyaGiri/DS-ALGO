package datastructures.stacksandqueues;

import java.util.*;

// https://leetcode.com/problems/validate-stack-sequences/
public class ValidateStackSequences {
    /**
     * Given two sequences pushed and popped with distinct values, return true if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.
        Example 1:

        Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
        Output: true
        Explanation: We might do the following sequence:
        push(1), push(2), push(3), push(4), pop() -> 4,
        push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
        Example 2:

        Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
        Output: false
        Explanation: 1 cannot be popped before 2.
        

        Constraints:

        0 <= pushed.length == popped.length <= 1000
        0 <= pushed[i], popped[i] < 1000
        pushed is a permutation of popped.
        pushed and popped have distinct values.

        Idea: Use a real stack. As long as there are elements to be pushed and the stack top != curr ele in popped array,
        we can push the current element into the stack. Otherwise check if stack top matches the tobe popped ele, and pop the
        stack. If both operations couldn't be done, then break and declare that it is not a valid stack sequence!. At the end
        stack needs to be empty for a valid stack sequence!
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i=0,j=0;
        int n = pushed.length;
        while(i<n || j<n){
            // case1: push if stack top has a diff elem than the one to be popped
            if(i<n && (stack.empty() || (j>=n || stack.peek() != popped[j]))){
                stack.push(pushed[i++]);
            } else if(j<n && stack.peek() == popped[j]){ // case2: pop the stack if a match occurs
                stack.pop();
                j++;
            } else{
                break;
            }
        }
        return stack.empty();
    }
}
