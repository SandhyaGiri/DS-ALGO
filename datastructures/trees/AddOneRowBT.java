package datastructures.trees;

import java.util.*;

// https://leetcode.com/problems/add-one-row-to-tree/
public class AddOneRowBT {
    /**
     * Given the root of a binary tree and two integers val and depth, add a row of nodes with value val at the given depth depth.

        Note that the root node is at depth 1.

        The adding rule is:

        Given the integer depth, for each not null tree node cur at the depth depth - 1, create two tree nodes with value val as cur's left subtree root and right subtree root.
        cur's original left subtree should be the left subtree of the new left subtree root.
        cur's original right subtree should be the right subtree of the new right subtree root.
        If depth == 1 that means there is no depth depth - 1 at all, then create a tree node with value val as the new root of the whole original tree, and the original tree is the new root's left subtree.

     * @param root
     * @param v
     * @param d
     * @return
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        // case1: root changes
        if(d == 1){
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        // case2: root doesn't change
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        int level =1;
        while(!nodes.isEmpty()){
            int levelSize = nodes.size();
            for(int i=0;i<levelSize;i++){
                TreeNode curr = nodes.poll();
                if(level == d-1){
                    // introduce new nodes as children
                    TreeNode left = curr.left;
                    TreeNode right = curr.right;
                    curr.left = new TreeNode(v);
                    curr.right = new TreeNode(v);
                    curr.left.left = left;
                    curr.right.right = right;
                } else {
                    if(curr.left != null){
                        nodes.add(curr.left);
                    }
                    if(curr.right != null){
                        nodes.add(curr.right);
                    }
                }
            }
            if(level == d-1){
                break;
            }
            level++;
        }
        return root;
    }
}
