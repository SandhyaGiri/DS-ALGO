package algorithms.dp;

// https://leetcode.com/problems/partition-equal-subset-sum/
public class PartitionEqualSubsetSum {
    // subset check is same as knapsack problem or coin change problem.
    // find: whether or not some elements sum up to a target
    boolean isSubsetSumDP(int[] nums, int targetSum){
        int m = nums.length;
        int n = targetSum;
        boolean[][] dp = new boolean[m+1][n+1];
        for(int i=0;i<=m;i++){
            dp[i][0]= true;
        }
        for(int j=1;j<=n;j++){
            dp[0][j] = false;
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(nums[i-1] <= j){
                    dp[i][j]= (dp[i-1][j-nums[i-1]] || dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[m][n];
    }
    /**
     * Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
        Example 1:

        Input: nums = [1,5,11,5]
        Output: true
        Explanation: The array can be partitioned as [1, 5, 5] and [11].
        Example 2:

        Input: nums = [1,2,3,5]
        Output: false
        Explanation: The array cannot be partitioned into equal sum subsets.

        Idea: Check if subset sum is even, then check if there is a subset with sum equals to sum/2.
        If so, then remaining elements sum up to the same sum (as we checked for even sum first).

     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];
        }
        System.out.println(sum);
        if(sum%2 == 0){
            // 2 * subsetsum == overall sum
            return isSubsetSumDP(nums, sum/2);
        }
        return false;
    }
}
