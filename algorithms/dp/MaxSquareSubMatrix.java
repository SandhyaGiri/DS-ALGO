package algorithms.dp;

/**
 * Given a Binary matrix with 0s and 1s, find the maximum size submatrix with all 1s.
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