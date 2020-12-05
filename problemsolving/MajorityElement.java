package problemsolving;

import java.util.*;

public class MajorityElement {
    /**
     * https://leetcode.com/problems/majority-element/
     * 
     * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

        You may assume that the array is non-empty and the majority element always exist in the array.

        Example 1:

        Input: [3,2,3]
        Output: 3
        Example 2:

        Input: [2,2,1,1,1,2,2]
        Output: 2
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        if(nums.length > 0) {
            Arrays.sort(nums);   
            return nums[nums.length/2];
        }
        return 0;
    }

    /**
     * https://leetcode.com/problems/majority-element-ii/
     * 
     * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

        Follow-up: Could you solve the problem in linear time and in O(1) space?

        Example 1:

        Input: nums = [3,2,3]
        Output: [3]
        Example 2:

        Input: nums = [1]
        Output: [1]
        Example 3:

        Input: nums = [1,2]
        Output: [1,2]

    Idea: Atmost 2 elements can exists which occur more than n/3 times -> 2 majority elements. Use Moore's voting
    algorithm https://www.geeksforgeeks.org/n3-repeated-number-array-o1-space/.

    Time: O(n) - two traversals
    Space: O(1)

     * @param nums
     * @return
     */
    public List<Integer> majorityElement2(int[] nums) {
        // Moore's voting algorithm
        // 1. find top two candidates
        Integer first = null, second = null;
        int count1 = 0, count2 = 0;
        for(int i=0;i<nums.length;i++){
            if(first != null && nums[i] == first){
                count1+=1;
            } else if(second != null && nums[i] == second){
                count2 += 1;
            } else if(count1 == 0){ // anytime count drops to zero, reset first to currEle
                first = nums[i];
                count1 =1;
            } else if(count2 == 0){
                second = nums[i];
                count2 =1;
            } else { // new element, decrement both counts
                count1-=1;
                count2-=1;
            }
        }
        
        // 2. confirm their actual occurence count
        count1 =0;
        count2 =0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] == first){
                count1+=1;
            } else if(nums[i] == second){
                count2+=1;
            }
        }
        int idealMajorityCnt = nums.length/3;
        List<Integer> result = new ArrayList<>();
        if(count1 > idealMajorityCnt){
            result.add(first);
        }
        if(count2 > idealMajorityCnt){
            result.add(second);
        }
        return result;
    }
}