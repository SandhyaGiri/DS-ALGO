package datastructures.arrays;

// https://leetcode.com/problems/can-place-flowers/
public class Flowerbed {
    /**
     * Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

        Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
        
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int numFreeSpots = 0;
        int i=0;
        while(i<flowerbed.length){
            if(flowerbed[i] == 1){
                i+=2; // input is valid, so we can jump safely
            } else if(i+1 == flowerbed.length || flowerbed[i+1] == 0){
                // last spot in bed, or next spot is not potted
                numFreeSpots += 1;
                i+=2;
            } else{
                i+=1; // tread carefully
            }
        }
        return n<=numFreeSpots;
    }
}
