package problemsolving;

class SetMatrixZeros {
    /**
     * Time: O(mn) - as all the elements need to be visited
     * Space: O(m+n) - we need extra arrays to track if a row/col needs to be all zeros.
     * 
     * Improvement: use sets to store row/col numbers instead of arrays (may be useful if its sparse, we can reduce the array space).
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean rows[] = new boolean[m];
        boolean cols[] = new boolean[n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(rows[i] || cols[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    /**
     * Time: O(mn * (m+n)) - for each of mn elements we traverse the row & col that they belong to.
     * Space: O(1) - no extra space, but in place modification
     * 
     * Down: repeatedly sets the same elements to MODIFIED. can be improved.
     * @param matrix
     */
    public void setZerosLessEfficient(int[][] matrix) {
        int MODIFIED = -1000000; // this value will change based on max value matrix can contain
        int m = matrix.length, n = matrix[0].length;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(matrix[i][j] == 0) {
                    for(int k=0;k<n;k++) {
                        matrix[i][k] = MODIFIED;
                    }
                    for(int k=0;k<m;k++) {
                        matrix[k][j] = MODIFIED;
                    }
                }
            }
        }
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(matrix[i][j] == MODIFIED) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * Time: O(mn)
     * Space: O(1)
     * 
     * Avoids repeated setting of elements, by using the first element in each row/column as a flag to indicate if that row/col needs to be set to zero.
     * Only problem is with the first row, col for which the flag indicator is the same - matrix[0][0]. Only for this case, we need to maintain a
     * separate flag either for first row or col.
     * @param matrix
     */
    public void setZerosEfficent(int[][] matrix) {
        boolean firstColZero = false;
        int m = matrix.length, n = matrix[0].length;
        for(int i=0;i<m;i++) {
            // Check the first column separately
            if(matrix[i][0] == 0){
                firstColZero = true;
            }
            // Check the remaining columns and set the respective flags matrix[i][0] or matrix[0][j]
            for(int j=1;j<n;j++) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // use flags and set the remaining rows or columns to zero
        for(int i=1;i<m;i++) {
            for(int j=1;j<n;j++) {
                if(matrix[i][0] == 0 ||matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // Check if the first row needs to be set to zeros
        if(matrix[0][0] == 0) {
            for(int k=0;k<n;k++) {
                matrix[0][k] = 0;
            }
        }
        // Check if the first col needs to be set to zeros
        if(firstColZero) {
            for(int k=0;k<m;k++) {
                matrix[k][0] = 0;
            }
        }
    }
}