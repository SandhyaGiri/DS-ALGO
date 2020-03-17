package datastructures.arrays;

import java.util.*;

/**
 * https://leetcode.com/problems/3sum/
 * 
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

nums = [-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6]
solution: 
[[-4,-2,6],[-4,0,4],[-2,-2,4],[-4,1,3],[-4,2,2],[-2,0,2]]

 */
public class ThreeSum {
    // finds all pairs in the nums array from l..r, which sum to the value.
    List<List<Integer>> twoSum(int[] nums, int l, int r, int sum){
        List<List<Integer>> pairs = new ArrayList<>();
        int a=l,b=r;
        while(a<b){
            int candSum = nums[a]+nums[b];
            if(candSum == sum){
                pairs.add(Arrays.asList(nums[a],nums[b],-1*sum));
                int tmp1=nums[a],tmp2=nums[b];
                // move to next unique pair
                while(a<b && nums[a]==tmp1){
                    a++;
                }
                if(a<b && nums[a]== tmp1){
                    while(b>a && nums[b]==tmp2){
                        b--;
                    }
                }
            } else if(candSum > sum){
                b--;
            } else {
                a++;
            }
        }
        return pairs;
    }
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        Integer curr = null;
        for(int k=nums.length-1;k>=2;k--){
            if(curr == null || nums[k] != curr){
                List<List<Integer>> candidate = twoSum(nums, 0,k-1,-1*nums[k]);
                if(candidate != null && !candidate.isEmpty()){
                    result.addAll(candidate);
                }  
                curr = nums[k];
            }
        }
        return result;
    }
}