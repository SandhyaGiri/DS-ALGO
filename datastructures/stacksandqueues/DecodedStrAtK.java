package datastructures.stacksandqueues;

import java.util.*;

// https://leetcode.com/problems/decoded-string-at-index/
public class DecodedStrAtK {
    /**
     * An encoded string S is given.  To find and write the decoded string to a tape, the encoded string is read one character at a time and the following steps are taken:

If the character read is a letter, that letter is written onto the tape.
If the character read is a digit (say d), the entire current tape is repeatedly written d-1 more times in total.
Now for some encoded string S, and an index K, find and return the K-th letter (1 indexed) in the decoded string.

 

Example 1:

Input: S = "leet2code3", K = 10
Output: "o"
Explanation: 
The decoded string is "leetleetcodeleetleetcodeleetleetcode".
The 10th letter in the string is "o".
Example 2:

Input: S = "ha22", K = 5
Output: "h"
Explanation: 
The decoded string is "hahahaha".  The 5th letter is "h".
Example 3:

Input: S = "a2345678999999999999999", K = 1
Output: "a"
Explanation: 
The decoded string is "a" repeated 8301530446056247680 times.  The 1st letter is "a".
 
     */

    // memory limit exceeds!
    public String decodeAtIndexMemHeavy(String S, int K) {
        int endIndex = 0;
        int currIndex =0;
        StringBuilder decodedStr = new StringBuilder();
        while(endIndex <= K-1){
            char currChar = S.charAt(currIndex);
            if(Character.isDigit(currChar)){
                int digit = Character.getNumericValue(currChar);
                digit -=1;
                // System.out.println(digit);
                // repeat curr decoded str digit-1 times
                String currDecoded = decodedStr.toString();
                while(digit > 0){
                    decodedStr.append(currDecoded);
                    endIndex += currDecoded.length();
                    digit-=1;
                }
                // System.out.println(decodedStr.toString());
            } else{
                decodedStr.append(currChar);
                endIndex+= 1;
            }
            currIndex += 1;
        }
        String currDecoded = decodedStr.toString();
        //System.out.println(decodedStr);
        return currDecoded.charAt(K-1) + "";
    }
    /**
     * If we have a decoded string like appleappleappleappleappleapple and an index like K = 24, the answer is the same if K = 4 (24%5).

        In general, when a decoded string is equal to some word with size length repeated some number of times (such as apple with size = 5
        repeated 6 times), the answer is the same for the index K as it is for the index K % size.

        We can use this insight by working backwards, keeping track of the size of the decoded string. Whenever the decoded string would equal
        some word repeated d times, we can reduce K to K % (word.length).

        We need two traversals
        1. Forward: On seeing a digit dump curr length of decoded str in stack, also update the currlen based on digit.
        2. Backward: On seeing a digit, update currLen as previously seen by popping the stack, k = k% currLen. on seeing char
        check if k==0 or currLen exactly matches k-1, then we have reached the char.

     * @param S
     * @param K
     * @return
     */
    public String decodeAtIndex(String S, int K) {
        // length of decoded str can be 2^63 >>> int.max_value
        // so use long
        Stack<Long> wordLengths = new Stack<>();
        long numChars = 0;
        int currIndex =0;
        while(currIndex < S.length()){
            char currChar = S.charAt(currIndex);
            if(Character.isDigit(currChar)){
                int digit = Character.getNumericValue(currChar);
                wordLengths.push(numChars);
                numChars += (numChars * (digit-1));
                // System.out.println(decodedStr.toString());
            } else{
                numChars+= 1;
            }
            currIndex += 1;
        }
        // traverse s from right
        for(int i=S.length()-1;i>=0;i--){
            char currChar = S.charAt(i);
            if(Character.isDigit(currChar)){
                numChars = wordLengths.pop();
                K = (int)(K % numChars);
            } else {
                numChars -= 1;
                if(K == 0 || numChars == K-1){
                    return currChar + "";
                }
            }
        }
        return "";
    }
}
