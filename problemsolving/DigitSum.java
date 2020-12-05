package problemsolving;

// https://leetcode.com/problems/add-digits/
public class DigitSum {
    // https://en.wikipedia.org/wiki/Digital_root
    /**
     * 
     * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
        Example:

        Input: 38
        Output: 2 
        Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2. 
                    Since 2 has only one digit, return it.
        Follow up:
        Could you do it without any loop/recursion in O(1) runtime?
     * @param num
     * @return
     */
    public int addDigits(int num) {
        if(num == 0 || num <= 9){
            return num;
        }
        // digit_root(base b, num n) = {b-1 if n mod b-1 == 0 else n mod b-1}
        return (num % 9 == 0) ? 9 : num % 9;
    }
}