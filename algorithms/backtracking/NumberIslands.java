package algorithms.backtracking;

import java.util.*;

// https://leetcode.com/problems/number-of-closed-islands/
public class NumberIslands {
    class Index{
        int i;
        int j;
        Index(int x, int y){
            this.i=x;
            this.j=y;
        }
        // equals impl needed for removing from frontier
        public boolean equals(Object other){
            if(other instanceof Index){
                Index otherIndex = (Index) other;
                return this.i==otherIndex.i && this.j == otherIndex.j;
            }
            return false;
        }
    }
    int[][] neighbors = {{1,0},{-1,0},{0,1},{0,-1}};
    boolean isSurrounded(int[][] grid, Index start, Set<Integer> explored){
        // reject invalid entries
        if(start.i <0 || start.i>= grid.length || start.j<0 || start.j>=grid[0].length){
            return false;
        }
        if(grid[start.i][start.j] == 1){ // terminate surround search on seeing 1
            return true;
        }
        int encodedVal = start.i * grid[0].length + start.j;
        explored.add(encodedVal);
        boolean surrounded = true;
        for(int i=0;i<neighbors.length;i++){
            int targetRowIndex = start.i + neighbors[i][0];
            int targetColIndex = start.j + neighbors[i][1];
            int target = targetRowIndex * grid[0].length + targetColIndex;
            if(!explored.contains(target)){
                // all unexplored paths should end in 1 (surrounded in all dirs by 1)
                if(surrounded && !isSurrounded(grid, new Index(targetRowIndex, targetColIndex), explored)){
                    surrounded = false;
                    break;
                }
            }
        }
        return surrounded;
    }
    /**
     * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

        Return the number of closed islands.

        Example 1:

        Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
        Output: 2
        Explanation: 
        Islands in gray are closed because they are completely surrounded by water (group of 1s).
        Example 2:



        Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
        Output: 1
        Example 3:

        Input: grid = [[1,1,1,1,1,1,1],
                    [1,0,0,0,0,0,1],
                    [1,0,1,1,1,0,1],
                    [1,0,1,0,1,0,1],
                    [1,0,1,1,1,0,1],
                    [1,0,0,0,0,0,1],
                    [1,1,1,1,1,1,1]]
        Output: 2
        

        Constraints:

        1 <= grid.length, grid[0].length <= 100
        0 <= grid[i][j] <=1

        Idea: Similar to enemy capture, start at any of the indices where value is 0. do DFS, keep adding curr index to explored set
        terminate on boundaries or 1. if all paths end in 1, we are surrounded by 1 - return true. If so, we can easily remove indices 
        from explored set to avoid checking them again.

     * @param grid
     * @return
     */
    public int closedIsland(int[][] grid) {
        LinkedList<Index> frontier = new LinkedList<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == 0){
                    frontier.addLast(new Index(i, j));
                }
            }
        }
        int islandsCount =0;
        while(frontier.size() > 0){
            Index start = frontier.poll(); // returns next index to be checked
            Set<Integer> explored = new HashSet<>();
            if(isSurrounded(grid, start, explored)){
                islandsCount+=1;
                // remove explored from frontier
                for(Integer index: explored){
                    int row = index/grid[0].length;
                    int col = index % grid[0].length;
                    frontier.remove(new Index(row, col));
                }
            }
        }
        return islandsCount;
    }
}
