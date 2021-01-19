package algorithms.sorting;

// https://leetcode.com/problems/max-chunks-to-make-sorted/
public class MaxChunksToSort {
    /**
     * Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.

        What is the most number of chunks we could have made?

        Example 1:

        Input: arr = [4,3,2,1,0]
        Output: 1
        Explanation:
        Splitting into two or more chunks will not return the required result.
        For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.
        Example 2:

        Input: arr = [1,0,2,3,4]
        Output: 4
        Explanation:
        We can split into two chunks, such as [1, 0], [2, 3, 4].
        However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.
        Note:

        arr will have length in range [1, 10].
        arr[i] will be a permutation of [0, 1, ..., arr.length - 1]. // distinct numbers

        Idea: The first chunk can be found as the smallest k for which A[:k+1] == [0, 1, 2, ...k]; then we repeat this process.
        we know that sum of first n natural numbers from 0..n is n(n+1)/2.
        
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {
        int chunkSumSoFar = 0;
        int currSum = 0;
        int numChunks = 0;
        for(int i=0;i<arr.length;i++){
            currSum += arr[i];
            int expectedSum = i * (i+1)/2; // sum of first n numbers starting from 0
            //System.out.println("expected sum: "+ expectedSum);
            //System.out.println("curr sum: "+ currSum);
            //System.out.println("sum so far: " + chunkSumSoFar);
            if(expectedSum - chunkSumSoFar == currSum){
                numChunks+= 1; // found a chunk which has all needed numbers inside.
                chunkSumSoFar += currSum;
                currSum = 0; // begin a new chunk
            }
        }
        return numChunks == 0 ? 1 : numChunks;
    }
}
