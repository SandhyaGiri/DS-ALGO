package datastructures.arrays;

// https://leetcode.com/problems/largest-rectangle-in-histogram/
public class LargestRectangleInHistogram {
    /**
     * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
     * 
        Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
        The largest rectangle is shown in the shaded area, which has area = 10 unit. (between bars 5 and 6)
        Example:

        Input: [2,1,5,6,2,3]
        Output: 10

        Idea: For each i, we need to look to left and right and find largest width of a rectangle with height i. To do this, maintain two arrays
        largest increasing subarray ending at i with values >= i, and similarly another array for lis starting at i with values >=i.
        adding these two values for any i (and -1) gives the max width possible for a rectangle whose height can be i. Use this compute
        the maximum area.
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if(n == 0){
            return 0;
        }
        int[] lisEnding = new int[n]; // li subarray ending at i, with values >= i
        int[] lisStarting = new int[n]; // lis starting at i with values >= i
        lisEnding[0]=1;
        lisStarting[n-1]=1;
        // similar to stock span on the left?
        for(int i=1;i<n;i++){
            if(heights[i] <= heights[i-1]){
                int lisEndingHere = lisEnding[i-1];
                int prevIndex = i- lisEndingHere;
                while(prevIndex >= 0 && heights[i] <= heights[prevIndex]){
                    lisEndingHere += lisEnding[prevIndex];
                    prevIndex -= lisEnding[prevIndex];
                }
                lisEnding[i] = lisEndingHere;
            } else {
                lisEnding[i] = 1;
            }
        }
        // similar to stock span on the right?
        for(int i=n-2;i>=0;i--){
            if(heights[i] <= heights[i+1]){
                int lisStartingHere = lisStarting[i+1];
                int nextIndex = i+ lisStartingHere;
                while(nextIndex < n  && heights[i] <= heights[nextIndex]){
                    lisStartingHere += lisStarting[nextIndex];
                    nextIndex += lisStarting[nextIndex];
                }
                lisStarting[i] = lisStartingHere;
            } else {
                lisStarting[i] = 1;
            }
        }
        // compute largest rectangle area with each i as the height of the rectangle
        // and width is dictated by two lis sequences to left and right
        int maxArea = 0;
        for(int i=0;i<n;i++){
            int width = lisEnding[i] + lisStarting[i] -1;
            int height = heights[i];
            int area= width * height;
            if(area > maxArea){
                maxArea = area;
            }
        }
        return maxArea;
    }
}
