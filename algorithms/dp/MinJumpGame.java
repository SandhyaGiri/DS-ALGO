package algorithms.dp;

import java.util.*;
/**
 * https://leetcode.com/problems/jump-game-ii/
 * 
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

    Each element in the array represents your maximum jump length at that position.

    Your goal is to reach the last index in the minimum number of jumps.
 */
public class MinJumpGame {
    // could have added visited node, as time limit exceeded!
    int jump_bfs(int[] nums){
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int goalIndex = nums.length-1;
        int level =0;
        boolean goalReached = false;
        // shallowest goal node found using BFS
        while(!q.isEmpty()){
            int levelSize = q.size();
            for(int i=0;i<levelSize;i++){
                int nodeIndex = q.poll();
                int jumps = nums[nodeIndex];
                for(int jump=1;jump<=jumps;jump++){
                    if(nodeIndex+jump == goalIndex){
                        goalReached = true;
                        return level+1;
                    }
                    if(nodeIndex+jump < nums.length){
                        q.add(nodeIndex+jump);   
                    }
                }
            }
            level+=1;
        }
        return 0;
    }
    // dp based solution - o(n*n) memory - Memory limit execeeded
    // we don't need minJumps from any other index like 1 or 2 to goal node. this sol stores all such paths.
    // we only need minJumps from index 0
    int jump_dp(int[] nums){
        // memory limit exceeded
        int n = nums.length;
        if(n > 0){
            int[][] minJumps = new int[n][n];
            for(int i=0;i<n;i++){
                minJumps[i][i] = 0;
            }
            for(int j=1;j<n;j++){
                for(int i=j-1;i>=0;i--){
                    // check for direct jump from i to j
                    if(i+nums[i] >= j){
                        minJumps[i][j] = 1;
                    } else {
                        // check for every intermediate path
                        int minjump = Integer.MAX_VALUE;
                        for(int k=i+1;k<j;k++) {
                            //System.out.println("k: " + k + ",i:" + i + ",j:" + j +", jumps: " + (minJumps[i][k] + minJumps[k][j]));
                            if(minJumps[i][k] >= 0 && minJumps[k][j] >= 0){
                                    minjump = Math.min(minjump, minJumps[i][k] + minJumps[k][j]); 
                            }
                        }
                        minJumps[i][j] = minjump == Integer.MAX_VALUE ? -1 : minjump;
                    }
                }
            }
            return minJumps[0][n-1] <0 ? 0 : minJumps[0][n-1];
        }
        return 0;
    }
    // dp based solution with O(n) memory
    // similar to Longest Increasing subsequence
    // for any index i, if it is directly accessible from 0, min jump needed is 1
    // if not, for any j < i, if i is directly accessible from j, find the min jumps of all such j's and add 1
    public int jump(int[] nums) {
        int n = nums.length;
        if(n > 0){
            // min jumps from index 0
            int[] minJumps = new int[n];
            for(int i=1;i<n;i++){
                if(nums[0] >= i){
                    minJumps[i] = 1;
                } else {
                    int jumpNeeded = Integer.MAX_VALUE;
                    for(int j=i-1;j>0;j--){
                        if(j+nums[j] >= i && minJumps[j] < jumpNeeded){
                            jumpNeeded = minJumps[j];
                        }
                    }
                    minJumps[i] = jumpNeeded + 1;
                }
            }
            return minJumps[n-1];
        }
        return 0;
    }
}