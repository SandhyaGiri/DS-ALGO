package problemsolving;

// https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/
public class ConcatenateBinaryNumbers {
    int getNumDigitsBinary(int num){
        int numDigits = 0;
        while(num > 0){
            num = num/2;
            numDigits++;
        }
        return numDigits;
    }
    /**
     * Given an integer n, return the decimal value of the binary string formed by concatenating the binary representations of 1 to n in order, modulo 109 + 7.

        Example 1:

        Input: n = 1
        Output: 1
        Explanation: "1" in binary corresponds to the decimal value 1. 
        Example 2:

        Input: n = 3
        Output: 27
        Explanation: In binary, 1, 2, and 3 corresponds to "1", "10", and "11".
        After concatenating them, we have "11011", which corresponds to the decimal value 27.
        Example 3:

        Input: n = 12
        Output: 505379714
        Explanation: The concatenation results in "1101110010111011110001001101010111100".
        The decimal value of that is 118505380540.
        After modulo 109 + 7, the result is 505379714.

        Idea: Each time left shift by the number of digits in new number to be appended. Then do bitwise OR
        with the new number. Avoid overflows by doing mod op before left shift.
     * @param n
     * @return
     */
    public int concatenatedBinary(int n) {
        long concatenatedVal = 0;
        for(int i=1;i<=n;i++){
            int digits = getNumDigitsBinary(i);
            // at any point prevent overflows by doing the modulus op
            concatenatedVal = (long)(concatenatedVal % (Math.pow(10, 9) + 7));
            concatenatedVal = concatenatedVal << digits;
            concatenatedVal = concatenatedVal | i;
        }
        return (int)(concatenatedVal % (Math.pow(10, 9) + 7));
    }
}
