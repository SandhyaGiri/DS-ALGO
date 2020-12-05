package datastructures.trees;

// https://leetcode.com/problems/binary-tree-tilt/
public class BinaryTreeTilt {
    class Context {
        int totalTilt;
        Context(int x){
            this.totalTilt = x;
        }
    }
    int sumNodes(TreeNode root, Context context){
        if(root == null){
            return 0;
        }
        int lsum = sumNodes(root.left, context);
        int rsum = sumNodes(root.right, context);
        int temp = root.val;
        // update current root's value as the tilt
        root.val = Math.abs(lsum-rsum); // skip this if orignal tree shouldn't be modified?
        // update total tilt in context
        context.totalTilt += root.val;
        // return sum of nodes in the subtree rooted at root.
        return lsum + rsum + temp;
    }
    /**
     * 
     * Given the root of a binary tree, return the sum of every tree node's tilt.

        The tilt of a tree node is the absolute difference between the sum of all left subtree node values and all right subtree node values. If a node does not have a left child, then the sum of the left subtree node values is treated as 0. The rule is similar if there the node does not have a right child.

        

        Example 1:


        Input: root = [1,2,3]
        Output: 1
        Explanation: 
        Tilt of node 2 : |0-0| = 0 (no children)
        Tilt of node 3 : |0-0| = 0 (no children)
        Tile of node 1 : |2-3| = 1 (left subtree is just left child, so sum is 2; right subtree is just right child, so sum is 3)
        Sum of every tilt : 0 + 0 + 1 = 1

        Idea: Idea is that we need sum of all nodes in left subtree and right subtree before calculating the tilt of a root.
        So do a post order traveral of the BT and keep track of sum of all tilts in a context class.
        
     * @param root
     * @return
     */
    public int findTilt(TreeNode root) {
        Context tiltContext = new Context(0);
        sumNodes(root, tiltContext);
        return tiltContext.totalTilt;
    }
}
