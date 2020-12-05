package datastructures.trees;

public class MaxPathSumBT {
    /**
     * https://leetcode.com/problems/binary-tree-maximum-path-sum/
     * 
     * Given a non-empty binary tree, find the maximum path sum.

    For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along
    the parent-child connections. The path must contain at least one node and does not need to go through the root.

    Example 1:

    Input: [1,2,3]

        1
        / \
        2   3

    Output: 6
    Example 2:

    Input: [-10,9,20,null,null,15,7]

    -10
    / \
    9  20
        /  \
    15   7

    Output: 42
     */
    int maxSum = Integer.MIN_VALUE;
    int maxPathSumUtil(TreeNode root){
        if(root == null){
            return 0;
        }
        int lSum = maxPathSumUtil(root.left);
        int rSum = maxPathSumUtil(root.right);
        // 4 path possibilities here - extend left path, extend right path, path through root, or root alone.
        maxSum = Math.max(maxSum, root.val + lSum + rSum);
        maxSum = Math.max(maxSum, root.val + lSum);
        maxSum = Math.max(maxSum, root.val + rSum);
        maxSum = Math.max(maxSum, root.val);
        // but we cannot return path through root, as that would lead to wrong paths.
        return Math.max(Math.max(lSum + root.val, rSum + root.val), root.val);
    }
    public int maxPathSum(TreeNode root) {
        maxPathSumUtil(root);
        return maxSum == Integer.MIN_VALUE ? 0 : maxSum;
    }
}