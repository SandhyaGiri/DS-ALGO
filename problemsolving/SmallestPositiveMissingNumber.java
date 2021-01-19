package problemsolving;

import java.util.stream.*;
import java.util.*;

// https://leetcode.com/problems/first-missing-positive/
public class SmallestPositiveMissingNumber {
    /**
     * Write a function:

        class Solution { public int solution(int[] A); }

        that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

        For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

        Given A = [1, 2, 3], the function should return 4.

        Given A = [−1, −3], the function should return 1.

        Write an efficient algorithm for the following assumptions:

        N is an integer within the range [1..100,000];
        each element of array A is an integer within the range [−1,000,000..1,000,000].
     * @param A
     * @return
     */
    public int solution(int[] A) {
        // write your code in Java SE 8
        int smallestPosNum = 0;
        IntStream s = Arrays.stream(A);
        int[] postiveA = s.filter(num -> num > 0).toArray();
        Arrays.sort(postiveA);
        for(int i=0;i<postiveA.length;i++) {
            if(postiveA[i] == smallestPosNum) {
                continue;
            } else if(postiveA[i] == smallestPosNum + 1){
                smallestPosNum++;
            } else {
                break;
            }
        }
        return smallestPosNum == 0 ? 1 : smallestPosNum + 1;
    }

    /**
     * Given an unsorted integer array nums, find the smallest missing positive integer.

        Follow up: Could you implement an algorithm that runs in O(n) time and uses constant extra space.?

        

        Example 1:

        Input: nums = [1,2,0]
        Output: 3
        Example 2:

        Input: nums = [3,4,-1,1]
        Output: 2
        Example 3:

        Input: nums = [7,8,9,11,12]
        Output: 1
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int expectedNum = 1;
        int prevNum = -1;
        for(int i=0;i<nums.length;i++){
            if(nums[i] > 0){
                if(nums[i] != expectedNum && nums[i] != prevNum){
                    return expectedNum;   
                } else if(nums[i] != prevNum){
                    expectedNum += 1;
                    prevNum = nums[i];
                }
            }
        }
        return expectedNum;
    }

    // https://leetcode.com/problems/kth-missing-positive-number/
    /**
     * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.

        Find the kth positive integer that is missing from this array.

        Example 1:

        Input: arr = [2,3,4,7,11], k = 5
        Output: 9
        Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
        Example 2:

        Input: arr = [1,2,3,4], k = 2
        Output: 6
        Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.

     * @param arr
     * @param k
     * @return
     */
    public int findKthPositive(int[] arr, int k) {
        int num=1; // expected number
        for(int i=0;i<arr.length;i++){
            if(arr[i] > num){
                int missingNumbers = arr[i] - num;
                if(k <= missingNumbers){
                    // found in this range
                    return num + k -1;
                } else {
                    k-=missingNumbers;
                }
                num = arr[i]+1; // expect next continuous number
            } else if(arr[i] == num){
                num+=1;
            }
        }
        return k>0 ? num+k-1 : -1;
    }
}