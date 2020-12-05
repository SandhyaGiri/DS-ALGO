package datastructures.trees;

import java.util.*;

public class PathSumBT {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
    }
    void dfsUtil(TreeNode root, int sum, int currSum, List<Integer> path, List<List<Integer>> result){
        if(root == null){
            return;
        }
        path.add(root.val);
        // check at leaf nodes
        if(root.left == null && root.right == null && currSum + root.val == sum){
            result.add(new ArrayList<>(path)); 
            path.remove(path.size()-1);
            return;
        }
        dfsUtil(root.left, sum, currSum + root.val, path, result);
        dfsUtil(root.right, sum, currSum + root.val, path, result);
        // backtrack
        path.remove(path.size()-1);
    }
    // https://leetcode.com/problems/path-sum-ii/
    /**
     * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        dfsUtil(root, sum, 0, new ArrayList<>(), result);
        return result;
    }
}
