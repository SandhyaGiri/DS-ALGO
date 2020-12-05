package datastructures.trees;

public class LongestUnivaluePathBT {
    /**
     * https://leetcode.com/problems/longest-univalue-path/
     * 
     * Given a binary tree, find the length of the longest path where each node in the path has the same value.
     * This path may or may not pass through the root.

        The length of path between two nodes is represented by the number of edges between them.
     */
    int maxPathLen = Integer.MIN_VALUE;
    int longestUnivaluePathUtil(TreeNode root){
        if(root == null || (root.left == null && root.right == null)) {
            // leaf node
            return 0;
        }
        int lenLeft = longestUnivaluePathUtil(root.left);
        int lenRight = longestUnivaluePathUtil(root.right);
        int maxLenLeft = 0, maxLenRight = 0;
        if(root.left != null && root.val == root.left.val){
            maxLenLeft += lenLeft +1;
        }
        if(root.right != null && root.val == root.right.val) {
            maxLenRight += lenRight +1;
        }
        maxPathLen = Math.max(maxPathLen, maxLenLeft+maxLenRight); // path going through this node (root)
        return Math.max(maxLenLeft, maxLenRight); // further you can only extend one of the paths left or right not both
    }
    public int longestUnivaluePath(TreeNode root) {
        longestUnivaluePathUtil(root);
        return maxPathLen == Integer.MIN_VALUE ? 0 : maxPathLen;
    }
}