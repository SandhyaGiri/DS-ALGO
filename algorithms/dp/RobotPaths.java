package algorithms.dp;

// Has problems related to robot starting on left corner and wanting to reach bottom right corner. 
// 1. unique paths to reach goal w/o obstacles
// 2. minimum path cost to reacg goal, given cost of stepping through each cell.
public class RobotPaths {
    
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

    DP based solution to reuse subproblems
    #paths to i,j is (#paths to i-1,j + #paths to i,j-1) as after reaching these previous positions, we can do a valid DOWN or RIGHT move.
    
    */
    public int uniquePaths(int m, int n) {
        int uniquePaths[][] = new int[m][n];
        for(int i=0;i<m;i++) {
            uniquePaths[i][0]= 1;
        }
        for(int j=0;j<n;j++){
            uniquePaths[0][j]= 1;
        }
        for(int i=1;i<m;i++) {
            for(int j=1;j<n;j++){
                uniquePaths[i][j]= uniquePaths[i-1][j]+uniquePaths[i][j-1];
            }
        }
        return uniquePaths[m-1][n-1];
    }

    /**
     * https://leetcode.com/problems/unique-paths-ii
     * 
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

        The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

        Now consider if some obstacles are added to the grids. How many unique paths would there be?

        An obstacle and empty space is marked as 1 and 0 respectively in the grid.

        Note: m and n will be at most 100.

        Example 1:

        Input:
        [
        [0,0,0],
        [0,1,0],
        [0,0,0]
        ]
        Output: 2
        Explanation:
        There is one obstacle in the middle of the 3x3 grid above.
        There are two ways to reach the bottom-right corner:
        1. Right -> Right -> Down -> Down
        2. Down -> Down -> Right -> Right

        Idea: use the same DP solution where if a block i,j has an obstacle then no paths go through it => uniquePaths[i][j]=0
        Also important is the initialization, in first row/col if any block has obstacle, the robot cannot move further (as no jumps are allowed)
        so any blocks after this obstacle block will also be unreachable, and should be initialized to 0.
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] uniquePaths = new int[m][n];
        boolean unreachableFound = false;
        for(int i=0;i<m;i++){
            if(obstacleGrid[i][0]==0 && !unreachableFound){ // reachable!
                uniquePaths[i][0]=1;
            } else {
                unreachableFound = true;
            }
        }
        unreachableFound = false;
        for(int j=0;j<n;j++){
            if(obstacleGrid[0][j]==0 && !unreachableFound){ // reachable!
                uniquePaths[0][j]=1;
            } else {
                unreachableFound = true;
            }
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                uniquePaths[i][j]= obstacleGrid[i][j]==0 ? uniquePaths[i-1][j]+uniquePaths[i][j-1] : 0;
            }
        }
        return uniquePaths[m-1][n-1];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int minPathCost[][] = new int[m][n];
        minPathCost[0][0]= grid[0][0];
        for(int i=1;i<m;i++){
            minPathCost[i][0]= minPathCost[i-1][0]+grid[i][0];
        }
        for(int j=1;j<n;j++){
            minPathCost[0][j]= minPathCost[0][j-1]+grid[0][j];
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                minPathCost[i][j]= Math.min(minPathCost[i-1][j], minPathCost[i][j-1])+grid[i][j];
            }
        }
        return minPathCost[m-1][n-1];
    }
}