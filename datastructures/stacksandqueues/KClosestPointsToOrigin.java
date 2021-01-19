package datastructures.stacksandqueues;

import java.util.*;

// https://leetcode.com/problems/k-closest-points-to-origin/
public class KClosestPointsToOrigin {
    class Point implements Comparable<Point>{
        double dis;
        int index;
        Point(double dis, int i){
            this.dis = dis;
            this.index = i;
        }
        public int compareTo(Point other){
            int compareResult = Double.compare(this.dis, other.dis);
            // System.out.println(this.dis + " - " + other.dis + " : " + compareResult);
            return compareResult;
        }
    }
    /**
     * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

 

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
 

Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000

    Time: O(n*logk)
    Space: O(n) as we store all points in the min heap -> can be reduced to O(k) if we store only top k elements in max heap and add others based on comparison
    to top element.
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Point> minHeap = new PriorityQueue<>();
        for(int i=0;i<points.length;i++){
            double disToOrigin = Math.sqrt(points[i][0]*points[i][0] + points[i][1]*points[i][1]);
            // System.out.println(disToOrigin);
            minHeap.add(new Point(disToOrigin, i));
        }
        int[][] kpoints = new int[K][2];
        for(int i=0;i<K;i++){
            Point closestPoint = minHeap.poll();
            kpoints[i][0] = points[closestPoint.index][0];
            kpoints[i][1] = points[closestPoint.index][1];
        }
        return kpoints;
    }
}
