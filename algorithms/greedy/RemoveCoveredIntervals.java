package algorithms.greedy;

import java.util.*;

// https://leetcode.com/problems/remove-covered-intervals/
public class RemoveCoveredIntervals {
    class IntervalComparator implements Comparator<int[]>{
        public int compare(int[] x, int[] y){
            return x[0] == y[0] ? x[1] - y[1] : x[0]-y[0];
        }
    }
    class Interval{
        int start;
        int end;
        Interval(int a, int b){
            this.start = a;
            this.end = b;
        }
    }
    int coveredBy(Interval x, Interval y){
        // returns 1 if x covers y, -1 if y covers x, 0 otherwise
        if(x.start <= y.start && x.end >= y.end){
            return 1;
        } else if(y.start <= x.start && y.end >= x.end){
            return -1;
        } else{
            return 0;
        }
    }
    /**
     * Given a list of intervals, remove all intervals that are covered by another interval in the list.

        Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.

        After doing so, return the number of remaining intervals.

        

        Example 1:

        Input: intervals = [[1,4],[3,6],[2,8]]
        Output: 2
        Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
        Example 2:

        Input: intervals = [[1,4],[2,3]]
        Output: 1
        Example 3:

        Input: intervals = [[0,10],[5,12]]
        Output: 2
        Example 4:

        Input: intervals = [[3,10],[4,10],[5,11]]
        Output: 2
        Example 5:

        Input: intervals = [[1,2],[1,4],[3,4]]
        Output: 1

        Idea: Sort the intervals by start time
     * @param intervals
     * @return
     */
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, new IntervalComparator());
        Stack<Interval> stack = new Stack<>();
        if(intervals.length > 0){
            for(int i=0;i<intervals.length;i++){
                Interval curr = new Interval(intervals[i][0], intervals[i][1]);
                if(stack.empty()){
                    stack.push(curr);
                } else{
                    Interval top = stack.peek();
                    int coveredReln = coveredBy(top, curr);
                    if(coveredReln < 0){
                        stack.pop();
                        stack.push(curr);
                    } else if(coveredReln == 0){
                        stack.push(curr);
                    }
                }   
            }
            return stack.size();
        }
        return 0;
    }
}
