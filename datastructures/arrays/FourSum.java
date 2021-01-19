package datastructures.arrays;

import java.util.*;

public class FourSum {
    public int fourSumCount2Slow(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int i=0;i<D.length;i++){
            countMap.put(D[i], countMap.getOrDefault(D[i], 0) +1);
        }
        int numQuadruples = 0;
        // Time: O(n*n*n) Space: O(n)
        for(int i=0;i<A.length;i++){
            for(int j=0;j<B.length;j++){
                for(int k=0;k<C.length;k++){
                    int target = -1 * (A[i] + B[j] + C[k]);
                    int numInstances = countMap.getOrDefault(target, 0);
                    numQuadruples += numInstances;
                }
            }
        }
        return numQuadruples;
    }
    // https://leetcode.com/problems/4sum-ii/
    /**
     * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

        To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

        Example:

        Input:
        A = [ 1, 2]
        B = [-2,-1]
        C = [-1, 2]
        D = [ 0, 2]

        Output:
        2

        Explanation:
        The two tuples are:
        1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
        2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
        
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        // use countMap to store first two pairs (a+b)
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int i=0;i<A.length;i++){
            for(int j=0;j<B.length;j++){
                int pair = A[i] + B[j];
                countMap.put(pair, countMap.getOrDefault(pair, 0) +1);
            }
        }
        // Time: O(n*n), Space: O(n*n)
        int numQuadruples = 0;
        for(int i=0;i<C.length;i++){
            for(int j=0;j<D.length;j++){
                int sum = C[i] + D[j];
                int target = -1*sum;
                if(countMap.containsKey(target)){
                    numQuadruples += countMap.get(target);
                }
            }
        }
        return numQuadruples;
    }
}
