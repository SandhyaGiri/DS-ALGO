package problemsolving;

import java.util.*;

public class UglyNumber {
    // https://leetcode.com/problems/ugly-number/
    // keep dividing by 2,3,5 final number is >2 then it has to be one of 3,5 otherwise its not ugly.
    // 1 is also an ugly number
    public boolean isUgly(int num) {
        if(num <= 0){ // negative nums are not ugly
            return false;
        }
        while(num%2 == 0){
            num = num/2;
        }
        //System.out.println(num);
        // for finding all prime factors this loop has to go until sqrt(num)
        for(int i=3;i<=5 && num > 1;i+=2){ // only odd numbers
            while(num%i == 0){
                if((i!=3 && i!= 5)){
                    return false;   
                } else{
                    num = num/i;
                }
            }
        }
        //System.out.println(num);
        return num > 2? (num == 3 || num == 5) : true;
    }

    /**
     * https://leetcode.com/problems/ugly-number-ii/
     * 
     * Write a program to find the n-th ugly number.

        Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 

        Example:

        Input: n = 10
        Output: 12
        Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
        Note:  

        1 is typically treated as an ugly number.
        n does not exceed 1690.

        -- generate all ugly numbers, multiples of 2,3 or 5 from previous ugly numbers
        -- start with 1
        -- enque all in priorityQueue, always pick minValue (next ugly number)
        -- each time enque all 3 multiples
        -- stop when n <1
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> uglyNumbers = new PriorityQueue<>();
        uglyNumbers.add(1L);
        while(n>1){
            long x = uglyNumbers.poll();
            //System.out.println(x);
            if(!uglyNumbers.contains(x*2)){
                uglyNumbers.add(x*2);   
            }
            if(!uglyNumbers.contains(x*3)){
                uglyNumbers.add(x*3);   
            }
            if(!uglyNumbers.contains(x*5)){
                uglyNumbers.add(x*5);   
            }
            n-=1;
        }
        return uglyNumbers.poll().intValue();
    }
}