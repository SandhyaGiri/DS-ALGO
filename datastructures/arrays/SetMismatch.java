package datastructures.arrays;

import java.util.*;
import java.util.stream.*;

// https://leetcode.com/problems/set-mismatch/
public class SetMismatch {
    /**
     * You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.

        You are given an integer array nums representing the data status of this set after the error.

        Find the number that occurs twice and the number that is missing and return them in the form of an array.

        Example 1:

        Input: nums = [1,2,2,4]
        Output: [2,3]
        Example 2:

        Input: nums = [1,1]
        Output: [1,2]

        Time:O(n)
        Space: O(n)

     */
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        Set<Integer> actualNumbers = Stream.iterate(1, (i) -> i+1).limit(n).collect(Collectors.toSet());
        int[] result= new int[2];
        for(int i=0;i<nums.length;i++){
            if(!actualNumbers.contains(nums[i])){
                // duplicate, the number was already seen and removed from set
                result[0] = nums[i];
            } else{
                actualNumbers.remove(nums[i]);
            }
        }
        // now set has only the missing number!
        result[1] = actualNumbers.iterator().next();
        return result;
    }
}
