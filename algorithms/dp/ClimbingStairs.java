package algorithms.dp;

public class ClimbingStairs {
    // https://leetcode.com/problems/climbing-stairs/
    /**
     * You are climbing a stair case. It takes n steps to reach to the top.

        Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

        Note: Given n will be a positive integer.

        Example 1:

        Input: 2
        Output: 2
        Explanation: There are two ways to climb to the top.
        1. 1 step + 1 step
        2. 2 steps
        Example 2:

        Input: 3
        Output: 3
        Explanation: There are three ways to climb to the top.
        1. 1 step + 1 step + 1 step
        2. 1 step + 2 steps
        3. 2 steps + 1 step

        Idea: like in robot paths, sum num of ways of reaching steps from where you can directly jump to
        current location. Here possible previous locations are previous step (1 step from there) or the one before
        (2 steps from there).
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if(n>2){
            int[] numWays = new int[n];
            numWays[0] = 1; // one step on to first stair
            numWays[1] = 2; // one step from previous or two steps to second stair
            for(int i=2;i<n;i++){
                // sum of #ways of reaching either previous stair or the stair before previous one
                // from those stairs, we take 1 step or 2 step respectively.
                numWays[i] = numWays[i-1] + numWays[i-2];
            }
            return numWays[n-1];
        }
        return n==2 ? 2 : 1;
    }

    // https://leetcode.com/problems/min-cost-climbing-stairs/
    /**
     * 
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] minCost = new int[n];
        if(n <= 2){
            return 0;
        }
        // already pay the costs at starting locations, so you are eligible for clibing 1-2 stairs from it.
        minCost[0]=cost[0];
        minCost[1]=cost[1];
        for(int i=2;i<n;i++){
            minCost[i] = cost[i] + Math.min(minCost[i-1], minCost[i-2]);
        }
        // prev step is also already paid, so we can reach top from prev too.
        return Math.min(minCost[n-1], minCost[n-2]);
    }
}