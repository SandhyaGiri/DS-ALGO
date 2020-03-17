package datastructures.trees;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class SubtreeCheck {
    boolean isExactMatch(TreeNode root1, TreeNode root2){
        if((root1 == null && root2 != null) || (root1 != null && root2 == null)){
            return false;
        }
        if(root1 == null && root2 == null){// empty trees are exact matches
            return true;
        }
        return root1.val == root2.val && isExactMatch(root1.left, root2.left) &&  isExactMatch(root1.right, root2.right);
    }
    /**
     * https://leetcode.com/problems/subtree-of-another-tree/
     * 
     * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4 
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null && t==null){ // null trees are subtrees of each other
            return true;
        }
        if(s == null || t==null){
            return false;
        }
        if(s.val == t.val){
            // if though root value matches but tree matching fails, we need to check in left/right subtrees
            return isExactMatch(s,t) || isSubtree(s.left, t) || isSubtree(s.right, t); 
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
}