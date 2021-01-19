package datastructures.arrays;

import java.util.*;

/**

Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.

Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.

 */
class TwoSumSorted {
	/**
	 * 
	 * Idea: For each element we check if target-curr is in remainder of the array.
	 * 
	 * Time: O(n * logn)
	 * 
	 * Slighlty optimal: two pointer solution -> time: O(n), space: O(1)
	 * @param arr
	 * @param l
	 * @param r
	 * @param target
	 * @return
	 */
    private int binSearch(int[] arr, int l, int r, int target) {
        int found = -1;
        while(l<=r) {
            int mid = (int)Math.floor((l+r)/2);
            if(arr[mid] == target) {
                found = mid;
                break;
            } else if(arr[mid] < target) {
                l = mid+1;
            } else if(arr[mid] >target) {
                r = mid-1;
            }
        }
        return found;
    }
    
    public int[] twoSum(int[] numbers, int target) {
        for(int i=0;i<numbers.length;i++) {
            int pair_index = binSearch(numbers, i+1, numbers.length-1, target-numbers[i]);
            if(pair_index != -1) {
                return new int[]{i+1,pair_index+1};
            }
        }
        return new int[]{};
	}
	
	// https://leetcode.com/problems/max-number-of-k-sum-pairs/
	/**
	 * You are given an integer array nums and an integer k.

		In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.

		Return the maximum number of operations you can perform on the array.


		Example 1:

		Input: nums = [1,2,3,4], k = 5
		Output: 2
		Explanation: Starting with nums = [1,2,3,4]:
		- Remove numbers 1 and 4, then nums = [2,3]
		- Remove numbers 2 and 3, then nums = []
		There are no more pairs that sum up to 5, hence a total of 2 operations.
		Example 2:

		Input: nums = [3,1,3,4,3], k = 6
		Output: 1
		Explanation: Starting with nums = [3,1,3,4,3]:
		- Remove the first two 3's, then nums = [1,4,3]
		There are no more pairs that sum up to 6, hence a total of 1 operation.

		Idea: Basically count all pairs (two sum == k) inside the array which sum up to the k value.
		Time: O(nlogn)
	 * @param nums
	 * @param k
	 * @return
	 */
	public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int l=0,r=nums.length-1;
        int numOps = 0;
        while(l<r){
            int currSum = nums[l] + nums[r];
            if(currSum == k){
                numOps++;
                l++;
                r--;
            } else if(currSum > k){
                r--;
            } else {
                l++;
            }
        }
        return numOps;
    }
}

class TwoSumSortedRotated {
	/**
	 * Given an array that is sorted and then rotated around an unknown point.
	 * Find if the array has a pair with a given sum ‘x’. It may be assumed that all elements in the array are distinct.
	 * 
	 * idea: Find the starting point (either linear or bsearch to find minEleIndex).
	 * Again use two pointers l and index-1 is r. move the pointers in a circular fashion.
	 * @param numbers
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] numbers, int target) {
		// find the smallest element
		int i=0;
		while(i<numbers.length-1){
			if(numbers[i] > numbers[i+1]){
				break;
			}
			i++;
		}
		int l=i+1, r=l; // two pointers
		int[] result = new int[2];
		int n = numbers.length;
		while(l!=r){
			if(numbers[l] + numbers[r] == target){
				result[0] = l;
				result[1] = r;
				break;
			} else if(numbers[l] + numbers[r] < target){ // move in circular fashion
				l = (l+1)%n;
			} else {
				r = (n+r-1)%n;
			}
		}
		return result;
	}
}

class TwoSumAmazonFlights {
    class PairInt{
        int x;
        int y;
        PairInt(int a, int b){
            this.x = a;
            this.y = b;
        }
    }
    class Movie implements Comparable<Movie>{
        int id;
        int duration;
        Movie(int id, int duration){
            this.id = id;
            this.duration = duration;
        }
        public int compareTo(Movie other){
            return this.duration - other.duration;
        }
    }
	// METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
	PairInt IDsOfmovies(int flightDuration, int numMovies,
	                    ArrayList<Integer> movieDuration)
	{
		// WRITE YOUR CODE HERE
		// sort the movie durations
		Set<Movie> movies = new TreeSet<>();
		for(int i=0;i<numMovies;i++){
		    movies.add(new Movie(i,movieDuration.get(i)));
		}
		List<Movie> movieList = new ArrayList<>();
		for(Movie x: movies){
		    //System.out.println(x.id + "," + x.duration);
		    movieList.add(x);
		}
		int targetTime = flightDuration - 30;
		if(flightDuration > 30) {
		    // initialize two pointers at ends
    		int i=0, j=numMovies-1;
    		boolean pairFound = false;
    		int movie1Dur = -1, movie2Dur=-1;
    		int bestMovie1=-1,bestMovie2=-1;
    		while(i<j){
    		    Movie movie1 = movieList.get(i);
    		    Movie movie2 = movieList.get(j);
    		    if(movie1.duration + movie2.duration == targetTime){
    		        pairFound = true;
    		        //System.out.println("here" + movie1.id + "," + movie2.id);
    		        if(Math.max(movie1.duration, movie2.duration) > Math.max(movie1Dur, movie2Dur)){
    		            bestMovie1 = movie1.id;
    		            bestMovie2 = movie2.id;
    		            movie1Dur = movie1.duration;
    		            movie2Dur = movie2.duration;
    		        }
    		        i++;
    		        j--;
    		    } else if(movie1.duration + movie2.duration > targetTime){
    		        j--;
    		    } else {
    		        i++;
    		    }
    		}
    		return new PairInt(bestMovie1, bestMovie2);
		}
	    return new PairInt(-1,-1);
	}
	// METHOD SIGNATURE ENDS
}