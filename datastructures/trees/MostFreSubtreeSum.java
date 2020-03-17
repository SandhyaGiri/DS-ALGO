package datastructures.trees;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class MostFreSubtreeSum {
    int dfs(TreeNode root, Map<Integer,Integer> subtreesums){
        if(root == null){
            return 0;
        }
        int lsum = dfs(root.left, subtreesums);
        int rsum = dfs(root.right, subtreesums);
        int sum = root.val+lsum+rsum;
        subtreesums.put(sum, subtreesums.getOrDefault(sum,0)+1);
        return sum;
    }
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer,Integer> sums = new HashMap<>();
        dfs(root, sums);
        int maxCount = Integer.MIN_VALUE;
        List<Integer> maxSums = new ArrayList<>();
        for(int sum:sums.keySet()){
            if(sums.get(sum)>maxCount){
                maxCount = sums.get(sum);
                maxSums.clear();
                maxSums.add(sum);
            } else if(sums.get(sum)==maxCount){
                maxSums.add(sum);
            }
        }
        return maxSums.stream().mapToInt(i -> i).toArray();
    }
}