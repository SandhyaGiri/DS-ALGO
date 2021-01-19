package datastructures.trees;

import java.util.*;

// https://leetcode.com/problems/even-odd-tree/
public class EvenOddTree {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    /**
     * A binary tree is named Even-Odd if it meets the following conditions:

        The root of the binary tree is at level index 0, its children are at level index 1, their children are at level index 2, etc.
        For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
        For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).
        Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.

     * @param root
     * @return
     */
    public boolean isEvenOddTree(TreeNode root) {
        boolean isValid = true;
        if(root != null){
            Queue<TreeNode> nodes = new LinkedList<>();
            nodes.add(root);
            boolean isEvenLevel = true;
            while(!nodes.isEmpty()){
                int levelSize = nodes.size();
                Integer prev = null;
                for(int i=0;i<levelSize;i++){
                    TreeNode currNode = nodes.poll();
                    int curr = currNode.val;
                    boolean isCurrOdd = curr % 2 != 0;
                    if((isEvenLevel && !isCurrOdd) || (!isEvenLevel && isCurrOdd)){
                        return false;
                    }
                    if(prev != null && ((isEvenLevel && curr <= prev) || (!isEvenLevel && curr >= prev))){
                        return false;
                    }
                    prev = curr;
                    if(currNode.left != null){
                        nodes.add(currNode.left);
                    }
                    if(currNode.right != null){
                        nodes.add(currNode.right);
                    }
                }
                isEvenLevel = !isEvenLevel;
            }
        }
        return isValid;
    }
}
