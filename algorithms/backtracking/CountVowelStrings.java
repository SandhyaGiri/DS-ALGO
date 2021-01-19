package algorithms.backtracking;

// https://leetcode.com/problems/count-sorted-vowel-strings/
public class CountVowelStrings {
    int dfs(int n, int charCount, Integer prevCharNum){
        if(charCount == n){
            return 1;
        }
        int numPossibleStrings = 0;
        for(int i=(prevCharNum == null ? 1 : prevCharNum);i<=5;i++){
            numPossibleStrings += dfs(n, charCount+1, i);
        }
        return numPossibleStrings;
    }
    /**
     * Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.

        A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.

        

        Example 1:

        Input: n = 1
        Output: 5
        Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
        Example 2:

        Input: n = 2
        Output: 15
        Explanation: The 15 sorted strings that consist of vowels only are
        ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
        Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
        Example 3:

        Input: n = 33
        Output: 66045

        Idea: Same as combination sum, assume aeiou as numbers 12345, now we only take numbers which are greater or equal to
        previously chosen number. That way we end up with lexi sorted string.
     * @param n
     * @return
     */
    public int countVowelStrings(int n) {
        return dfs(n, 0, null);
    }
}
