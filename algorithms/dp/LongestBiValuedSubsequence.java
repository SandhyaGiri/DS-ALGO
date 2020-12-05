package algorithms.dp;

public class LongestBiValuedSubsequence {
    /**
     * [4,2,2,4,2] ==> 5 as entire array is a bi-valued sequence - i.e has only 2 values (2,4)
     * [1,2,3,2] ==> 3 as (2,3,2) is slice with max length containing 2 values
     * [0,5,4,4,5,12] ==> 4 as (5,4,4,5) is slice with max length containing 2 values
     * [4,4,4,7,7,6] ==> 5
     * 
     * subarray(continuous) and not subsequence
     * @param A
     * @return
     */
    public int getLbsLength(int[] A) {
        int n = A.length;
        int[] lbs = new int[n];
        int maxBiValuedSeqLength = Integer.MIN_VALUE;
        Integer val1 = null, val2 = null;
        for(int i=0;i<n;i++) {
            if(val1 == null) {
                val1 = A[i];
                lbs[i] = 1;
                continue;
            } else if(val2 == null) {
                if(A[i] == val1) {
                    // same element occuring again
                    lbs[i] = lbs[i-1] + 1;
                } else {
                    // new element in bi-value pair found
                    val2 = A[i];
                    lbs[i] = lbs[i-1] + 1;
                }
            } else {
                if(A[i] == val1 || A[i] == val2) {
                    // extend the sequence
                    lbs[i] = lbs[i-1] + 1;
                } else {
                    // breaks the current sequence
                    val1 = val2;
                    val2 = A[i];
                    lbs[i] = 2;
                }
            }
            if(lbs[i] > maxBiValuedSeqLength) {
                maxBiValuedSeqLength = lbs[i];
            }
        }
        return maxBiValuedSeqLength == Integer.MIN_VALUE ? 0 : maxBiValuedSeqLength;
    }
}