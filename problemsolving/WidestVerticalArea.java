package problemsolving;

import java.util.*;

// https://leetcode.com/problems/widest-vertical-area-between-two-points-containing-no-points/
public class WidestVerticalArea {
    class PointComparator implements Comparator<int[]>{
        public int compare(int[] a, int[] b){
            return Integer.compare(a[0],b[0]); // sort by x-pos ascending
        }
    }
    public int maxWidthOfVerticalArea(int[][] points) {
        // sort by x position of the points
        Arrays.sort(points, new PointComparator());
        // find the max range (largest gap) between any two x positions
        int maxWidth = Integer.MIN_VALUE;
        int prevXPos = -1;
        for(int i=0;i<points.length;i++){
            if(prevXPos != -1){
                int width = points[i][0] - prevXPos;
                if(width > maxWidth){
                    maxWidth = width;
                }
            }
            prevXPos = points[i][0];
        }
        return maxWidth;
    }
}
