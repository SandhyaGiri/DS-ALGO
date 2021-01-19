package datastructures.arrays;

public class SpiralMatrix {
    int populateBoundary(int[][] matrix, int startVal, int i, int j, int n){
        // first row ->
        for(int col=j;col<n;col++){
            matrix[i][col] = startVal;
            startVal+=1;
        }
        // last col 
        for(int row=i+1;row<n;row++){
            matrix[row][n-1] = startVal;
            startVal += 1;
        }
        // last row
        if(n-1 != i){ // last row not same as first row
            for(int col=n-2;col>=j;col--){
                matrix[n-1][col] = startVal;
                startVal +=1;
            }
        }
        // first col
        if(j != n-1){ //this is diff from last col
            for(int row=n-2;row>i;row--){
                matrix[row][j] = startVal;
                startVal+=1;
            }
        }
        return startVal;
    }
    // https://leetcode.com/problems/spiral-matrix-ii/
    /**
     * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
     * 
     * Ex:
     * Input: n = 3
     * Output: [[1,2,3],[8,9,4],[7,6,5]]
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int i=0,j=0,size=n;
        int startVal=1;
        while(i < size){
            // also returns next startVal
            startVal = populateBoundary(matrix, startVal, i, j, size);
            i++;
            j++;
            size--;
        }
        return matrix;
    }
}
