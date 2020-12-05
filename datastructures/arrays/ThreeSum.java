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
                pairs.add(Arrays.asList(nums[a],nums[b],-1*sum)); // target sum is the third number
                int tmp1=nums[a],tmp2=nums[b];
                // move to next unique pair
                while(a<b && nums[a]==tmp1){
                    a++;
                }
                if(a<b && nums[a]== tmp1){ // no change in a, change the b pointer atleast to get a unique pair
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
        Integer prev = null;
        for(int k=nums.length-1;k>=2;k--){
            if(prev == null || nums[k] != prev){ // to avoid duplicate entries in result, as we already found all unqiue two pairs for prev.
                List<List<Integer>> candidate = twoSum(nums, 0,k-1,-1*nums[k]);
                if(candidate != null && !candidate.isEmpty()){
                    result.addAll(candidate);
                }  
                prev = nums[k];
            }
        }
        return result;
    }
}

// https://leetcode.com/problems/3sum-closest
class ThreeSumClosest {
    /
    Integer twoSumClosest(int[] nums, int l, int r, int threeSumTarget, int thirdNum){
        if(l==r){
            return null;
        }
        int minDiffToTarget = Integer.MAX_VALUE;
        int minDiffSum = 0;
        for(int i=l;i<r-1;i++){
            int twoSumTarget = threeSumTarget - thirdNum;
            int index = Arrays.binarySearch(nums, i+1, r, twoSumTarget-nums[i]);
            if(index >= 0){//positive - exact number exists
                return nums[i] + nums[index];
            } else {// has -(inserpos)-1
                int insertPos = -1* (index+1);
                // 2 possibilities , floor and ceil
                int ceilPos = insertPos >= r ? r-1 : insertPos;
                int floorPos = insertPos-1 > l ? insertPos-1 : l;
                if(ceilPos != i){
                    int currTwoSumCeil = nums[i] + nums[ceilPos];
                    //System.out.println("potential 2 sum (ceil):" + currTwoSumCeil+ ", for thirdNum: " + thirdNum);
                    int currSum = thirdNum + currTwoSumCeil;
                    if(Math.abs(currSum-threeSumTarget) < minDiffToTarget){
                        minDiffToTarget = Math.abs(currSum-threeSumTarget);
                        minDiffSum = currTwoSumCeil;
                    }
                }
                if(floorPos != i){
                    int currTwoSumFloor = nums[i] + nums[floorPos];
                    //System.out.println("potential 2 sum (floor):" + currTwoSumFloor+ ", for thirdNum: " + thirdNum);
                    int currSum = thirdNum + currTwoSumFloor;
                    if(Math.abs(currSum-threeSumTarget) < minDiffToTarget){
                        minDiffToTarget = Math.abs(currSum-threeSumTarget);
                        minDiffSum = currTwoSumFloor;
                    }
                }
            }
        }
        return minDiffSum;
    }
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minDiffToTarget = Integer.MAX_VALUE;
        int minDiffSum = 0;
        for(int i=0;i<nums.length-2;i++){ // last 2 numbers for finding two sum
            int num1 = nums[i];
            Integer twoSum = twoSumClosest(nums, i+1, nums.length, target, num1);
            if(twoSum != null){
                int threeSum = num1+twoSum;
                System.out.println("potential 3 sum:" + threeSum);
                if(Math.abs(threeSum-target) < minDiffToTarget){
                    minDiffToTarget = Math.abs(threeSum-target);
                    minDiffSum = threeSum;
                }   
            }
        }
        return minDiffSum;
    }

    // Idea2: using same 2 pointers, but also maintain min diff of threesum to target 
    public int threeSumClosest2Pointers(int[] nums, int target) {
        int diff = Integer.MAX_VALUE, sz = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < sz && diff != 0; ++i) {
            int lo = i + 1, hi = sz - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (Math.abs(target - sum) < Math.abs(diff))
                    diff = target - sum;
                if (sum < target)
                    ++lo;
                else
                    --hi;
            }
        }
        return target - diff;
    }
}