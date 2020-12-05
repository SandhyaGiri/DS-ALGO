package datastructures.strings;

// https://leetcode.com/problems/repeated-substring-pattern/
public class RepeatedSubstring {
    /**
     * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

        Example 1:

        Input: "abab"
        Output: True
        Explanation: It's the substring "ab" twice.
        Example 2:

        Input: "aba"
        Output: False
        Example 3:

        Input: "abcabcabcabc"
        Output: True
        Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)

        Idea: Keep expanding the substr starting from 0, see if its continuously repeated, otherwise fallback and increase
        the window length.
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        int i=1;
        int n = s.length();
        int halfLen = (int)Math.ceil((float)n/2);
        System.out.println("half length: " + halfLen);
        while(i<=halfLen){ // only half the string needs to be searched
            // check if this substring is repeated
            int substrLen = i;
            String substr = s.substring(0, i); 
            int currIndex = i;
            int nextIndex = s.indexOf(substr, i);
            boolean repeated = false;
            //System.out.println("curr substr: " + substr);
            //System.out.println("next matched index: " + nextIndex);
            while(nextIndex == currIndex && currIndex<n){ // starts right after
                currIndex+=substrLen;
                nextIndex = s.indexOf(substr, currIndex);
                repeated = true;
            }
            //System.out.println("final index: "+ currIndex);
            if(repeated && currIndex==n){
                return true;
            }
            i++; // when no match occurs
        }
        return false;
    }

    // https://leetcode.com/problems/repeated-string-match
    public int repeatedStringMatch(String a, String b) {
        StringBuilder repeatedStr = new StringBuilder(a);
        int repetitions = 1;
        while(repeatedStr.length() < b.length()){
            repeatedStr.append(a);
            repetitions += 1;
        }
        if(repeatedStr.indexOf(b) != -1){
            return repetitions;
        }
        // one additional repetition for cases with str a longer than str b
        if(repeatedStr.append(a).indexOf(b) >= 0){
            return repetitions+1;
        }
        return -1;
    }
}