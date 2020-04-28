package datastructures.arrays;

import java.util.*;

/**
 * https://leetcode.com/problems/container-with-most-water/
 * 
 */
class ContainerMostWater {
    public int maxArea(int[] height) {
        int maxWater = Integer.MIN_VALUE;
        int l=0,r=height.length-1;
        while(l<r) {
            int h = Math.min(height[l], height[r]);
            int w = r-l;
            int area = h*w;
            if(area > maxWater) {
                maxWater = area;
            } else if(height[l]<height[r]){
                l++;
            } else {
                r--;
            }
        }
        return maxWater;
    }
}

/**
 * https://leetcode.com/problems/trapping-rain-water/
 * 
 */
class TrappingWater {
    public int trap(int[] height) {
        int currMax = Integer.MIN_VALUE;
        int trappedWater =0;
        Stack<Integer> bars = new Stack<Integer>();
        int i=0;
        // skip initial zeros
        for(;i<height.length;i++) {
            if(height[i] != 0){
                break;
            }
        }
        while(i<height.length) {
            if(height[i] >= currMax) {
                // pop nodes and calc trapped water
                while(!bars.empty()) {
                   trappedWater += currMax-bars.pop();
                }
                currMax= height[i];
            }
            bars.push(height[i]);
            i++;
        }
        while(!bars.empty()) {
            int newMax = bars.pop();
            while(!bars.empty() && bars.peek() >= newMax) {
                newMax=bars.pop();
            }
            while(!bars.empty() && bars.peek() <= newMax) {
                int newLevel = bars.pop();
                trappedWater += newMax-newLevel;
            }
        }
        return trappedWater;
    }
}