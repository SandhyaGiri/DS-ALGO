package problemsolving;

import java.util.Random;

// https://leetcode.com/problems/implement-rand10-using-rand7/
public class Rand10 {
    int rand7(){
        return 1 + (new Random().nextInt(7));
    }
    int rand10_single(){
        // won't work as some numbers are never returned
        int u1 = rand7(); // uniformly sampled u1 variable 1...7 values
        float percent = (float)u1/7 * 100;
        System.out.println("u1: " + u1 + ", percent: " + percent);
        return (int)Math.ceil(percent/10);
    }
    public int rand10() {
        // using 2 uni(1,7) random variables
        // we can generate 49 numbers (remember a 2D grid), reject >40 as it is not a multiple of 10 ==> WE DO REJECTION SAMPLING
        int row, col, index;
        do{
            row = rand7();
            col = rand7();
            index = (row - 1)*7 + col;
        } while(index > 40); // we reject samples higher than 40
        return 1 + (index-1)%10; // mod 10 returns 0...9
    }
}