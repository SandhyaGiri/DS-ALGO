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
public class MaxLevelSumBT {
    /**
     * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
        Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
        Example 1:
        Input: [1,7,0,7,-8,null,null]
        Output: 2
        Explanation: 
        Level 1 sum = 1.
        Level 2 sum = 7 + 0 = 7.
        Level 3 sum = 7 + -8 = -1.
        So we return the level with the maximum sum which is level 2.

        Idea: use BFS, find the sum of all nodes in a level, update maxLevel, maxLevelSum
     * @param root
     * @return
     */
    public int maxLevelSum(TreeNode root) {
        int maxLevelSum = Integer.MIN_VALUE;
        int levelOfMaxSum = 0;
        int level = 0;
        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        nodes.add(root);
        while(!nodes.isEmpty()){
            int levelSize = nodes.size();
            int levelSum =0;
            level++;
            for(int i=0;i<levelSize;i++) {
                TreeNode node = nodes.poll();
                levelSum += node.val;
                if(node.left != null){
                    nodes.add(node.left);   
                }
                if(node.right != null) {
                    nodes.add(node.right);
                }
            }
            if(levelSum > maxLevelSum) {
                maxLevelSum = levelSum;
                levelOfMaxSum = level;
            }
        }
        return levelOfMaxSum;
    }
}