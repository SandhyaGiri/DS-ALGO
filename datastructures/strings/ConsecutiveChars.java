package datastructures.strings;

public class ConsecutiveChars {
    // Consecutive Characters
    /**
     * Given a string s, the power of the string is the maximum length of a non-empty substring that contains only one unique character.

        Return the power of the string.

        Input: s = "leetcode"
        Output: 2
        Explanation: The substring "ee" is of length 2 with the character 'e' only.
        Example 2:

        Input: s = "abbcccddddeeeeedcba"
        Output: 5
        Explanation: The substring "eeeee" is of length 5 with the character 'e' only.
        Example 3:

        Input: s = "triplepillooooow"
        Output: 5
        Example 4:

        Input: s = "hooraaaaaaaaaaay"
        Output: 11
        Example 5:

        Input: s = "tourist"
        Output: 1
     * @param s
     * @return
     */
    public int maxPower(String s) {
        int currCharCount = 0;
        Character currChar = null;
        int maxChainLength = Integer.MIN_VALUE;
        for(int i=0;i<s.length();i++){
            char c =s.charAt(i);
            if(currChar == null){
                currChar = c;
                currCharCount += 1;
            } else if(c == currChar){
                currCharCount += 1;
            } else { // new char start
                if(currCharCount > maxChainLength){
                    maxChainLength = currCharCount;
                }
                currCharCount = 1;
                currChar = c;
            }
        }
        // last chain
        if(currCharCount > maxChainLength){
            maxChainLength = currCharCount;
        }
        return maxChainLength == Integer.MIN_VALUE ? 0 : maxChainLength;
    }
}