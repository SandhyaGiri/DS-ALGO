package algorithms.dp;

import java.util.*;

// https://leetcode.com/problems/non-overlapping-intervals/
// inverse problem of LongestChainOfPairs
public class NonOverlappingIntervals {
    class IntervalComparator implements Comparator<int[]>{
        public int compare(int[] x, int[] y){
            return x[0] == y[0] ? x[1] - y[1] : x[0] - y[0];
        }
    }
    /**
     * 
     * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping. 

        Example 1:

        Input: [[1,2],[2,3],[3,4],[1,3]]
        Output: 1
        Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
        Example 2:

        Input: [[1,2],[1,2],[1,2]]
        Output: 2
        Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
        Example 3:

        Input: [[1,2],[2,3]]
        Output: 0
        Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
        

        Note:

        You may assume the interval's end point is always bigger than its start point.
        Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.

        Idea: Find the longest chain of pairs after sorting the intervals by start time (and end time if a tie).
        Min number of intervals to be removed = #pairs - (longest chain length)

     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new IntervalComparator());
        int[] chainLength = new int[intervals.length];
        int maxChainLength = 0;
        for(int i=0;i<intervals.length;i++){
            // find which (best one) previous interval can be extended by current one
            int maxSubChainLength = 0;
            for(int j=i-1;j>=0;j--){
                if(intervals[j][1] <= intervals[i][0] && chainLength[j] > maxSubChainLength){
                   maxSubChainLength =  chainLength[j];
                }
            }
            chainLength[i] = maxSubChainLength+1;
            if(chainLength[i] > maxChainLength){
                maxChainLength = chainLength[i];
            }
        }
        return intervals.length - maxChainLength;
    }
}