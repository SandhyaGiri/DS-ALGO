package datastructures.trees;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
}
public class DiameterBST {
    int maxLengthPath = Integer.MIN_VALUE;
    int height(TreeNode root) {
        if(root == null) { // empty tree
            return 0;
        }
        int lheight = height(root.left);
        int rheight = height(root.right);
        if((lheight + rheight + 1) > maxLengthPath) {
            maxLengthPath = lheight + rheight + 1;
        }
        return 1 + Math.max(lheight,rheight);
    }
    
    // Diameter could be no of nodes along the longest path between any leaf nodes or no of edges. height() returns the nodes along the max path length.
    // Take care of edge cases when tree is empty
    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return maxLengthPath != Integer.MIN_VALUE ? maxLengthPath-1 : 0;
    }
}