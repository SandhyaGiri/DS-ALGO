package problemsolving;

import java.util.*;

/**
 * https://leetcode.com/problems/max-area-of-island
 * 
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:

[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.

 */
public class IslandArea {
    class IndexPair{
        int i;
        int j;
        IndexPair(int x,int y){
            this.i=x;
            this.j=y;
        }
    }
    // i,j are potential start indices of the island
    int islandSize(int i, int j, int[][] grid, boolean[][] visited){
        int m = grid.length;
        int n = grid[0].length;
        Queue<IndexPair> q = new LinkedList<>();
        q.add(new IndexPair(i,j));
        int numLandCells =0;
        while(!q.isEmpty()){
            IndexPair cell = q.poll();
            if(!visited[cell.i][cell.j]){
                numLandCells++;
                visited[cell.i][cell.j]=true;
                // enqueue valid neighbors;
                if(cell.j-1>=0 && grid[cell.i][cell.j-1] == 1 && !visited[cell.i][cell.j-1]){
                    q.add(new IndexPair(cell.i,cell.j-1));
                }
                if(cell.j+1<n && grid[cell.i][cell.j+1] == 1 && !visited[cell.i][cell.j+1]){
                    q.add(new IndexPair(cell.i, cell.j+1));
                }
                if(cell.i-1>=0 && grid[cell.i-1][cell.j] == 1 && !visited[cell.i-1][cell.j]){
                    q.add(new IndexPair(cell.i-1, cell.j));
                }
                if(cell.i+1<m && grid[cell.i+1][cell.j] == 1 && !visited[cell.i+1][cell.j]){
                    q.add(new IndexPair(cell.i+1, cell.j));
                }
            }
        }
        return numLandCells;
    }
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxIslandArea = Integer.MIN_VALUE;
        boolean[][] visited = new boolean[m][n];
        // find first 1 from top row, searching l to r
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++) {
                if(!visited[i][j] && grid[i][j] == 1){
                    int size = islandSize(i,j,grid,visited);
                    if(size > maxIslandArea){
                        maxIslandArea= size;
                    }
                }
            }
        }
        return maxIslandArea == Integer.MIN_VALUE ? 0 :maxIslandArea;
    }
}