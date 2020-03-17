package datastructures.trees;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
/**
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 * 
 */
public class MinDiffBST {
    public int minDiffInBST(TreeNode root) {
        int minDiff = Integer.MAX_VALUE;
        TreeNode curr = root;
        // iterative inorder traversal (morris)
        // in order traversal, as we want to visit the nodes in sorted order. In sorted array, min difference occurs between adjacent nodes.
        int previous = Integer.MAX_VALUE;
        while(curr != null) {
            if(curr.left == null) {
                int diff = Math.abs(previous-curr.val);
                if(diff < minDiff) {
                    minDiff = diff;
                }
                previous = curr.val;
                curr = curr.right;
            } else {
                TreeNode prev = curr.left;
                // go to inorder predecessor for curr (left child's right most node)
                while(prev != null && prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }
                if(prev.right == null) { // not yet threaded, connect it to curr and go left.
                    prev.right = curr;
                    curr = curr.left;
                } else { // already threaded, so we are visiting in inorder now.
                    prev.right = null;
                    int diff = Math.abs(prev.val - curr.val);
                    if(diff < minDiff) {
                        minDiff = diff;
                    }
                    previous = curr.val;
                    curr = curr.right; // left subtree is exhausted, move right
                }
            }
        }
        return minDiff;
    }
}