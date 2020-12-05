package datastructures.trees;

public class GoodNodesBT {

    // Count Good Nodes in Binary Tree
    /**
     * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.

Return the number of good nodes in the binary tree.
     */
    int numGoodNodes;
    void dfsUtils(TreeNode root, int currPathMax){
        if(root == null){
            return;
        }
        if(root.val >= currPathMax){
            numGoodNodes += 1;
            dfsUtils(root.left, root.val);
            dfsUtils(root.right, root.val);
        } else {
            dfsUtils(root.left, currPathMax);
            dfsUtils(root.right, currPathMax);
        }
    }
    public int goodNodes(TreeNode root) {
        numGoodNodes = 0;
        dfsUtils(root, Integer.MIN_VALUE);
        return numGoodNodes;
    }
}