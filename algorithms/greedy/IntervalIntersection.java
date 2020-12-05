package algorithms.greedy;

import java.util.*;

// https://leetcode.com/problems/interval-list-intersections/
// Idea: similar to merge two sorted arrays, move the pointer on the list whose interval endpoint has been exhausted,
// while holding the pointer to other list whose interval is large containing other elements.
public class IntervalIntersection {
    class Interval{
        int a;
        int b;
        Interval(int a, int b){
            this.a = a;
            this.b = b;
        }
    }
    /**
     * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

        Return the intersection of these two interval lists.

        (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

        Example 1:
        Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
        Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
        Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
        

        Note:

        0 <= A.length < 1000
        0 <= B.length < 1000
        0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
        NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
     * @param A
     * @param B
     * @return
     */
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<Interval> result = new LinkedList<Interval>();
        int firstIndex = 0, secondIndex = 0;
        int m = A.length, n = B.length;
        Integer start = null, end = null;
        while(firstIndex < m && secondIndex < n){
            if(start == null){
                if(A[firstIndex][0] > B[secondIndex][0]){
                    start = A[firstIndex][0];
                } else{
                    start = B[secondIndex][0];
                }
            }
            if(end == null){
                if(A[firstIndex][1] < B[secondIndex][1]){
                    end = A[firstIndex][1];
                    firstIndex++;
                } else {
                    end = B[secondIndex][1];
                    secondIndex++;
                }
            }
            if(start <= end){
                result.add(new Interval(start, end));   
            }
            start = null;end=null;
        }
        int[][] results = new int[result.size()][2];
        int i=0;
        for(Interval res: result){
            results[i][0] = res.a;
            results[i][1] = res.b;
            i++;
        }
        return results;
    }
}