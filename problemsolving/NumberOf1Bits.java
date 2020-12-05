package problemsolving;

// Counting Bits
// https://leetcode.com/problems/counting-bits/
public class NumberOf1Bits {
    int getOneBits(int x){
        int count = 0;
        while(x > 0){
            count += x%2;
            x = x/2;
        }
        return count;
    }
    /**
     * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

        Example 1:

        Input: 2
        Output: [0,1,1]
        Example 2:

        Input: 5
        Output: [0,1,1,2,1,2]
        Follow up:

        It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
        Space complexity should be O(n).

        Idea: You should make use of what you have produced already.
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] numOneBits = new int[num+1];
        for(int i=0;i<=Math.min(3,num);i++){
            numOneBits[i] = getOneBits(i);
        }
        int pow = 2;
        double counter = Math.pow(2,pow);
        for(int i=4;i<=num;i++){
            numOneBits[i] = 1 + numOneBits[i-(int)counter];
            counter--;
            if(counter == 0){
                pow += 1;
                counter = Math.pow(2, pow);
            }
        }
        return numOneBits;
    }

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        // if you do normal %2, /2 for binary conversion, last signed bit will result in wrong values
        // so better to check each bit, by raising 2 to the power of 31...0
        int numSetBits =0;
        int binaryIndex = 31;
        System.out.println((n & (int)Math.pow(2, 31)) > 0);
        while(binaryIndex>=0){
            if((n & (long)Math.pow(2, binaryIndex)) > 0){
                numSetBits++;
            }
            binaryIndex -=1;
        }
        return numSetBits;
    }
}