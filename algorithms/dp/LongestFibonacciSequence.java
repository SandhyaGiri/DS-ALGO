package algorithms.dp;

import java.util.*;

// https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/
public class LongestFibonacciSequence {

    /**
     * Correct solution: like an extension to LIS, if we consider for k, any previous j as 
     * the ending pair (j,k) of a fib series. Then we need to find (i,j) pair such that 
     * A[i]+A[j] == A[k], i.e simply check if A[k]-A[j] exists and its index i <j.
     * Now (j,k) pair extends LFS ending at (i,j).
     * 
     * @param A
     * @return
     */
    public int lenLongestFibSubseq(int[] A) {
        int n = A.length;
        Map<Integer, Integer> indices = new HashMap<>();
        for(int i=0;i<n;i++){
            indices.put(A[i], i);
        }
        // consider j, k as one element (j,k) and maintain lfs
        Map<Integer, Integer> lfs = new HashMap<>();
        int maxFibonacciSeqLength = 0;
        for(int k=0;k<n;k++){
            for(int j=0;j<k;j++){
                // check if A[k]-A[j] exists
                int i =indices.getOrDefault(A[k]- A[j], -1);
                if(i>=0 && i<j){
                    // get length of fs ending at (i,j) or 2 for new fs
                    // we encode the tuple (i,j) as i*n+j
                    int currFSLen = lfs.getOrDefault(i*n+j, 2) + 1;
                    lfs.put(j*n+k, currFSLen);
                    maxFibonacciSeqLength = Math.max(maxFibonacciSeqLength, currFSLen);
                }
            }
        }
        return maxFibonacciSeqLength;
    }

    public int lenLongestFibSubseqOwn(int[] A) {
        /**
        This solution chooses the max length fib seq until i, this could restrict our
        choices in the future resulting in shorter lengths.
        [2,4,7,8,9,10,14,15,18,23,32,50]
        here we will choose 2,8,10,18 (but we have no more possibility to add numbers)
        when ideal length is: 4, 14, 18, 32, 50
        */
        int[] lfs = new int[A.length];
        int[] prevIndex = new int[A.length];
        int maxFibonacciSeqLength = Integer.MIN_VALUE;
        if(A.length >= 3){
            for(int i=0;i<A.length;i++){
                int maxLength = Integer.MIN_VALUE;
                int candidate = -1;
                for(int j=i;j>=0;j--){
                    // check for extending an existing fibonacci seq. ( 1,2,3 elements all fixed)
                    if(lfs[j]>=3 && A[j] + A[prevIndex[j]] == A[i] && lfs[j] > maxLength){
                        maxLength = lfs[j];
                        candidate = j;
                    }
                }
                // check for two seed starters that sum up to A[i]
                int l=0,r=i-1;
                while(l<r && r>=0 && candidate == -1){ // once found or already found break
                    if(A[l]+A[r] == A[i]){
                        maxLength = 2;
                        candidate = r; // choose the right most seed
                    } else if(A[l]+A[r] > A[i]){
                        r--;
                    } else{
                        l++;
                    }
                }
                if(candidate == -1){
                    // no chain exists
                    lfs[i] = 0;
                    prevIndex[i] = -1;
                } else {
                    // extend an existing chain
                    lfs[i]= maxLength+1;
                    prevIndex[i]= candidate;
                }
                if(lfs[i] > maxFibonacciSeqLength){
                    maxFibonacciSeqLength = lfs[i];
                }
            }
            System.out.println(Arrays.toString(lfs));
            System.out.println(Arrays.toString(prevIndex));
            return maxFibonacciSeqLength == Integer.MAX_VALUE ? 0 : maxFibonacciSeqLength;
        }
        return 0;
    }
}
