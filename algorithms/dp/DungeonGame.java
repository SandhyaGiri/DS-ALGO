package algorithms.dp;

/**
 * https://leetcode.com/problems/dungeon-game/
 * 
 */
class Solution {
    int healthNeeded(int cost){
        return cost>0 ? 1 : 1-cost;
    }
    public int calculateMinimumHP(int[][] dungeon) {
        int m= dungeon.length;
        int n = dungeon[0].length;
        int minPathCost[][] = new int[m][n];
        int minHealth[][] = new int[m][n];
        
        minPathCost[0][0]= dungeon[0][0];
        minHealth[0][0]= healthNeeded(minPathCost[0][0]);
        
        for(int i=1;i<m;i++){
            minPathCost[i][0]= minPathCost[i-1][0]+dungeon[i][0];
            minHealth[i][0] = Math.max(minHealth[i-1][0], healthNeeded(minPathCost[i][0]));
        }
        for(int j=1;j<n;j++){
            minPathCost[0][j]= minPathCost[0][j-1]+dungeon[0][j];
            minHealth[0][j]= Math.max(minHealth[0][j-1], healthNeeded(minPathCost[0][j]));
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                // new healths needed
                int x= healthNeeded(minPathCost[i-1][j]+dungeon[i][j]); // down
                int y= healthNeeded(minPathCost[i][j-1]+dungeon[i][j]); // right
                // add to existing health needed, and choose the min health one!
                x = Math.max(x, minHealth[i-1][j]);
                y = Math.max(y, minHealth[i][j-1]);
                if(x<y) {
                    minPathCost[i][j]= minPathCost[i-1][j]+dungeon[i][j];
                    minHealth[i][j]=x;
                }else {
                    minPathCost[i][j]= minPathCost[i][j-1]+dungeon[i][j];
                    minHealth[i][j]=y;
                }
            }
        }
        return minHealth[m-1][n-1];
    }
}