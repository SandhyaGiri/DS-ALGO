package algorithms.greedy;

import java.util.*;

//https://leetcode.com/problems/insert-interval/
/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

    You may assume that the intervals were initially sorted according to their start times.

    Example 1:

    Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
    Output: [[1,5],[6,9]]
    Example 2:

    Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
    Output: [[1,2],[3,10],[12,16]]
    Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
    Example 3:

    Input: intervals = [], newInterval = [5,7]
    Output: [[5,7]]
    Example 4:

    Input: intervals = [[1,5]], newInterval = [2,3]
    Output: [[1,5]]
    Example 5:

    Input: intervals = [[1,5]], newInterval = [2,7]
    Output: [[1,7]]
 */
public class InsertInterval {
    
    /**
     * IDEA1: append this new interval to old list and do 
     * a merge overlapping intervals algo -> sort the list again
     * and merge them.
     * 
     * O(Nlog N)
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert1(int[][] intervals, int[] newInterval) {
        int[][] in = new int[intervals.length + 1][2];
        for(int i = 0; i < intervals.length; i++)
            in[i] = intervals[i];
        in[intervals.length] = newInterval;
        return merge(in);
    }
    
    // like merge intervals - O(nlogn)
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0) 
            return intervals;
        
        List<int[]> list = new ArrayList();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] in = intervals[0];
        int i = 1;
        while(i < intervals.length) {
            if(intervals[i][0] > in[1]) {
                list.add(in);
                in = intervals[i];
            } else {
                in[1] = Math.max(intervals[i][1], in[1]);
            }
            i++;
        }
        list.add(in);
        
        return list.toArray(new int[list.size()][]);
    }
    /**
     * IDEA2 : Since the given list is already sorted, traverse through the list.
     * For a given interval i, if i lies completely ahead of new, then insert i,
     * otherwise if new lies ahead of i, insert new and make i as the new "new".
     * otherwise adjust start, end values of the new interval using current i.
     * 
     * Time: O(n)
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList();
        for (int[] in : intervals) {
            if (in[1] < newInterval[0]) {
                list.add(in);
            } else if (newInterval[1] < in[0]) {
                list.add(newInterval);
                newInterval = in;
            } else {
                newInterval[0] = Math.min(newInterval[0], in[0]);
                newInterval[1] = Math.max(newInterval[1], in[1]);
            }
        }
        list.add(newInterval);

        return list.toArray(new int[list.size()][]);
    }
}
