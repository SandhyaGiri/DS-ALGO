package javaproblems;

public class IntReverse {
    public int reverse(int x) {
        long result = 0; // need to store in long, so as to detect int overflow
        boolean isNegative = x<0;
        x = x< 0 ? -x : x;
        while(x>0){
            int digit = x%10;
            result= result * 10 + (long)digit;
            if(result > Integer.MAX_VALUE){ 
                // possible as result is long, if it were INT then overflow cannot be detected, as 
                // we would already have a wrong value.
                return 0;
            }
            System.out.println(result);
            x=x/10;
        }
        return !isNegative ? (int)result : -(int)result;
    }
}