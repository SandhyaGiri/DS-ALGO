package algorithms.dp;

import java.util.*;

// https://leetcode.com/problems/perfect-squares/
public class PerfectSquares {
    // Similar to CoinChange - min no of coins that sum up to an amount, where coins are the perfect square values given.
    /**
     * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

        Example 1:

        Input: n = 12
        Output: 3 
        Explanation: 12 = 4 + 4 + 4.
        Example 2:

        Input: n = 13
        Output: 2
        Explanation: 13 = 4 + 9.
     * @param n
     * @return
     */
    public int numSquares(int n) {
        List<Integer> squares = new LinkedList<>();
        int x=1;
        while(x*x <= n){
            squares.add(x*x);
            x+=1;
        }
        int m=squares.size();
        int[][] numSqrs = new int[m+1][n+1];
        for(int i=1;i<=m;i++){
            numSqrs[i][0] = 0; // for sum=0 we don't need any sqrs
        }
        for(int j=1;j<=n;j++){
            numSqrs[0][j] = Integer.MAX_VALUE; // with 0 sqrs we can't make sum>0
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                int currSqr = squares.get(i-1);
                //System.out.println(currSqr);
                if(currSqr <= j && numSqrs[i][j-currSqr] != Integer.MAX_VALUE){
                    numSqrs[i][j] = Math.min(numSqrs[i-1][j], 1+numSqrs[i][j-currSqr]);
                } else{
                    numSqrs[i][j] = numSqrs[i-1][j];
                }
            }
        }
        return numSqrs[m][n] != Integer.MAX_VALUE ? numSqrs[m][n] : -1;
    }
}