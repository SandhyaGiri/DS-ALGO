package algorithms.dp;

// https://leetcode.com/problems/longest-mountain-in-array/
public class LongestMountainSubarray {
    public int longestMountain(int[] A) {
        int n = A.length;
        // we do this way so that at the mountain peak, both subarrays meet and we can
        // easily subtract two values to get mountain length
        int[] lisLeft = new int[n]; // Longest increasing subarray from left to right
        int[] lisRight = new int[n]; // Longest increasing subarray from right to left
        for(int i=0;i<n;i++){
            if(i>0 && A[i] > A[i-1]){
                lisLeft[i] = lisLeft[i-1]+1;
            } else{
                lisLeft[i] = 1; // start a new subarray
            }
        }
        for(int i=n-1;i>=0;i--){
            if(i<n-1 && A[i] > A[i+1]){
                lisRight[i] = lisRight[i+1]+1; // extend an already increasing subarray
            }else{
                lisRight[i] = 1; // start a new subarray
            }
        }
        int maxMountainLength = 0;
        for(int i=0;i<n;i++){ // calculate mountain lengths
            int mountainLen = lisLeft[i] + lisRight[i] -1;
            // more than one element in left and right subarray check
            if(lisLeft[i] > 1 && lisRight[i]> 1 && mountainLen > maxMountainLength){
                maxMountainLength = mountainLen;
            }   
        }
        return maxMountainLength;
    }
}
