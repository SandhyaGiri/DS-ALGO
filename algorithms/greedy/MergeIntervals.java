package algorithms.greedy;

import java.util.*;

// https://leetcode.com/problems/merge-intervals/
public class MergeIntervals {
    class Interval implements Comparable<Interval>{
        int a;
        int b;
        Interval(int x, int y){
            a = x;
            b = y;
        }
        public int compareTo(Interval other){
            // sort by incr start time, and incr end time
            return this.a == other.a ? this.b - other.b : this.a - other.a;
        }
    }
    /**
     * Given a collection of intervals, merge all overlapping intervals.

        Example 1:

        Input: [[1,3],[2,6],[8,10],[15,18]]
        Output: [[1,6],[8,10],[15,18]]
        Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
        Example 2:

        Input: [[1,4],[4,5]]
        Output: [[1,5]]
        Explanation: Intervals [1,4] and [4,5] are considered overlapping.

        Idea: first sort the given intervals by ascending start time, if conflict ascending end times.
        Then traverse the sorted list of intervals, fix the start, look for an interval end that is largest
        for this start. Stop when you encounter a start which is beyond the current start. Then add the current
        interval to result list.
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        int m = intervals.length;
        Set<Interval> intervalList = new TreeSet<Interval>();
        for(int i=0;i<m;i++){
            intervalList.add(new Interval(intervals[i][0], intervals[i][1]));
        }
        Integer start = null, end =null;
        List<Interval> results = new LinkedList<Interval>();
        for(Interval interval : intervalList){
            System.out.println(interval.a +"," + interval.b);
            if(start != null && end != null && interval.a > end){
                results.add(new Interval(start, end));
                start = null;
                end = null;
            }
            
            if(start == null){
                start = interval.a;
            } // all start pts are increasing, so we cannot later witness a start pt that is smaller than current start.
            
            if(end == null){
                end = interval.b;
            } else if(interval.b > end && interval.a >= start){ // as end points are sorted only on same start pts.
                // use the largest end (for big interval)
                end = interval.b;
            }
        }
        if(start != null && end != null){ // last one
           results.add(new Interval(start, end)); 
        }
        int[][] result = new int[results.size()][2];
        int j=0;
        for(Interval x : results){
            result[j][0] = x.a;
            result[j][1] = x.b;
            j++;
        }
        return result;
    }
}