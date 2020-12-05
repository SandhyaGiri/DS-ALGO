package algorithms.dp;

import java.util.*;

// https://leetcode.com/problems/triangle/
public class TrianglePathSum {

    int minPathSum = Integer.MAX_VALUE;
    /**
     * Idea: Simple search - pre-order traversal from root, at leaves compare the pathSum.
     * 
     * Time: O(2^N) -> exponential
     * Space: O(triangle.size) -> recurstion stack.
     * @param triangle
     * @param depth
     * @param index
     * @param pathSum
     */
    void dfsUtil(List<List<Integer>> triangle, int depth, int index, int pathSum){
        if(depth >= triangle.size() || index >= triangle.get(depth).size()){
            minPathSum = Math.min(minPathSum, pathSum);
            return;
        }
        int currValue = triangle.get(depth).get(index);
        // go to neighbors
        dfsUtil(triangle, depth+1, index, pathSum+currValue);
        dfsUtil(triangle, depth+1, index+1, pathSum+currValue);
    }
    public int minimumTotalComplex(List<List<Integer>> triangle) {
        if(triangle.size() > 0){
            dfsUtil(triangle, 0, 0, 0);
        }
        return minPathSum == Integer.MAX_VALUE ? 0 : minPathSum;
    }

    /**
     * Idea: Each number has 2 neighbors in the next row, x at i has two neighbors at i and i+1 in next row.
     * 
     * DP: Store min pathSum. Start from the last row same as given triangle's last row. For each subsequent row
     * add the number to min(pathSum at i, pathSum at i+1) => extending the optimal path. Finally the top of the
     * pathSums triangle has the overall min path sum.
     * 
     * Time: O(N)
     * Space: O(N)
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle){
        List<List<Integer>> pathSum = new ArrayList<>();
        int numRows = triangle.size();
        pathSum.add(triangle.get(numRows-1)); // last row
        int row = numRows-2;
        while(row >= 0){
            List<Integer> numbers = triangle.get(row);
            List<Integer> prevPathSumRow = pathSum.get(pathSum.size()-1);
            List<Integer> currPathSumRow = new ArrayList<>();
            for(int i=0;i<numbers.size();i++){
                int minSoFar = Math.min(prevPathSumRow.get(i), prevPathSumRow.get(i+1));
                currPathSumRow.add(numbers.get(i) + minSoFar);
            }
            row-=1;
            pathSum.add(currPathSumRow);
        }
        return pathSum.get(pathSum.size()-1).get(0);
    }
}
