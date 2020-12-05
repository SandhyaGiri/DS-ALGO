package algorithms.dp;

//https://leetcode.com/problems/coin-change-2/
public class CoinChange {
    int dfsUtil(int[] coins, int amount, int index, int currAmount){
        if(currAmount == amount){
            return 1;
        }
        if(currAmount > amount){
            return 0;
        }
        if(index > coins.length-1){
            return currAmount== amount ? 1 : 0;
        }
        int numPaths = 0;
        for(int i=index;i<coins.length;i++){
            numPaths += dfsUtil(coins, amount, i, currAmount+coins[i]);
        }
        return numPaths;
    }
    /**
     * You are given coins of different denominations and a total amount of money.
     * Write a function to compute the number of combinations that make up that amount.
     * You may assume that you have infinite number of each kind of coin.

        Example 1:

        Input: amount = 5, coins = [1, 2, 5]
        Output: 4
        Explanation: there are four ways to make up the amount:
        5=5
        5=2+2+1
        5=2+1+1+1
        5=1+1+1+1+1
        Example 2:

        Input: amount = 3, coins = [2]
        Output: 0
        Explanation: the amount of 3 cannot be made up just with coins of 2.
        Example 3:

        Input: amount = 10, coins = [10] 
        Output: 1
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int m = coins.length;
        int n = amount;
        int[][] dp=new int[m+1][n+1];
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(coins[i-1] <= j){
                    int additionalPaths = 0;   
                    // find num of paths including the new coin (and with other coins or itself)
                    additionalPaths = dp[i][j-coins[i-1]];
                    // use both paths without this coin, and the paths with the new coin as overall paths
                    dp[i][j] = dp[i-1][j] + additionalPaths;
                } else {
                    dp[i][j] = dp[i-1][j]; // don't include the coin
                }
            }
        }
        return amount > 0 ? dp[m][n] : 1;
    }
    /**
     * https://leetcode.com/problems/coin-change
     * 
     * You are given coins of different denominations and a total amount of money amount.
     * Write a function to compute the fewest number of coins that you need to make up that amount.
     * If that amount of money cannot be made up by any combination of the coins, return -1.

        Example 1:

        Input: coins = [1, 2, 5], amount = 11
        Output: 3 
        Explanation: 11 = 5 + 5 + 1
        Example 2:

        Input: coins = [2], amount = 3
        Output: -1
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int m = coins.length;
        int n = amount;
        int[][] dp=new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            dp[i][0] = 0; // no coins needed for amount 0
        }
        for(int j=1;j<=n;j++){
            dp[0][j] = Integer.MAX_VALUE; // if no coins available, we cannot make >0 amount
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(coins[i-1] <= j && dp[i][j-coins[i-1]] != Integer.MAX_VALUE){
                    // include coin only if possible to make up the remaining amount after including the coin
                    // MAX_VALUE indicates that the amount is not satisfied.
                    dp[i][j] = Math.min(dp[i-1][j], 1+dp[i][j-coins[i-1]]);
                } else {
                    dp[i][j] = dp[i-1][j]; // don't include the coin
                }
            }
        }
        return dp[m][n] == Integer.MAX_VALUE ? -1 : dp[m][n];
    }
}