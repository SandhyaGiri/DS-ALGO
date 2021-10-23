package datastructures.stacksandqueues;

import java.util.*;

// https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/
public class KWeakestRowsMatrix {
    
    class RowSoldierPair implements Comparable<RowSoldierPair>{
        int rowId;
        int numSoldiers;
        RowSoldierPair(int r, int numSoldiers){
            this.rowId = r;
            this.numSoldiers = numSoldiers;
        }
        public int compareTo(RowSoldierPair other){
            return this.numSoldiers == other.numSoldiers ? Integer.compare(other.rowId, this.rowId) : Integer.compare(other.numSoldiers, this.numSoldiers);
        }
    }
    
    int bSearchZeroStart(int[] num){
        int l = 0, r= num.length-1;
        while(l<=r){
            int mid = l + (r-l)/2;
            if(num[mid] == 0 && (mid-1 < 0 || num[mid-1] == 1)){
                return mid;
            } else if(num[mid] == 1){ // go right
                l = mid+1;
            } else {
                r = mid-1;
            }
        }
        return l;
    }
    
    /**
     * Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians), return the indexes of the k weakest rows in the matrix ordered from the weakest to the strongest.

    A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j, or they have the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row, that is, always ones may appear first and then zeros.

    

    Example 1:

    Input: mat = 
    [[1,1,0,0,0],
    [1,1,1,1,0],
    [1,0,0,0,0],
    [1,1,0,0,0],
    [1,1,1,1,1]], 
    k = 3
    Output: [2,0,3]
    Explanation: 
    The number of soldiers for each row is: 
    row 0 -> 2 
    row 1 -> 4 
    row 2 -> 1 
    row 3 -> 2 
    row 4 -> 5 
    Rows ordered from the weakest to the strongest are [2,0,3,1,4]
    Example 2:

    Input: mat = 
    [[1,0,0,0],
    [1,1,1,1],
    [1,0,0,0],
    [1,0,0,0]], 
    k = 2
    Output: [0,2]
    Explanation: 
    The number of soldiers for each row is: 
    row 0 -> 1 
    row 1 -> 4 
    row 2 -> 1 
    row 3 -> 1 
    Rows ordered from the weakest to the strongest are [0,2,3,1]

    Idea: In each row, find num soldiers using bsearch. Then simply find k weakest rows using a max heap.
     * @param mat
     * @param k
     * @return
     */
    public int[] kWeakestRows(int[][] mat, int k) {
        PriorityQueue<RowSoldierPair> maxHeap = new PriorityQueue<>();
        int numAdded = 0;
        for(int i=0;i<mat.length;i++){
            int numSoldiers = bSearchZeroStart(mat[i]);
            maxHeap.add(new RowSoldierPair(i, numSoldiers));
            numAdded++;
            if(numAdded > k){
                maxHeap.poll();
            }
        }
        int[] weakestRows = new int[k];
        int j=k-1; // reverse order as we had max heap
        while(!maxHeap.isEmpty()){
            weakestRows[j--] = maxHeap.poll().rowId;
        }
        return weakestRows;
    }
}
