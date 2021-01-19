package datastructures.stacksandqueues;

import java.util.*;

public class CandyCrush {
    // https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
    /**
     * Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.

        We repeatedly make duplicate removals on S until we no longer can.

        Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.

        Example 1:

        Input: "abbaca"
        Output: "ca"
        Explanation: 
        For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
        

        Note:

        1 <= S.length <= 20000
        S consists only of English lowercase letters.
     * @param S
     * @return
     */
    public String removeDuplicates(String S) {
        Stack<Character> charStack = new Stack<>();
        for(int i=0;i<S.length();i++){
            char c = S.charAt(i);
            boolean isDuplicate = false;
            while(!charStack.empty() && c == charStack.peek()){
                charStack.pop();
                isDuplicate = true;
            }
            if(!isDuplicate){ // duplicates crush and disappear
                charStack.push(c);   
            }
        }
        Optional<String> reducedStr = charStack.stream().map(c -> c+"").reduce((c1,c2) -> (c1+c2));
        return reducedStr.isPresent() ? reducedStr.get() : "";
    }

    class CharCount{
        char c;
        int count;
        CharCount(char c, int cnt){
            this.c = c;
            this.count = cnt;
        }
    }
    // https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
    /**
     * Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.

        We repeatedly make k duplicate removals on s until we no longer can.

        Return the final string after all such duplicate removals have been made.

        It is guaranteed that the answer is unique.

        Example 1:

        Input: s = "abcd", k = 2
        Output: "abcd"
        Explanation: There's nothing to delete.
        Example 2:

        Input: s = "deeedbbcccbdaa", k = 3
        Output: "aa"
        Explanation: 
        First delete "eee" and "ccc", get "ddbbbdaa"
        Then delete "bbb", get "dddaa"
        Finally delete "ddd", get "aa"
        Example 3:

        Input: s = "pbbcggttciiippooaais", k = 2
        Output: "ps"


        Constraints:

        1 <= s.length <= 10^5
        2 <= k <= 10^4
        s only contains lower case English letters.

        Idea: similar to previous problem, maintain a charcount along with the char in the stack. when curr char matches the stack top
        and stack top's count +1 == k, then we can crush these characters (pop k-1 times). Otherwise insert the current char with an
        appropriate count into the stack.
     * @param s
     * @param k
     * @return
     */
    public String removeDuplicates2(String s, int k) {
        Stack<CharCount> charStack = new Stack<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            //System.out.println(c);
            if(!charStack.empty() && c == charStack.peek().c){
                //System.out.println(charStack.peek().c + "-" + charStack.peek().count);
                if(charStack.peek().count+1 == k){
                    // pop all chars (k-1 times)
                    int timesToPop = k-1;
                    while(timesToPop >0){
                        charStack.pop();
                        timesToPop-=1;
                    }
                } else{
                   charStack.push(new CharCount(c, charStack.peek().count+1));
                }
            } else {
                charStack.push(new CharCount(c, 1));
            }
        }
        StringBuilder result = new StringBuilder();
        while(!charStack.empty()){
            result.append(charStack.pop().c + "");
        }
        return result.reverse().toString();
    }
}
