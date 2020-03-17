package datastructures.trees;

class IncreasingBT {
    TreeNode prev = null;

    // traverse the tree in-order and maintain a prev node, link prev to current as right child and also make left child of prev empty
    // take care of last node which will not be considered as prev (empty its left child to avoid loops in the tree)
    public void rearrange(TreeNode root) {
        if(root != null) {
            rearrange(root.left);
            if(prev != null) {
                prev.left = null;
                prev.right = root;
                prev = root;
            } else {
                prev = root;
            }
            rearrange(root.right);
            root.left = null; // last node
        }
    }
    /**
     * Given a binary search tree, rearrange the tree in in-order so that the leftmost node
     * in the tree is now the root of the tree, and every node has no left child and only 1 right child.
     * 
     * https://leetcode.com/problems/increasing-order-search-tree/
     * 
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        TreeNode head = root;
        prev = null;
        while(head.left != null) {
            head = head.left;
        }
        rearrange(root);
        return head;
    }
}