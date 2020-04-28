package datastructures.arrays;

import java.util.*;

interface BinaryMatrix {
    public int get(int x, int y);
    public List<Integer> dimensions();
};

public class BinaryMatrixLeftmostCol {
    /**
     * Sol1 : Binary search for finding starting index of 1 in each row.
     * 
     * But complexity is #rows * O(log #cols)
     * @param binaryMatrix
     * @param row
     * @return
     */
    int bsearchIndex(BinaryMatrix binaryMatrix, int row){
        // search all columns in this row O(log #cols)
        // return the index of first 1
        int numCols = binaryMatrix.dimensions().get(1);
        int l = 0, r = numCols-1;
        int colIndex = -1;
        //System.out.println("row: " + row);
        while(l<=r){
            int mid = (l+r)/2;
            //System.out.println("mid: " + mid);
            int midValue = binaryMatrix.get(row, mid);
            //System.out.println("mid value: " + midValue);
            if(midValue == 1 && (mid-1 < 0 || binaryMatrix.get(row, mid-1) == 0)) {
                colIndex = mid;
                //System.out.println("Found at: " + colIndex);
                break;
            }
            else if(midValue == 0){
                l = mid+1;
            } else {
                r = mid-1;
            }
        }
        return colIndex;
    }
    /**
     * A binary matrix means that all elements are 0 or 1. For each individual row of the matrix, this row is sorted in non-decreasing order.

    Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it. If such index doesn't exist, return -1.

    You can't access the Binary Matrix directly.  You may only access the matrix using a BinaryMatrix interface:

    BinaryMatrix.get(x, y) returns the element of the matrix at index (x, y) (0-indexed).
    BinaryMatrix.dimensions() returns a list of 2 elements [n, m], which means the matrix is n * m.
    Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.

    For custom testing purposes you're given the binary matrix mat as input in the following four examples. You will not have access the binary matrix directly.
     * @param binaryMatrix
     * @return
     */
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dim = binaryMatrix.dimensions();
        int m = dim.get(0);
        int n = dim.get(1);
        int minColIndex = Integer.MAX_VALUE;
        //for(int row=0;row<m;row++){
        //    if(binaryMatrix.get(row, n-1) == 1){
        //        // candidate
        //        int candColIndex = bsearchIndex(binaryMatrix, row);
        //        //System.out.println("Cand index: " + candColIndex);
        //        minColIndex = Math.min(minColIndex, candColIndex);
        //    }
        //}

        // Sol2: near linear time complexity, start from top right corner.
        int row=0, col= n-1;
        while(row < m && col >=0){
            int val = binaryMatrix.get(row, col);
            if(val == 1){
                minColIndex = Math.min(minColIndex, col);
                col--;
            } else {
                row++;
            }
        }
        return minColIndex == Integer.MAX_VALUE ? -1 : minColIndex;
    }
}