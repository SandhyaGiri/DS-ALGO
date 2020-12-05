package problemsolving;

// https://leetcode.com/problems/reverse-bits/
public class ReverseBits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        int pow=30;
        // check sign and use last bit -- needed only for negative numbers
        if(n < 0){
            System.out.println("Here");
            result = result + 1;
        }
        int bitValue = 0;
        while(pow>=0){
            bitValue = (n & (int)Math.pow(2, pow)) > 0 ? 1 : 0;
            result = result + bitValue * (int)Math.pow(2, 31-pow);
            //System.out.println(pow + "," + bitValue + ", " + (int)Math.pow(2, 31-pow)+", " + result);
            pow-=1;
        }
        if(bitValue == 1) { // last bit becomes most significant bit (compensate for 2's complement storage)
            result += 1;
        }
        return result;
    }
}