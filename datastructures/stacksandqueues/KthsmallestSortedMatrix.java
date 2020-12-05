package datastructures.stacksandqueues;

import java.util.*;

// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
public class KthsmallestSortedMatrix {
    class Number implements Comparable<Number>{
        int val;
        int rowIndex;
        int colIndex;
        Number(int x, int i, int j){
            this.val=x;
            this.rowIndex=i;
            this.colIndex=j;
        }
        public int compareTo(Number other){
            return this.val - other.val;
        }
    }
    /**
     * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

        Note that it is the kth smallest element in the sorted order, not the kth distinct element.

        Example:

        matrix = [
        [ 1,  5,  9],
        [10, 11, 13],
        [12, 13, 15]
        ],
        k = 8,

        return 13.

        [[1,4,7,11,15],
        [2,5,8,12,19],
        [3,6,9,16,22],
        [10,13,14,17,24],
        [18,21,23,26,30]]
        k=5, return 5


        Idea: Imagine that you can only see the columns from the top i.e the first row. (like some products in supermarket, where a new one 
        appears once you pull out an item). Have these in a minHeap, and pick the smallest element, now add its successor i.e element
        lying in the same column but next row to heap. Repeat this k times, to find the kth smallest element.

        Time: O(m*n) // worst case, k=m*n
        Space: O(n) // column size
        
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k){
        int m = matrix.length;
        int n = matrix[0].length;
        PriorityQueue<Number> columnValues = new PriorityQueue<>();
        // enqueue all column values from the first row 
        for(int j=0;j<n;j++){
            columnValues.add(new Number(matrix[0][j], 0, j));
        }
        while(k>0){
            // pick the smallest element and add next element in its column as the successor
            Number pickedNumber = columnValues.poll();
            k-=1;
            if(k == 0){
                return pickedNumber.val;
            }
            int row = pickedNumber.rowIndex;
            int col = pickedNumber.colIndex;
            if(row+1 < m){
                columnValues.add(new Number(matrix[row+1][col], row+1, col));   
            } // ow. we have exhausted this column
        }
        return -1;
    }
}
