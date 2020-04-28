package datastructures.strings;

public class RangeBitwiseAnd {
    String getBinaryNum(int x){
        StringBuilder s = new StringBuilder();
        while(x > 0){
            s.append(x%2);
            x=x/2;
        }
        return s.reverse().toString();
    }
    int getInteger(String s){
        int num = 0;
        int powIndex =0;
        for(int i=s.length()-1;i>=0;i--){
            num += Character.getNumericValue(s.charAt(i)) * Math.pow(2, powIndex);
            powIndex++;
        }
        return num;
    }
    /**
     * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

        Example 1:

        Input: [5,7]
        Output: 4
        Example 2:

        Input: [0,1]
        Output: 0

        Soln: Observe that if m, n have different length binary representation, then result is 0.
        For m and n having same length, the longest common prefix after which they diverge is the final
        string, for remaining chars set to 0. then convert this binary str to int value.

        Time: O(m+n) - length of binary representations of m and n
     * @param m
     * @param n
     * @return
     */
    public int rangeBitwiseAnd(int m, int n) {
        String mBinary = getBinaryNum(m);
        String nBinary = getBinaryNum(n);
        System.out.println(mBinary + "," + nBinary);
        if(mBinary.length() == nBinary.length()){
            // compare most significant digits
            StringBuilder s = new StringBuilder();
            boolean diverged = false;
            for(int i=0;i<nBinary.length();i++){
                if(!diverged && mBinary.charAt(i) == nBinary.charAt(i)){
                    s.append(mBinary.charAt(i));
                } else {
                    diverged = true;
                    s.append("0");
                }
            }
            return getInteger(s.toString());
        }
        return 0;
    }
}