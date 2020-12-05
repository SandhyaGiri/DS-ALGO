package datastructures.trees;

import java.util.*;

// https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
public class MaxAncestorChildDiff {
    int maxDiff = Integer.MIN_VALUE;

    void dfsUtil(TreeNode root, TreeSet<Integer> path){ // topdown - maintain min, max in this path
        if(root == null){
            return;
        }
        if(path.isEmpty()){
            path.add(root.val); 
        } else { // calculate ancestor diff, compare to min, max values in path
            maxDiff = Math.max(maxDiff, Math.abs(path.first() - root.val)); // min
            maxDiff = Math.max(maxDiff, Math.abs(path.last() - root.val)); // max
            path.add(root.val); 
        }
        dfsUtil(root.left, path);
        dfsUtil(root.right, path);
        // backtrack, remove last added path node
        path.remove(root.val); // ? duplicates ?? - will work only if BT has no duplicates, ow we are removing 
    }
    /**
     * Given the root of a binary tree, find the maximum value V for which there exist different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.

       A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.

       Idea: do a simple top-down dfs, by maintaining the path of nodes traversed. Max diff occurs only with min value or max value
       from this set of nodes. As a given node can be smaller or larger than its ancestors. If smaller, then diff with max value gives max diff.
       If larger, then diff with smallest ancestor gives max diff. After all children of a node are traversed, remove the root.
     * @param root
     * @return
     */
    public int maxAncestorDiff(TreeNode root) {
        TreeSet<Integer> path = new TreeSet<>();
        dfsUtil(root, path);
        return maxDiff == Integer.MIN_VALUE ? 0 : maxDiff;
    }
}
