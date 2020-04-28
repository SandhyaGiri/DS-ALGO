package datastructures.strings;

public class StringShifts {
    /**
     * You are given a string s containing lowercase English letters, and a matrix shift, where shift[i] = [direction, amount]:

direction can be 0 (for left shift) or 1 (for right shift). 
amount is the amount by which string s is to be shifted.
A left shift by 1 means remove the first character of s and append it to the end.
Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.
Return the final string after all operations.

 

Example 1:

Input: s = "abc", shift = [[0,1],[1,2]]
Output: "cab"
Explanation: 
[0,1] means shift to left by 1. "abc" -> "bca"
[1,2] means shift to right by 2. "bca" -> "cab"
Example 2:

Input: s = "abcdefg", shift = [[1,1],[1,1],[0,2],[1,3]]
Output: "efgabcd"
Explanation:  
[1,1] means shift to right by 1. "abcdefg" -> "gabcdef"
[1,1] means shift to right by 1. "gabcdef" -> "fgabcde"
[0,2] means shift to left by 2. "fgabcde" -> "abcdefg"
[1,3] means shift to right by 3. "abcdefg" -> "efgabcd"
     * @param s
     * @param shift
     * @return
     */
    public String stringShift(String s, int[][] shift) {
        int numchars = s.length();
        int indices[] = new int[numchars];
        for(int i=0;i<s.length();i++) {
            indices[i] = i;
        }
        for(int i=0;i<shift.length;i++){
            int dir = shift[i][0];
            int amt = shift[i][1];
            // update the indices
            for(int j=0;j<indices.length;j++) {
                if(dir == 0){ // left
                    indices[j] = (indices[j] + (numchars - amt) ) % numchars;
                }
                else if(dir == 1){ // right
                    indices[j] = (indices[j] + amt) % numchars;
                }
            }
        }
        // finally assemble the new string
        StringBuilder modifiedStr = new StringBuilder(s);
        for(int i=0;i<indices.length;i++) {
             modifiedStr.setCharAt(indices[i], s.charAt(i));
        }
        return modifiedStr.toString();
    }
}