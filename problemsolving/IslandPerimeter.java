package problemsolving;

import java.util.*;

/**
 * https://leetcode.com/problems/island-perimeter/
 * 
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

 

Example:

Input:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Output: 16

Idea: Start all land cells with a peri of 4. For each cell subtract the shared borders from 4 to get its final perimeter.
In order to visit each node, use a queue with first land cell found, then keep enqueing unvisited neighbors.
 */
public class IslandPerimeter {
    class IndexPair{
        int i;
        int j;
        int perimeter;
        IndexPair(int x, int y, int peri){
            this.i=x;
            this.j=y;
            this.perimeter=peri;
        }
    }
    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<IndexPair> q = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        // find first 1 from top row, searching l to r
        boolean startFound = false;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++) {
                if(grid[i][j] == 1){
                    q.add(new IndexPair(i,j,4));
                    startFound=true;
                    break;
                }
            }
            if(startFound){
                break;
            }
        }
        int perimeter =0;
        while(!q.isEmpty()){
            IndexPair cell = q.poll();
            if(!visited[cell.i][cell.j]){
                // System.out.println(cell.i+","+cell.j);
                visited[cell.i][cell.j]= true;
                int sharedNeighbors =0;
                if(cell.j-1>=0 && grid[cell.i][cell.j-1] == 1){
                    if(!visited[cell.i][cell.j-1])
                        q.add(new IndexPair(cell.i,cell.j-1, 4));
                    sharedNeighbors++;
                }
                if(cell.j+1<n && grid[cell.i][cell.j+1] == 1){
                    if(!visited[cell.i][cell.j+1])
                        q.add(new IndexPair(cell.i, cell.j+1, 4));
                    sharedNeighbors++;
                }
                if(cell.i-1>=0 && grid[cell.i-1][cell.j] == 1){
                    if(!visited[cell.i-1][cell.j])
                        q.add(new IndexPair(cell.i-1, cell.j, 4));
                    sharedNeighbors++;
                }
                if(cell.i+1<m && grid[cell.i+1][cell.j] == 1){
                    if(!visited[cell.i+1][cell.j])
                        q.add(new IndexPair(cell.i+1, cell.j, 4));
                    sharedNeighbors++;
                }
                // System.out.println(cell.perimeter+ " "+ sharedNeighbors);
                perimeter+=cell.perimeter-sharedNeighbors;   
            }
        }
        return perimeter;
    }
}