package datastructures.arrays;

import java.util.*;

// https://leetcode.com/problems/sort-the-matrix-diagonally/
public class SortMatrixDiagonally {
    void sortDiagonal(int[][] mat, int i, int j, int m, int n){
        int x=i, y=j;
        // add elements to list
        List<Integer> diagonalElements = new ArrayList<>();
        while(x<m && y<n){
            diagonalElements.add(mat[x][y]);
            x++;
            y++;
        }
        Collections.sort(diagonalElements); // O(nlogn)
        x=i; y=j;
        int k=0;
        while(x<m && y<n){
            mat[x][y] = diagonalElements.get(k);
            x++;
            y++;
            k++;
        }
    }
    /**
     * A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end. For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].

        Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.

        Example 1:

        Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
        Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]

        Idea: For each diagonal, store elements in a collection and sort it. Then do a second pass through the diagonal and write the
        sorted elements in the order.

        We can also use "counting sort" as elements of the matrix will be between 1..100, thus reducing O(nlogn) to O(n) linear sort.
     * @param mat
     * @return
     */
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        if(m > 0){
            int n = mat[0].length;
            for(int i=0;i<m;i++){ // diagonal starting at i,0
                sortDiagonal(mat, i, 0, m, n);
            }
            for(int j=1;j<n;j++){
                sortDiagonal(mat, 0, j, m, n);
            }
        }
        return mat;
    }
}
