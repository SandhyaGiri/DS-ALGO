package problemsolving;

import java.util.*;

// https://leetcode.com/problems/rotting-oranges/
public class RottingOranges {
    class IndexPair{
        int x;
        int y;
        IndexPair(int i, int j){
            this.x = i;
            this.y = j;
        }
    }
    /**
     * In a given grid, each cell can have one of three values:

    the value 0 representing an empty cell;
    the value 1 representing a fresh orange;
    the value 2 representing a rotten orange.
    Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

    Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
    Input: [[2,1,1],[1,1,0],[0,1,1]]
    Output: 4
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int numFreshOranges = 0;
        boolean[][] visited = new boolean[m][n];
        Queue<IndexPair> rottenOranges = new LinkedList<>();
        // traverse the grid and add current rotten cells to the queue
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 2){
                    rottenOranges.add(new IndexPair(i, j));
                }
                if(grid[i][j] == 1){
                    numFreshOranges++;
                }
            }
        }
        //System.out.println("Fresh oranges left: " + numFreshOranges);
        int minutes = 0; // number of levels in BFS
        while(!rottenOranges.isEmpty() ){
            int levelSize = rottenOranges.size();
            // counts for one minute reactions when fresh -> rotten based on adjacency
            for(int i=0;i<levelSize;i++){
                IndexPair index = rottenOranges.poll();
                visited[index.x][index.y] = true;
                // check neighbors for fresh oranges
                if(index.x+1 < m && grid[index.x+1][index.y] == 1 && !visited[index.x+1][index.y]){
                    numFreshOranges--;
                    grid[index.x+1][index.y] = 2;
                    rottenOranges.add(new IndexPair(index.x+1, index.y));
                }
                if(index.y+1 < n && grid[index.x][index.y+1] == 1 && !visited[index.x][index.y+1]){
                    numFreshOranges--;
                    grid[index.x][index.y+1] = 2;
                    rottenOranges.add(new IndexPair(index.x, index.y+1));
                }
                if(index.x-1>=0 && grid[index.x-1][index.y] == 1 && !visited[index.x-1][index.y]){
                    numFreshOranges--;
                    grid[index.x-1][index.y] = 2;
                    rottenOranges.add(new IndexPair(index.x-1, index.y));
                }
                if(index.y-1>=0 && grid[index.x][index.y-1] == 1 && !visited[index.x][index.y-1]){
                    numFreshOranges--;
                    grid[index.x][index.y-1] = 2;
                    rottenOranges.add(new IndexPair(index.x, index.y-1));
                }
            }
            minutes++;
            //System.out.println("Fresh oranges left: " + numFreshOranges + " at minute" + minutes);
        }
        // we take an extra minute to confirm there are no more fresh oranges hence -1
        return numFreshOranges == 0 ? (minutes > 0 ? minutes-1 : minutes) : -1;
    }
}