package algorithms.dp;

/**
 * Given a Binary matrix with 0s and 1s, find the maximum size submatrix with all 1s.
 * 
 * Similar to: https://leetcode.com/problems/maximal-square/
 */
public class MaxSquareSubMatrix {

    private static int findMaxSquareSubmatrix(int[][] M) {
        int[][] S = new int[M.length][M[0].length];
        // cop first row and column
        for(int i=0;i<M.length;i++) {
            S[i][0] = M[i][0]; // 1 here indicates it is a 1*1 submatrix containing all 1s
        }
        for(int j=0;j<M[0].length;j++) {
            S[0][j] = M[0][j]; // 1 here indicates it is a 1*1 submatrix containing all 1s
        }
        int max = Integer.MIN_VALUE;
        int x=-1,y=-1;
        for(int i=1;i<M.length;i++) {
            for(int j=1;j<M[0].length;j++) {
                if(M[i][j] == 1) {
                    S[i][j] = 1+ Math.min(Math.min(S[i-1][j-1],S[i][j-1]),S[i-1][j]);
                } else {
                    S[i][j] = 0;
                }
                if(S[i][j] > max) {
                    max = S[i][j];
                    x=i;y=j;
                }
            }
        }
        System.out.println("Maximum square sub matrix of all 1s found at: " + (x-max+1) + "," + (y-max+1));
        return max;
    }

    /**
     * 
     * Count Square Submatrices with All Ones
     * 
     * https://leetcode.com/problems/count-square-submatrices-with-all-ones
     * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

 

        Example 1:

        Input: matrix =
        [
        [0,1,1,1],
        [1,1,1,1],
        [0,1,1,1]
        ]
        Output: 15
        Explanation: 
        There are 10 squares of side 1.
        There are 4 squares of side 2.
        There is  1 square of side 3.
        Total number of squares = 10 + 4 + 1 = 15.
        Example 2:

        Input: matrix = 
        [
        [1,0,1],
        [1,1,0],
        [1,1,0]
        ]
        Output: 7
        Explanation: 
        There are 6 squares of side 1.  
        There is 1 square of side 2. 
        Total number of squares = 6 + 1 = 7.

        Solution: O(n*3)
     */
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int sumOnes[][] = new int[m][n];
        // sum of 1s in matrix from [0,0] till [i,j]
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                sumOnes[i][j] = matrix[i][j];
                if(i-1>=0){
                    sumOnes[i][j] += sumOnes[i-1][j];
                }
                if(j-1 >= 0){
                    sumOnes[i][j] += sumOnes[i][j-1];
                }
                if(i-1 >=0 && j-1>=0){
                    sumOnes[i][j] -= sumOnes[i-1][j-1];
                }
            }
        }
        // num of 1*1 submatrices = number of 1's in whole matrix 
        int countSquareSubs = sumOnes[m-1][n-1];
        for(int size=2;size<=Math.min(m,n);size++){
            for(int i=size-1;i<m;i++){
                for(int j=size-1;j<n;j++){
                    int sum = sumOnes[i][j];
                    if(i-size>=0){
                        sum -= sumOnes[i-size][j];
                    }
                    if(j-size >= 0){
                        sum -= sumOnes[i][j-size];
                    }
                    if(i-size >=0 && j-size>=0){
                        sum += sumOnes[i-size][j-size];
                    }
                    // System.out.println(i + "," + j + "=" + sum);
                    if(sum == size*size){
                        countSquareSubs++;
                    }
                }
            }
        }
        return countSquareSubs;
    }
    public static void main(String[] args) {
        int[][] arr = new int[][] { 
            {0,1,1,0,1}, 
            {1,1,0,1,0}, 
            {1,1,1,1,0}, 
            {1,1,1,1,1},
            {0,0,0,0,0} 
            };
        findMaxSquareSubmatrix(arr);
    }
}