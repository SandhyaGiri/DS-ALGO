package algorithms.dp;

// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
public class LongestIncreasingPathMatrix {
    enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }
    int[] getNextIndex(int[][] matrix, int i, int j, Direction direction){
        switch(direction){
            case LEFT:
                return new int[]{i,j-1};
            case RIGHT:
                return new int[]{i,j+1};
            case UP:
                return new int[]{i-1,j};
            case DOWN:
                return new int[]{i+1,j};
        }
        return new int[]{};
    }
    int getNextValue(int[][] matrix, int i, int j, Direction direction){
        int m = matrix.length;
        int n = matrix[0].length;
        int nextVal = Integer.MIN_VALUE; // if outside boundary return minval
        switch(direction){
            case LEFT:
                if(j-1>=0){
                    nextVal = matrix[i][j-1];
                }
                break;
            case RIGHT:
                if(j+1< n){
                    nextVal = matrix[i][j+1];
                }
                break;
            case UP:
                if(i-1 >=0){
                    nextVal = matrix[i-1][j];
                }
                break;
            case DOWN:
                if(i+1 < m){
                    nextVal = matrix[i+1][j];
                }
                break;
        }
        return nextVal;
    }
    int dfsUtil(int[][] matrix, int i, int j, int[][] dp){
        if(dp[i][j] == 0){ // we haven't yet discovered paths starting from dp[i][j]
            dp[i][j] = 1; // one element path
            for(Direction dir: Direction.values()){
                int nextVal = getNextValue(matrix, i, j, dir);
                if(nextVal > matrix[i][j]){ // we can extend the path
                    int[] nextIndex = getNextIndex(matrix, i, j, dir);
                    dp[i][j] = Math.max(dp[i][j], 1+dfsUtil(matrix, nextIndex[0], nextIndex[1], dp));
                }
            }
        }
        return dp[i][j];
    }
    /**
     * Given an integer matrix, find the length of the longest increasing path.

        From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

        Example 1:

        Input: nums = 
        [
        [9,9,4],
        [6,6,8],
        [2,1,1]
        ] 
        Output: 4 
        Explanation: The longest increasing path is [1, 2, 6, 9].
        Example 2:

        Input: nums = 
        [
        [3,4,5],
        [3,2,6],
        [2,2,1]
        ] 
        Output: 4 
        Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

        Idea: each of the entries of the matrix can be a start of the sequence, so we need to check
        longest increasing pathlens from each of these starting positions. We can store this info in
        a dp[][] matrix, so that it can be reused for some other starting pos, for which this ele is
        a neighbor.

        for each i,j check if any of the neighbors are > than curre value, then compute their (dp[i+1][j] ex.)
        then curr value is just 1+dp of valid neighbor, find the max across all possible neighbors.
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        // if only right and down moves allowed, same as robot paths. single DP matrix enough
        int m = matrix.length;
        if(m > 0){
            int n = matrix[0].length;
            int maxPathLen = Integer.MIN_VALUE;
            int[][] dp = new int[m][n];
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    int pathLen = dfsUtil(matrix, i, j, dp);
                    if(pathLen > maxPathLen){
                        maxPathLen = pathLen;
                    }
                }
            }
            return maxPathLen;
        }
        return 0;
    }
}
