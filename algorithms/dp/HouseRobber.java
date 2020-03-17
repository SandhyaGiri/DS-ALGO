package algorithms.dp;

public class HouseRobber{
    /**
     * https://leetcode.com/problems/house-robber
     * 
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

    Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

    Example 1:

    Input: [1,2,3,1]
    Output: 4
    Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
                Total amount you can rob = 1 + 3 = 4.
    Example 2:

    Input: [2,7,9,3,1]
    Output: 12
    Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
                Total amount you can rob = 2 + 9 + 1 = 12.

    Idea: Similar to LIS. For each house i, excluding previous adjacent house, find a j<i-1 whose loot value is maximum. If such a j exists,
    add current value to this, to extend the thievery and store in loot[i]. If no such j exists, then this house is the beginning of the loot chain,
    so assign loot[i]=nums[i]. Also keep track of max loot to be returned.

    Time: O(n*n)
    */
    public int rob(int[] nums) {
        int[] loot = new int[nums.length];
        int maxLoot = 0;
        for(int i=0;i<nums.length;i++){
            int j=i-2;
            int maxLootSoFar = Integer.MIN_VALUE;
            int candidateHouse = -1;
            while(j>=0){
               if(loot[j] > maxLootSoFar){
                   maxLootSoFar = loot[j];
                   candidateHouse = j;
               }
                j--;
            }
            if(candidateHouse !=-1){
                loot[i]= loot[candidateHouse]+nums[i];
            } else {
                loot[i]= nums[i];
            }
            if(loot[i]>maxLoot){
                maxLoot = loot[i];
            }
        }
        return maxLoot;
    }

    /**
     * https://leetcode.com/problems/house-robber-ii/
     * 
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

        Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

        Example 1:

        Input: [2,3,2]
        Output: 3
        Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
                    because they are adjacent houses.
        Example 2:

        Input: [1,2,3,1]
        Output: 4
        Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
                    Total amount you can rob = 1 + 3 = 4.

        Idea: Since House[1] and House[n] are adjacent, they cannot be robbed together. 
        Therefore, the problem becomes to rob either House[1]-House[n-1] or House[2]-House[n], 
        depending on which choice offers more money. Now the problem has degenerated to the House Robber, which is already been solved.
     * @param nums
     * @param l
     * @param r
     * @return
     */
    int lootHouses(int[] nums, int l, int r){
        int[] loot = new int[nums.length];
        int maxLoot = 0;
        for(int i=l;i<=r;i++){
            int j=i-2;
            int maxLootSoFar = Integer.MIN_VALUE;
            int candidateHouse = -1;
            while(j>=l){
                if(loot[j] > maxLootSoFar){
                    maxLootSoFar = loot[j];
                    candidateHouse = j;
                }
                j--;
            }
            if(candidateHouse != -1){
                loot[i]= loot[candidateHouse]+nums[i];
            } else {
                loot[i]=nums[i];
            }
            
            if(loot[i]>maxLoot){
                maxLoot = loot[i];
            }
        }
        return maxLoot;
    }
    public int rob2(int[] nums) {
        return nums.length >=2 ? Math.max(lootHouses(nums, 0, nums.length-2), lootHouses(nums, 1, nums.length-1)) : (nums.length==1 ? nums[0] : 0);
    }
}