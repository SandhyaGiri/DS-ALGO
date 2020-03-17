package algorithms.dp;

public class FrogJump{

    int getMinSteps(int[][] grid, int maxStepSize){
        int n = grid.length;
        int m = grid.length;
        int[][] cost = new int[n][m];
        cost[0][0] =0;// frog starts from here
        // for first row -- jumps horizontally
        for(int j=1;j<m;j++){
            if(grid[0][j]== -1){ // obstacle
                cost[0][j]=-1; // unreachable
            } else {
                // jump from closest non obstacle node
                int k = j-1;
                int step=1;
                while(k>0 && step<maxStepSize && cost[0][k] ==-1){
                    k--;
                    step++;
                }
                cost[0][j]=cost[0][k] == -1 ? -1 : cost[0][k]+step;
            }
        }
        // for first col -- jumps vertically
        for(int i=1;i<n;i++){
            if(grid[i][0]== -1){ // obstacle
                cost[i][0]=-1; // unreachable
            } else {
                // jump from closest non obstacle node
                int k = i-1;
                int step=1;
                while(k>0 && step<maxStepSize && cost[k][0] ==-1){
                    k--;
                    step++;
                }
                cost[i][0]=cost[k][0] == -1 ? -1 : cost[k][0]+step;
            }
        }
        // process remaining elements
        
        return 0;
    }
}