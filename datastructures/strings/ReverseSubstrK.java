package datastructures.strings;

// https://leetcode.com/problems/reverse-string-ii/
public class ReverseSubstrK {
    /**
     * Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
        Example:
        Input: s = "abcdefg", k = 2
        Output: "bacdfeg"
        Restrictions:
        The string consists of lower English letters only.
        Length of the given string and k will in the range [1, 10000]
        
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        StringBuilder reversedStr = new StringBuilder();
        boolean reverseBlock = true;
        for(int i=0;i<s.length();i+=k){
            // march in blocks of size k
            int size = 0;
            StringBuilder localReversedStr = new StringBuilder();
            while(size < k && i+size < s.length()){
                char c = s.charAt(i+size);
                if(reverseBlock){
                    localReversedStr.insert(0, c);
                } else {
                    localReversedStr.append(c);
                }
                size+=1;
            }
            reverseBlock = !reverseBlock;
            reversedStr.append(localReversedStr.toString());
        }
        return reversedStr.toString();
    }
}
