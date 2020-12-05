package datastructures.arrays;

import java.util.*;

//https://leetcode.com/problems/shuffle-an-array/
public class ArrayShuffling {
    int[] original;
    int[] nums;
    public ArrayShuffling(int[] nums) {
        // inefficient to gen all possible permutations here - memory limit exceeded
        original = nums.clone();
        this.nums = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        for(int i=0;i<this.nums.length;i++){
            this.nums[i] = original[i];
        }
        return this.nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        // Fisher–Yates shuffle Algorithm  O(n) algorithm
        // https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
        Random rand = new Random();
        for(int i=this.nums.length-1;i>=0;i--){
            int permuteIndex = rand.nextInt(this.nums.length);
            int temp = this.nums[permuteIndex];
            this.nums[permuteIndex] = this.nums[i];
            this.nums[i] = temp;
        }
        return this.nums;
    }
}