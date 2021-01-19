package datastructures.arrays;

// https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
public class PairsDivisibleBy60 {
    /**
     * You are given a list of songs where the ith song has a duration of time[i] seconds.

        Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.

        

        Example 1:

        Input: time = [30,20,150,100,40]
        Output: 3
        Explanation: Three pairs have a total duration divisible by 60:
        (time[0] = 30, time[2] = 150): total duration 180
        (time[1] = 20, time[3] = 100): total duration 120
        (time[1] = 20, time[4] = 40): total duration 60
        Example 2:

        Input: time = [60,60,60]
        Output: 3
        Explanation: All three pairs have a total duration of 120, which is divisible by 60.

        Ex: [30,20,10,70,130,150,100,50,170,40]
        output: 9
        30-> 30, 150  (1 pair)
        20 -> 20
        40->100, 40 (2 pairs with remaining 20)
        10-> 10,70,130
        50->50,170 (3*2 pairs with remaining 10)

     * @param time
     * @return
     */
    public int numPairsDivisibleBy60(int[] time) {
        int[] mod60Counts = new int[60];
        for(int i=0;i<time.length;i++){ // count number of 
            mod60Counts[time[i] % 60] += 1;
        }
        int numPairs =0;
        for(int i=0;i<60;i++){
            if(mod60Counts[i] > 0){
                int complementIndex = 60-i;
                if(complementIndex == i || i==0){
                    // number of pairs in a list of n numbers. (unique based on index)
                    numPairs += mod60Counts[i] * (mod60Counts[i] - 1) / 2;
                } else {
                    // each number in i can be paired with any number in complementIndex.
                    // number of pairs is the cartesian product.
                    numPairs += mod60Counts[i] * mod60Counts[complementIndex];
                    mod60Counts[complementIndex] = 0; // so that its not counted later on
                }
            }
        }
        return numPairs;
    }
}
