package algorithms.backtracking;

public class RobotPaths {
    int numPaths = 0;
    
    // i,j are current position of the robot, m,n indicate the limits in each direction
    // recomputes same sub problem again and again!
    void dfs(int i,int j, int m, int n){
        if(i==m-1 && j==n-1){
            // goal reached
            numPaths+=1;
            return;
        }
        if(i<m-1){ // go down
            dfs(i+1, j, m, n);
        }
        if(j<n-1){ // go right
            dfs(i, j+1, m, n);
        }
    }

    /**
     * 
     * https://leetcode.com/problems/unique-paths/
     * 
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

    The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

    How many possible unique paths are there?

    Example 1:

    Input: m = 3, n = 2
    Output: 3
    Explanation:
    From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
    1. Right -> Right -> Down
    2. Right -> Down -> Right
    3. Down -> Right -> Right
    Example 2:

    Input: m = 7, n = 3
    Output: 28

    Idea: Naive DFS expanding, and count paths when you reach the goal. Repeatedly explores the same sub path again, and is expensive => exponential!
    */
    public int uniquePaths(int m, int n) {
        dfs(0,0,m,n);
        return numPaths;
    }
}