package problemsolving;

// https://leetcode.com/problems/excel-sheet-column-number/
public class ExcelSheetColNumber {
    /**
     * Given a column title as appear in an Excel sheet, return its corresponding column number.

        For example:

            A -> 1
            B -> 2
            C -> 3
            ...
            Z -> 26
            AA -> 27
            AB -> 28 
            ...
        Example 1:

        Input: "A"
        Output: 1
        Example 2:

        Input: "AB"
        Output: 28
        Example 3:

        Input: "ZY"
        Output: 701
     * @param s
     * @return
     */
    public int titleToNumber(String s) {
        int numDigits = s.length();
        int value = 0;
        int possibilities = 26;
        for(int i=1;i<=numDigits-1;i++){
            // for n-1 times we have whole combinations
            value += possibilities;
            possibilities *= 26;
        }
        // for the given n digit combination
        for(int i=0;i<s.length();i++){
            int charValue = s.charAt(i) - 'A' + 1;
            int prevChars = charValue - 1;
            // System.out.println(charValue + "," + prevChars);
            // run full combinations for previous charatcers
            value += (prevChars * Math.pow(26, numDigits-i-1));
        }
        // to include the new value in last digit
        return value+1;
    }
}