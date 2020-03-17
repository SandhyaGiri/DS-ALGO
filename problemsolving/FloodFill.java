package problemsolving;

import java.util.*;

/**
 * https://leetcode.com/problems/flood-fill/ or Paint fill
 * 
 * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

At the end, return the modified image.

Example 1:
Input: 
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: 
From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected 
by a path of the same color as the starting pixel are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected
to the starting pixel.
Note:

The length of image and image[0] will be in the range [1, 50].
The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
The value of each color in image[i][j] and newColor will be an integer in [0, 65535].

Idea: use a queue to sequentially visit new cells which need to repainted to new color. if a neighboring cell is already repainted, 
don't enqueue it again. only neighboring cells with same color as starting cell (sr,sc) need to be repainted and hence enqueued.
When a cell is removed from queue, paint to new color and search for new neighbors which should be repainted and enqueue them.
 */
public class FloodFill {
    class IndexPair{
        int i;
        int j;
        IndexPair(int x, int y){
            this.i=x;
            this.j=y;
        }
    }
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int m = image.length;
        int n = image[0].length;
        int startingColor =image[sr][sc];
        boolean changed[][]=new boolean[m][n];
        Queue<IndexPair> q = new LinkedList<IndexPair>();
        q.add(new IndexPair(sr,sc));
        while(!q.isEmpty()){
            IndexPair cell = q.poll();
            if(!changed[cell.i][cell.j]){
                image[cell.i][cell.j] = newColor;
                changed[cell.i][cell.j] = true;
            }
            // left neighbor
            if(cell.j-1>=0 && image[cell.i][cell.j-1] == startingColor && !changed[cell.i][cell.j-1]){
                q.add(new IndexPair(cell.i, cell.j-1));
            }
            // right neighbor
            if(cell.j+1<n && image[cell.i][cell.j+1] == startingColor && !changed[cell.i][cell.j+1]){
                q.add(new IndexPair(cell.i, cell.j+1));
            }
            // up
            if(cell.i-1>=0 && image[cell.i-1][cell.j] == startingColor && !changed[cell.i-1][cell.j]){
                q.add(new IndexPair(cell.i-1, cell.j));
            }
            // down
            if(cell.i+1<m && image[cell.i+1][cell.j] == startingColor && !changed[cell.i+1][cell.j]){
                q.add(new IndexPair(cell.i+1, cell.j));
            }
        }
        return image;
    }
}