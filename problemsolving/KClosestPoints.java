package problemsolving;

import java.util.*;

public class KClosestPoints {
    class Point implements Comparable<Point>{
        double dis;
        int index;
        Point(double dis, int i){
            this.dis = dis;
            this.index = i;
        }
        public int compareTo(Point other){
            int compareResult = Double.compare(this.dis, other.dis); // imp: remember Double class compares double values and returns an integer
            // System.out.println(this.dis + " - " + other.dis + " : " + compareResult);
            return compareResult;
        }
    }
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