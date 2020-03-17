package algorithms.greedy;

import java.util.*;

/**
 * Given an unsorted array with positive and negative integers, find the minimum absolute difference between any pair in given array.
 * 
 * Greedy algorithm. O(nlgn)
 * 
 * 1. Sort the array 
 * 2. iterate over the array, comparing the difference between adjacent pairs of elements, and track the minimum difference.
 */
public class MinDifferenceArray {

    private static int findMinDiff(int[] arr) {
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        for(int i=0;i<arr.length-1;i++) {
            if(Math.abs(arr[i+1]-arr[i]) < minDiff) {
                minDiff = Math.abs(arr[i+1]-arr[i]);
            }
        }
        return minDiff;
    }

    public static void main(String args[]) {
        int arr[] ={1, 5, 3, 19, 18, 25};
        System.out.println("Min difference: " + findMinDiff(arr));
    }
}